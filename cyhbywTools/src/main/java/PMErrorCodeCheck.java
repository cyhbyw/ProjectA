import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yanhuche on 2/23/2016.
 */
public class PMErrorCodeCheck {

    private static final String NB3P = "NB3P";

    private List<String> errorType_DoesNotUseErrorCode = new ArrayList<>();
    private List<String> errorType_ArgumentNumberNotEqual = new ArrayList<>();
    private List<String> errorType_NotInSameLine = new ArrayList<>();
    private List<String> errorType_EmptyMessageInfo = new ArrayList<>();
    private List<String> errorType_StackTraceNotPresentation = new ArrayList<>();
    private List<String> errorType_UndefinedMessageId = new ArrayList<>();
    private List<String> errorType_UndefinedNB3PID = new ArrayList<>();
    private List<String> errorType_DuplicateNB3PID = new ArrayList<>();
    private List<String> errorType_ArgumentIndexError = new ArrayList<>();
    private List<String> errorType_RightBraceError = new ArrayList<>();
    private List<String> errorType_MessageIdDefineError = new ArrayList<>();
    private List<String> errorType_MessageIdNumberUnequal = new ArrayList<>();

    private Map<String, Integer> messagePropertiesUsedTimesMap = new TreeMap<>();
    private Map<String, String> messagePropertiesMap = new HashMap<>();
    private List<String> messageIDList = new ArrayList<>();
    private int warnAndErrorLogInSameLineCount = 0;
    private int totalJavaFile = 0;


    public static void main(String[] args) {
        PMErrorCodeCheck cyh = new PMErrorCodeCheck();
        try {
            cyh.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run(String[] args) throws IOException {
        if (args == null || args.length == 0) {
            System.out.println("Usage: java -jar PMErrorCodeCheck.jar absPMCodePath");
            System.out.println("e.g.: java -jar PMErrorCodeCheck.jar D:\\code_ALL\\OSS_NBI_3gcpn_N16.5");
            System.out
                    .println("e.g.: java -jar PMErrorCodeCheck.jar D:\\code_ALL\\OSS_NBI_3gcpn_N16.5\\implementation");
            System.out.println("System will exit...");
            return;
        }

        String rootDir = args[0];
        System.out.println("rootDir: " + rootDir);
        checkMessageIdAndMessagesPropertyFile(rootDir);
        scanAllJavaFile(rootDir);
        writeToFile();

        /*
         * for (Map.Entry entry : isMsgPropsUsed.entrySet()) { System.out.println(NB3P + entry.getKey() + " used: " +
         * entry.getValue()); }
         */

        System.out.println("totalJavaFile: " + totalJavaFile);
        System.out.println("warnAndErrorLogInSameLineCount: " + warnAndErrorLogInSameLineCount);
    }

    private void writeToFile() throws IOException {
        String filePath = System.getProperty("user.dir") + "/PMErrorCodeCheckResult.txt";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(filePath)));

        writeOneErrorType(bufferedWriter, errorType_DoesNotUseErrorCode, "DoesNotUseErrorCode");
        writeOneErrorType(bufferedWriter, errorType_ArgumentNumberNotEqual, "ArgumentNumberNotEqual");
        writeOneErrorType(bufferedWriter, errorType_NotInSameLine, "NotInSameLine");
        writeOneErrorType(bufferedWriter, errorType_EmptyMessageInfo, "EmptyMessageInfo");
        writeOneErrorType(bufferedWriter, errorType_StackTraceNotPresentation, "StackTraceNotPresentation");
        writeOneErrorType(bufferedWriter, errorType_UndefinedMessageId, "UndefinedMessageId");
        writeOneErrorType(bufferedWriter, errorType_UndefinedNB3PID, "UndefinedNB3PID");
        writeOneErrorType(bufferedWriter, errorType_DuplicateNB3PID, "DuplicateNB3PID");
        writeOneErrorType(bufferedWriter, errorType_ArgumentIndexError, "ArgumentIndexError");
        writeOneErrorType(bufferedWriter, errorType_RightBraceError, "RightBraceError");
        writeOneErrorType(bufferedWriter, errorType_MessageIdDefineError, "MessageIdDefineError");
        writeOneErrorType(bufferedWriter, errorType_MessageIdNumberUnequal, "MessageIdNumberUnequal");

        bufferedWriter.close();
    }

