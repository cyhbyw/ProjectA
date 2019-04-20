import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * 将 Pdm 生成的 Sql 语句转换为对象的字段
 * @author: yanhua.chen
 * @date: 2019/4/19 16:16
 */
public class ConvertPdmSqlToInstanceField {

    public static void main(String[] args) throws Exception {
        new ConvertPdmSqlToInstanceField().run();
    }

    private void run() throws Exception {
        List<String> allLines = readFromFile();
        convert(allLines);
    }

    private List<String> readFromFile() throws URISyntaxException, IOException {
        Path path = Paths.get(this.getClass().getClassLoader().getResource("a.sql").toURI());
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }

    private void convert(List<String> allLines) {
        allLines.forEach(line -> {
            if ((line = line.trim()).isEmpty()) {
                return;
            }

            List<String> split = ConvertTableDefinitionToExcel.selfSplit(line);
            String fieldName = ConvertTableDefinitionToExcel.sub(split.get(0));
            fieldName = handleUnderLine(fieldName);
            String fieldType = convertType(split.get(1));
            String comment = ConvertTableDefinitionToExcel.findComment(split);

            System.out.println(String.format("/** %s */", comment));
            System.out.println(String.format("private %s %s;", fieldType, fieldName));
        });
    }

    private String handleUnderLine(String fieldName) {
        StringBuilder builder = new StringBuilder(fieldName.length());
        for (int x = 0; x < fieldName.length(); x++) {
            char ch = fieldName.charAt(x);
            if (ch == '_') {
                char chNext = fieldName.charAt(++x);
                builder.append((char) (chNext - 32));
            } else {
                builder.append(ch);
            }
        }
        return builder.toString();
    }

    private String convertType(String type) {
        if (type.startsWith("int")) {
            return "Integer";
        }
        if (type.startsWith("varchar")) {
            return "String";
        }
        if (type.startsWith("datetime") || type.startsWith("timestamp")) {
            return "Date";
        }
        if (type.startsWith("decimal")) {
            return "BigDecimal";
        }
        if (type.startsWith("bigint")) {
            return "Long";
        }
        return "ERROR_TYPE";
    }

}
