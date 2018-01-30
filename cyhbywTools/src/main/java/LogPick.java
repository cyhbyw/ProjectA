import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanhuche on 11/7/2016.
 */
public class LogPick {


    public static void main(String[] args) throws IOException {
        new LogPick().run();
    }


    private String regexpString = "at [[a-zA-Z\\$\\d]+\\.]+[a-zA-Z\\$\\d]+\\([a-zA-Z\\$\\d]+\\.java:\\d+\\)";
    private List<String[]> pick = new ArrayList<>();
    private List<String> unpick = new ArrayList<>();
    private List<File> toBePickFile = new ArrayList<>();
    private boolean isStackTraceOn = true;


    private void run() throws IOException {
        retrieveConfigArgument();
        doPick();
    }

    private void retrieveConfigArgument() throws IOException {
        File configFile = new File("/home/omc/cyh/LogPick.config");
        BufferedReader bufferedReader = new LineNumberReader(new FileReader(configFile));
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty() || line.charAt(0) == '#') {
                continue;
            }
            if (line.startsWith("pick")) {
                String[] oneLinePick = line.split("=")[1].split("&&");
                for (int i = 0; i < oneLinePick.length; i++) {
                    oneLinePick[i] = oneLinePick[i].trim();
                }
                pick.add(oneLinePick);
            } else if (line.startsWith("unpick")) {
                unpick.add(line.split("=")[1].trim());
            } else if (line.startsWith("stackTrace")) {
                isStackTraceOn = line.split("=")[1].trim().equalsIgnoreCase("on");
            } else {
                toBePickFile.add(new File(line));
            }
        }
        bufferedReader.close();
    }

    public void doPick() throws IOException {
        StringBuffer sbContent = new StringBuffer();

        for (File oneFile : toBePickFile) {
            LineNumberReader bufferedReader = new LineNumberReader(new FileReader(oneFile));
            sbContent.setLength(0);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (isNeeded(line)) {
                    sbContent.append(String.format("line:%6d |", bufferedReader.getLineNumber())).append(line)
                            .append("\n");
                }
            }
            bufferedReader.close();

            writeToNewFile(sbContent, oneFile);
        }
    }

    private void writeToNewFile(StringBuffer sbContent, File oneFile) throws IOException {
        File newFile = new File(oneFile.getParent() + File.separator + "logPick__2_" + oneFile.getName());
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile));
        bufferedWriter.write(sbContent.toString());
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private boolean isNeeded(String line) {
        if (isStackTraceOn) {
            if (line.startsWith("Caused by") || line.contains("ception") || line.contains("ERROR")
                    || line.trim().matches(regexpString)) {
                return true;
            }
        }

        return isPickedIn(line) && !isPickedOut(line);
    }

    private boolean isPickedIn(String line) {
        if (pick.isEmpty()) {
            return true;
        }

        for (String[] inputPick : pick) {
            if (pickedOneLine(line, inputPick)) {
                return true;
            }
        }
        return false;
    }

    private boolean pickedOneLine(String line, String[] inputPick) {
        for (String input : inputPick) {
            if (!line.contains(input)) {
                return false;
            }
        }
        return true;
    }

    private boolean isPickedOut(String line) {
        for (String input : unpick) {
            if (line.contains(input)) {
                return true;
            }
        }
        return false;
    }

}
