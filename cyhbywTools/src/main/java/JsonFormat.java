import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author CYH
 * @date 2018/1/30
 */
public class JsonFormat {

    public static void main(String[] args) throws Exception {
        new JsonFormat().run();
    }

    private void run() throws IOException {
        String diskPath = "D:\\txt\\JsonFormat.in";
        Path path = Paths.get(diskPath);
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        StringBuilder result = new StringBuilder(1 << 10);
        for (String line : allLines) {
            line = line.trim();
            if (line.isEmpty() || line.charAt(0) == '#') {
                continue;
            }
            String lineResult = formatLine(line);
            result.append(lineResult);
        }
        String outDiskPath = "D:\\txt\\JsonFormat.out";
        Files.write(Paths.get(outDiskPath), result.toString().getBytes());
    }

    private String formatLine(String line) {
        int tabCount = 0;
        boolean inQuot = false;
        StringBuilder result = new StringBuilder(1 << 8);
        for (int chIndex = 0; chIndex < line.length(); chIndex++) {
            char ch = line.charAt(chIndex);
            result.append(ch);
            if (ch == '"') {
                inQuot = !inQuot;
            }
            if (!inQuot) {
                if (ch == '[' || ch == '{') {
                    result.append('\n');
                    tabCount += 4;
                    result.append(appendTab(tabCount));
                }
                if (ch == ',') {
                    result.append('\n');
                    result.append(appendTab(tabCount));
                    while (chIndex + 1 < line.length() && line.charAt(chIndex + 1) == ' ') {
                        chIndex++;
                    }
                }
                if (chIndex + 1 < line.length()) {
                    char nextCh = line.charAt(chIndex + 1);
                    if (nextCh == ']' || nextCh == '}') {
                        result.append('\n');
                        tabCount -= 4;
                        result.append(appendTab(tabCount));
                    }
                }
            } else {
            }
        }
        result.append("\n--------------------------------------cyhbyw---------------------------------------------\n");
        return result.toString();
    }

    private String appendTab(int tabCount) {
        StringBuilder result = new StringBuilder(4);
        while (tabCount-- > 0) {
            result.append(' ');
        }
        return result.toString();
    }


}
