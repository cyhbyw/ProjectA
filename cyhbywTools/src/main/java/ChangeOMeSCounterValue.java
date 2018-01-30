import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanhuche on 5/5/2016.
 */
public class ChangeOMeSCounterValue {


    private final String[] unnecessaryTags = {"<?xml", "<OMeS", "<PMSetup", "<PMMOResult", "<MO", "<DN", "<PMTarget",
            "</"};
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    private int minuteAgo = 0;

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Usage: java ChangeOMsSCounterValue absFileName <minuteAgo_default_0>");
            System.exit(-1);
        }
        ChangeOMeSCounterValue cyh = new ChangeOMeSCounterValue();
        cyh.run(args);
    }

    private void run(String[] args) throws IOException {
        String filePath = args[0];
        if (args.length >= 2) {
            minuteAgo = Integer.valueOf(args[1]);
        }
        File inputOMesFile = new File(filePath);
        LineNumberReader bufferedReader = new LineNumberReader(new FileReader(inputOMesFile));
        String line;
        StringBuilder replacedLine = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            if (line.trim().isEmpty()) {
                continue;
            }
            if (isUnnecessaryTags(line)) {
                if (line.trim().startsWith("<PMSetup")) {
                    line = changeStartTime(line);
                }
                replacedLine.append(line).append('\n');
                continue;
            }
            int index = 0;
            while (true) {
                replacedLine.append(line.charAt(index));
                if (line.charAt(index) == '>') {
                    break;
                }
                index++;
            }
            replacedLine.append(1000000000 + new Random().nextInt(10000000));
            while (line.charAt(index) != '<') {
                index++;
            }
            while (index < line.length()) {
                replacedLine.append(line.charAt(index));
                index++;
            }
            replacedLine.append('\n');
        }
        bufferedReader.close();

        File outputOMeSFile =
                new File(inputOMesFile.getParent() + "/newCounterValue_" + System.currentTimeMillis() + "_"
                        + inputOMesFile.getName());
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputOMeSFile));
        bufferedWriter.write(replacedLine.toString());
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private String changeStartTime(String line) {
        StringBuilder PMSetupLine = new StringBuilder();
        int index = 0;
        while (true) {
            PMSetupLine.append(line.charAt(index));
            if (line.charAt(index) == '"') {
                break;
            }
            index++;
        }

        index++; // next of "
        String startTime =
                simpleDateFormat.format(new Date(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(minuteAgo)));
        startTime = startTime.substring(0, startTime.length() - 2);
        startTime += ":00:00";
        PMSetupLine.append(startTime);
        while (line.charAt(index) != '"') {
            index++;
        }
        while (index < line.length()) {
            PMSetupLine.append(line.charAt(index));
            index++;
        }
        return PMSetupLine.toString();
    }

    private boolean isUnnecessaryTags(String line) {
        line = line.trim();
        for (int i = 0; i < unnecessaryTags.length; i++) {
            if (line.startsWith(unnecessaryTags[i])) {
                return true;
            }
        }
        return false;
    }


}