    private void writeOneErrorType(BufferedWriter bufferedWriter, List<String> list, String errorType)
            throws IOException {
        if (list.isEmpty()) {
            return;
        }

        StringBuilder stringBuilder = new StringBuilder("====Begin of " + errorType + "====\n");
        for (String oneLog : list) {
            stringBuilder.append(oneLog).append('\n');
        }
        stringBuilder.append("============End of " + errorType + "============\n\n\n");

        bufferedWriter.write(stringBuilder.toString());
        bufferedWriter.flush();
    }

    private void scanAllJavaFile(String rootDir) throws IOException {
        dfsToCheckJavaFile(new File(rootDir));
    }

    private void dfsToCheckJavaFile(File fileOrDir) throws IOException {
        if (fileOrDir.isFile() && fileOrDir.getName().endsWith("java")) {
            totalJavaFile++;
            dealJavaSourceFile(fileOrDir);
        }

        if (fileOrDir.isDirectory() && !fileOrDir.getName().equals("target")) {
            File[] files = fileOrDir.listFiles();
            if (files != null && files.length != 0) {
                for (File file : files) {
                    dfsToCheckJavaFile(file);
                }
            }
        }
    }

    private void dealJavaSourceFile(File javaFile) throws IOException {
        if (javaFile.getName().equals("LogTest.java")) {
            return;
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(javaFile));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            line = line.trim();
            if (line.startsWith("LOG.warn") || line.startsWith("LOGGER.warn") || line.startsWith("log.warn")
                    || line.startsWith("logger.warn") || line.startsWith("LOG.error")
                    || line.startsWith("LOGGER.error") || line.startsWith("log.error")
                    || line.startsWith("logger.error")) {

                if (!line.endsWith(";")) {
                    errorType_NotInSameLine.add(line + " in file: " + javaFile.getName());
                } else {
                    warnAndErrorLogInSameLineCount++;
                    dealLogLine(javaFile, line);
                }
            }
        }
        bufferedReader.close();
    }

    private void dealLogLine(File javaFile, String logLine) {
        if (!logLine.contains("MessageId")) {
            errorType_DoesNotUseErrorCode.add(logLine + " in file: " + javaFile.getName());
            return;
        }

        int leftBracketPosition = logLine.indexOf('(');
        int rightBracketPosition = logLine.lastIndexOf(')');
        String logArgs = logLine.substring(leftBracketPosition + 1, rightBracketPosition).trim();
        String[] splitLogArgs = logArgs.split(",");
        String lastLogArg = splitLogArgs[splitLogArgs.length - 1].trim();
        int logArgsSize = splitLogArgs.length - 1; // discard the first one, for it is the MessageId
        boolean containsException = false;
        if (lastLogArg.matches("e\\d*") || lastLogArg.matches("t\\d*")) {
            // discard the last one, for it is Exception or Throwable argument
            logArgsSize--;
            containsException = true;
        }

        String firstLogArg = splitLogArgs[0].trim();
        String logMessageId = firstLogArg.split("\\.")[1];
        logMessageId = logMessageId.substring(logMessageId.length() - 4);
        if (!messageIDList.contains(logMessageId)) {
            errorType_UndefinedMessageId.add(firstLogArg + " is undefined in MessageId.java");
            return;
        }

        if (!messagePropertiesUsedTimesMap.containsKey(logMessageId)) {
            errorType_UndefinedNB3PID.add(firstLogArg + " is undefined in messages.properties");
            return;
        }

        checkNumEquality(logArgsSize, logMessageId, logLine, javaFile, containsException);
    }

    private void checkNumEquality(int logArgsSize, String logMessageId, String logLine, File javaFile,
            boolean containsException) {
        String logMessageInfo = messagePropertiesMap.get(logMessageId);
        messagePropertiesUsedTimesMap.put(logMessageId, messagePropertiesUsedTimesMap.get(logMessageId) + 1);

        if (logMessageInfo == null || logMessageInfo.trim().isEmpty()) {
            errorType_EmptyMessageInfo.add(NB3P + logMessageId + " is null or empty");
            return;
        }

        if (logMessageInfo.length() > 20) {
            String sufferLogMessageInfo = logMessageInfo.substring(logMessageInfo.length() - 15);
            if (sufferLogMessageInfo.contains("Exception") || sufferLogMessageInfo.contains("exception")) {
                if (!containsException) {
                    errorType_StackTraceNotPresentation.add(logMessageId + "==" + logMessageInfo + ", " + "Log=="
                            + logLine + ", in file: " + javaFile.getName());
                    return;
                }
            }
        }

        char startNumChar = '0' - 1;
        for (int i = 0; i < logMessageInfo.length(); i++) {
            if (logMessageInfo.charAt(i) == '{') {
                startNumChar++;
                if (logMessageInfo.charAt(i + 1) != startNumChar) {
                    errorType_ArgumentIndexError.add(logMessageId + "==" + logMessageInfo + ", Log==" + logLine
                            + ", in file: " + javaFile.getName());
                    return;
                }
                i++;
                i++;
                if (logMessageInfo.charAt(i) != '}') {
                    errorType_RightBraceError.add("Right brace error");
                    return;
                }
            }
        }

        int expectArguNum = Integer.valueOf(startNumChar + 1 - '0');
        if (expectArguNum != logArgsSize) {
            errorType_ArgumentNumberNotEqual.add("ExpectedArgumentsNum=" + expectArguNum + ", " + "ActuallyNum="
                    + logArgsSize + ", NB3P" + logMessageId + "=" + logMessageInfo + ", Log==" + logLine
                    + ", in file: " + javaFile.getName());
        }
    }

    private void checkMessageIdAndMessagesPropertyFile(String rootDir) throws IOException {
        dfsToFind(new File(rootDir));
    }

    private void dfsToFind(File fileOrDir) throws IOException {
        if (fileOrDir.getName().equals("messages.properties")) {
            dealMessagesPropertyFile(fileOrDir);
        }

        if (fileOrDir.getName().equals("MessageId.java")) {
            dealMessageIdFile(fileOrDir);
        }

        if (fileOrDir.isDirectory() && !fileOrDir.getName().equals("target")) {
            File[] files = fileOrDir.listFiles();
            if (files != null && files.length != 0) {
                for (File file : files) {
                    dfsToFind(file);
                }
            }
        }
    }

    private void dealMessageIdFile(File messageIdFile) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(messageIdFile));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            line = line.trim();
            if (!(line.contains("String") && line.contains("=") && line.contains("PREFIX") && line.contains("+"))) {
                continue;
            }

            if (!line.matches("String MSG\\d{4} = PREFIX \\+ \"\\d{4}\";")) {
                errorType_MessageIdDefineError.add(line);
                continue;
            }

            int MSGStartPosition = line.indexOf("MSG");
            String firstMessageId = line.substring(MSGStartPosition + 3, MSGStartPosition + 7);

            int leftQuotePosition = line.indexOf("\"");
            int rightQuotePosition = line.lastIndexOf("\"");
            String secondMessageId = line.substring(leftQuotePosition + 1, rightQuotePosition);

            if (!firstMessageId.equals(secondMessageId)) {
                errorType_MessageIdNumberUnequal.add(line);
                continue;
            }

            messageIDList.add(secondMessageId);
        }
        bufferedReader.close();
    }

    private void dealMessagesPropertyFile(File msgFile) throws IOException {
        System.out.println("messages.properties file path: " + msgFile.getAbsolutePath());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(msgFile));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.startsWith(NB3P) && line.contains("=")) {
                String NB3PID = line.substring(4, 8);
                if (messagePropertiesMap.containsKey(NB3PID)) {
                    errorType_DuplicateNB3PID.add(NB3P + NB3PID + " is duplicate in messages.properties file.");
                } else {
                    messagePropertiesMap.put(NB3PID, line.substring(9));
                    messagePropertiesUsedTimesMap.put(NB3PID, 0);
                }
            }
        }
        bufferedReader.close();
    }


}



/*

D:\code_ALL\OSS_NBI_3gcpn_N16.5\implementation

D:\code_ALL\OSS_NBI_3gcpn\implementation

D:\code_idea\CYH_SELF_FUN_PM\implementation


*/
