package _2021;

import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        String pathName = "D:\\opensource\\feign";
        Path rootPath = Paths.get(pathName);
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(rootPath);
        Iterator<Path> iterator = directoryStream.iterator();
        Map<String, String> changedMap = new HashMap<>();
        while (iterator.hasNext()) {
            Path next = iterator.next();
            String fileName = next.getFileName().toString();
            if (Files.isDirectory(next)) {
                String artifactId = getArtifactIdFromPomFile(next);
                if (artifactId != null && artifactId.length() > 0) {
                    Path newPath = next.getParent().resolve(artifactId);
                    Files.move(next, newPath);
                    changedMap.put(fileName, artifactId);
                    System.out.println(fileName + "  <---->  " + artifactId);
                }
            }
        }
        changeRootPomModules(pathName, changedMap);
    }

    private static void changeRootPomModules(String pathName, Map<String, String> changedMap) throws Exception {
        Path path = Paths.get(pathName, "pom.xml");
        StringBuilder builder = new StringBuilder();
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        boolean inModules = false;
        for (String line : allLines) {
            builder.append(whiteSpace(line));
            line = line.trim();
            String others = line;
            if (line.equals("<modules>")) {
                inModules = true;
            } else if (line.equals("</modules>")) {
                inModules = false;
            } else if (line.startsWith("<module>") && line.endsWith("</module>") && inModules) {
                String module = line.substring("<module>".length(), line.length() - "</module>".length());
                String newModuleName = changedMap.get(module);
                if (newModuleName == null || newModuleName.isEmpty()) {
                    throw new RuntimeException("Invalid pom.xml file......");
                }
                others = String.format("<module>%s</module>", newModuleName);
                System.out.println(module + " <====> " + newModuleName);
            }
            builder.append(others).append("\n");
        }
        Files.write(path, builder.toString().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
    }

    private static String whiteSpace(String line) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                builder.append(" ");
            } else {
                break;
            }
        }
        return builder.toString();
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
                    } else if (line.startsWith(START) && line.endsWith(END) && !inParent) {
                        return line.substring(START.length(), line.length() - END.length());
                    }
                }
            }
        }
        return null;
    }


}
