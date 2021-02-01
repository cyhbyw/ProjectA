package com.cyh.utils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * 将 JSON 文件内容压缩成一行，并替换其中的引号，以方便直接拷贝到 Postman 中
 *
 * @auther yanhua.chen
 * @date: 2021/1/30 0030 9:49
 */
public class JoinFileContentToOneLine {

    public static void main(String[] args) throws Exception {
        new JoinFileContentToOneLine().run();
    }

    private void run() throws Exception {
        final Path path = Paths.get("D:\\code_cyh_personal\\ProjectA\\mock-server\\CYH\\join_to_one_line.txt");
        StringBuilder all = new StringBuilder();
        final List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (String line : allLines) {
            all.append(line.trim());
        }
        String s = all.toString();
        s = s.replaceAll("\"", "\\\\\"");
        System.out.println(s);
    }


}
