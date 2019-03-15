import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class ReportFormat {

    public static void main(String[] args) throws IOException {
        new ReportFormat().run();
    }

    private int tab = 0;
    private StringBuilder result = new StringBuilder();

    private void run() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("D:/txt/ReportFormat.in")));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.trim().isEmpty() || line.trim().charAt(0) == '#') {
                continue;
            }
            for (int e = 0, len = line.length(); e < len; e++) {
                char ch = line.charAt(e);
                result.append(ch);
                if (ch == '[' || ch == '{') {
                    result.append('\n');
                    tab += 4;
                    printTab();
                }
                if (ch == ',') {
                    result.append('\n');
                    printTab();
                    if (e + 1 < len && line.charAt(e + 1) == ' ') {
                        e++;
                    }
                }
                if (e + 1 < len && (line.charAt(e + 1) == ']' || line.charAt(e + 1) == '}')) {
                    result.append('\n');
                    tab -= 4;
                    printTab();
                }
            }
            if (tab != 0) {
                System.err.println("tab != 0");
            }
            result.append("\n-----------------------------------------------------------------------------------\n");
        }
        bufferedReader.close();

        try {
            System.setOut(new PrintStream(new File("D:/txt/ReportFormat.out")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(result.toString());

    }

    private void printTab() {
        if (tab < 0) {
            System.err.println("tab < 0");
            return;
        }

        for (int k = 1; k <= tab; k++) {
            result.append(' ');
        }
    }

}
