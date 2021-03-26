package _2021;

import com.google.common.base.Splitter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * 将驼峰命名的变量改为小写下划线格式
 *
 * @author: yanhua.chen
 * @date: 2021/3/26 9:58
 */
public class ChangeCamelCaseToLowerUnderLine {

    public static void main(String[] args) throws Exception {
        Path path = Paths.get("D:\\txt\\3.txt");
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        StringBuilder builder = new StringBuilder();
        for (String line : allLines) {
            line = line.trim();
            if (!line.startsWith("//")) {
                List<String> split = Splitter.on(" ").splitToList(line);
                String variable = changeToLoverUnderLine(split.get(2));
                builder.append("  `").append(variable).append("` COMMENT '',\n");
            }
        }
        System.out.println(builder.toString());
    }

    private static String changeToLoverUnderLine(String variable) {
        StringBuilder builder = new StringBuilder();
        for (char c : variable.toCharArray()) {
            if (Character.isUpperCase(c)) {
                builder.append("_").append((char) (c + 32));
            } else if (c != ';') {
                builder.append(c);
            }
        }
        return builder.toString();
    }

}
