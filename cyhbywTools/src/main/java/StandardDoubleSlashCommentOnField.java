import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * 将字段上一行的 // 注释标准化
 * @author: yanhua.chen
 * @date: 2019/4/18 17:34
 */
public class StandardDoubleSlashCommentOnField {

    public static void main(String[] args) throws Exception {
        new StandardDoubleSlashCommentOnField().run();
    }

    private static final String SP = "\n";

    private void run() throws IOException {
        Path path = Paths.get("E:/txt/7.txt");
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
                result.append(String.format("/** %s */", line.substring(2))).append(SP);
            }
        });
        System.out.println(result);
    }


}
