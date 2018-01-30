import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanhuche on 2/23/2016.
 */
public class SnmpErrorCodeCheck {

    Map<String, String> msgProps = new HashMap<String, String>();
    int warnAndErrorLogInSameLineCount = 0;
    int totalJavaFile = 0;


    public static void main(String[] args) {
        SnmpErrorCodeCheck cyh = new SnmpErrorCodeCheck();
        try {
            cyh.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run(String[] args) throws IOException {
        if (args == null || args.length == 0) {
            System.out.println("Usage: java -jar SnmpErrorCodeCheck.jar absPath"); // TODO
            return;
        }

        String rootDir = args[0];
        checkMessagesPropertyFile(rootDir);
        scanAllJavaCode(rootDir);
        System.out.println("totalJavaFile==" + totalJavaFile);
        System.out.println("warnAndErrorLogInSameLineCount==" + warnAndErrorLogInSameLineCount);
    }

    private void scanAllJavaCode(String rootDir) throws IOException {
        dfsToCheckJavaCode(new File(rootDir));
    }

    private void dfsToCheckJavaCode(File fileOrDir) throws IOException {
        if (fileOrDir.isFile() && fileOrDir.getName().endsWith("java")) {
            totalJavaFile++;
            dealJavaSourceFile(fileOrDir);
        }

        if (fileOrDir.isDirectory() && !fileOrDir.getName().equals("target")) {
            File[] files = fileOrDir.listFiles();
            if (files != null && files.length != 0) {
                for (File file : files) {
                    dfsToCheckJavaCode(file);
                }
            }
        }
    }

    private void dealJavaSourceFile(File javaFile) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(javaFile));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            line = line.trim();
            if (line.startsWith("LOG.warn") || line.startsWith("LOGGER.warn")
                    || line.startsWith("LOG.error") || line.startsWith("LOGGER.error")) {

                if (!line.endsWith(";")) {
                    System.out.println(line + "    in file: " + javaFile.getName());
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
//            System.err.println("ERROR_A \"" + logLine + "\" does not use error code, in file: " + javaFile.getName());
            return;
        }

        int leftBracketPosi = logLine.indexOf('(');
        int rightBracketPosi = logLine.lastIndexOf(')');
        String logArgs = logLine.substring(leftBracketPosi + 1, rightBracketPosi).trim();
        String[] splitLogArgs = logArgs.split(",");
        int logArgsSize = splitLogArgs.length - 1; // discard the first one, for it is the MessageId

        String firstLogArg = splitLogArgs[0].trim();
        String logMessageId = firstLogArg.split("\\.")[1];
        logMessageId = logMessageId.substring(logMessageId.length() - 4);
        String logMessageInfo = msgProps.get(logMessageId);

        checkNumEquality(logArgsSize, logMessageId, logMessageInfo, logLine, javaFile);
    }

    private void checkNumEquality(int logArgsSize, String logMessageId, String logMessageInfo, String logLine, File javaFile) {
        if (logMessageInfo == null) {
            System.err.println("ERROR_D " + logMessageId + "=null, in file: " + javaFile.getName());
            return;
        }

        char startNumChar = '0' - 1;
        for (int i = 0; i < logMessageInfo.length(); i++) {
            if (logMessageInfo.charAt(i) == '{') {
                startNumChar++;
                if (logMessageInfo.charAt(i + 1) != startNumChar) {
                    System.err.println("ERROR_B exists in: " + logMessageId + "==" + logMessageInfo + ", Log==" + logLine + ", in file: " + javaFile.getName());
                    return;
                }
                i++;
                i++;
                if (logMessageInfo.charAt(i) != '}') {
                    System.err.println("ERROR_E");
                    return;
                }
            }
        }

        int expectArguNum = Integer.valueOf(startNumChar + 1 - '0');
        if (logArgsSize < expectArguNum) {
            System.err.println("ERROR_C Expected arguments num=" + expectArguNum
                    + ", Actually num=" + logArgsSize
                    + ", NBSP" + logMessageId + "=" + logMessageInfo
                    + ", Log==" + logLine
                    + ", in file: " + javaFile.getName());
        }
    }

    private void checkMessagesPropertyFile(String rootDir) throws IOException {
        dfsToFind(new File(rootDir));
    }

    private void dfsToFind(File fileOrDir) throws IOException {
        if (fileOrDir.getName().equals("messages.properties")) {
            dealMessagesPropertyFile(fileOrDir);
        }

        if (fileOrDir.isDirectory()) {
            File[] files = fileOrDir.listFiles();
            if (files != null && files.length != 0) {
                for (File file : files) {
                    dfsToFind(file);
                }
            }
        }
    }

    private void dealMessagesPropertyFile(File msgFile) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(msgFile));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.startsWith("NB") && line.contains("=")) {
                msgProps.put(line.substring(4, 8), line.substring(9));
            }
        }
        bufferedReader.close();
//        System.out.println("msgProps.size()=" + msgProps.size());
    }


}
