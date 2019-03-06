import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * 将字段上的行尾的 // 注释标准化
 * @author: yanhua.chen
 * @date: 2019/3/6 18:13
 */
public class StandardEndingComment {

    public static void main(String[] args) throws Exception {
        new StandardEndingComment().run();
    }

    private static final String SP = "\n";

    private void run() throws IOException {
        Path path = Paths.get("E:/txt/11.txt");
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        StringBuilder result = new StringBuilder();
        allLines.forEach(line -> {
            if ((line = line.trim()).isEmpty()) {
                return;
            }
            int index = line.indexOf("//");
            if (index == -1) {
                result.append(line).append(SP);
            } else {
                String left = line.substring(0, index).trim();
                String right = line.substring(index + 2).trim();
                result.append(String.format("/** %s */", right)).append(SP);
                result.append(left).append(SP);
            }
        });
        System.out.println(result.toString());
    }


}
