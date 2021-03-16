package _2021;

import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

/**
 * 修改 Maven 工程的磁盘目录名和 artifactId 不一致的问题
 *
 * TODO: 没实现递归
 *
 * @author: yanhua.chen
 * @date: 2021/3/16 9:21
 */
public class ChangeMavenProjectModuleName {

    private static final String START = "<artifactId>";
    private static final String END = "</artifactId>";

    public static void main(String[] args) throws Exception {
        Path rootPath = Paths.get("D:\\opensource\\nacos-1.4.1");
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(rootPath);
        Iterator<Path> iterator = directoryStream.iterator();
        while (iterator.hasNext()) {
            Path next = iterator.next();
            if (Files.isDirectory(next)) {
                String artifactId = getArtifactIdFromPomFile(next);
                if (artifactId != null && artifactId.length() > 0) {
                    Path newPath = next.getParent().resolve(artifactId);
                    Files.move(next, newPath);
                    System.out.println(newPath);
                }

            }
        }
    }

    private static String getArtifactIdFromPomFile(Path dir) throws Exception {
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir);
        Iterator<Path> iterator = directoryStream.iterator();
        boolean inParent = false;
        while (iterator.hasNext()) {
            Path next = iterator.next();
            String fileName = next.getFileName().toString();
            if (fileName.endsWith("pom.xml")) {
                List<String> allLines = Files.readAllLines(next, StandardCharsets.UTF_8);
                for (String line : allLines) {
                    line = line.trim();
                    if (line.equals("<parent>")) {
                        inParent = true;
                    } else if (line.equals("</parent>")) {
                        inParent = false;
                    }
                    if (line.startsWith(START) && line.endsWith(END) && !inParent) {
                        return line.substring(START.length(), line.length() - END.length());
                    }
                }
            }
        }
        return null;
    }


}
