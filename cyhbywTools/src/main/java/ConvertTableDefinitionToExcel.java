import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 将 Navicat 导出的DDL内容，抽取相关信息写入到Excel中，方便后期使用/粘贴
 * @author: yanhua.chen
 * @date: 2018/11/6 11:33
 */
public class ConvertTableDefinitionToExcel {

    public static void main(String[] args) throws Exception {
        new ConvertTableDefinitionToExcel().start();
    }


    private static final String QUOT = "`";
    private static final String QUOT2 = "'";
    private static final String FILE_SYSTEM_PATH = "E:\\txt\\db2.sql";

    private void start() throws IOException {
        List<ExcelContent> contentList = new ArrayList<>();
        Files.readAllLines(Paths.get(FILE_SYSTEM_PATH), Charset.defaultCharset()).stream().forEach(line -> {
            line = line.trim();
            if (line.length() == 0) {
                return;
            }

            List<String> split = selfSplit(line);
            String fieldName = sub(split.get(0));
            String fieldType = split.get(1);
            String comment = findComment(split);
            ExcelContent excelContent = new ExcelContent(fieldName, fieldType, comment);
            contentList.add(excelContent);
            System.out.println(excelContent);
        });
        writeToExcel(contentList);
    }

    static List<String> selfSplit(String line) {
        List<String> result = new ArrayList<>();
        boolean inQuot = false;
        for (int index = 0; index < line.length();) {
            char ch = line.charAt(index);
            if (!inQuot && Character.isWhitespace(ch)) {
                index++;
            } else {
                StringBuilder sb = new StringBuilder();
                while (index < line.length()) {
                    ch = line.charAt(index);
                    if (Character.isWhitespace(ch) && !inQuot) {
                        break;
                    }
                    sb.append(ch);
                    index++;
                    if (ch == '\'') {
                        inQuot = !inQuot;
                    }
                }
                result.add(sb.toString());
            }
        }
        return result;
    }

    static String sub(String s) {
        return (s.startsWith(QUOT) && s.endsWith(QUOT)) || (s.startsWith(QUOT2) && s.endsWith(QUOT2))
                ? s.substring(1, s.length() - 1) : s;
    }

    static String findComment(List<String> split) {
        int end = split.size() - 1;
        for (int index = 0; index < end; index++) {
            if ("COMMENT".equalsIgnoreCase(split.get(index))) {
                String comment = split.get(index + 1);
                if (comment.endsWith(",")) {
                    comment = comment.substring(0, comment.length() - 1);
                }
                return sub(comment).trim();
            }
        }
        return "";
    }

    private void writeToExcel(List<ExcelContent> contentList) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("cyh");
        for (int index = 0; index < contentList.size(); index++) {
            XSSFRow row = sheet.createRow(index);
            ExcelContent content = contentList.get(index);
            row.createCell(0).setCellValue(content.getFieldName());
            row.createCell(1).setCellValue(content.getFieldType());
            row.createCell(2).setCellValue(content.getComment());
        }
        FileOutputStream outputStream = new FileOutputStream(new File("D:/temp/tableDefinition.xlsx"));
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }


    @Data
    @AllArgsConstructor
    private static class ExcelContent {

        private String fieldName;
        private String fieldType;
        private String comment;

    }
}
