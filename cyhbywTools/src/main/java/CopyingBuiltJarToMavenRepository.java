import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 将编译好的SpringJar包Copy到对应的Maven目录中
 */
public class CopyingBuiltJarToMavenRepository {

    public static void main(String[] args) throws IOException {
        new CopyingBuiltJarToMavenRepository().run();
    }

    private static final Path SPRING_ROOT_PATH = Paths.get("D:\\openSource\\eureka_1_4_10\\eureka");
    private static final String SPRING_VERSION = "1.4.10";
    private static final Path MAVEN_ROOT_PATH = Paths.get("D:\\mvnRepo\\com\\netflix\\eureka");

    private void run() throws IOException {
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(SPRING_ROOT_PATH);
        Iterator<Path> iterator = directoryStream.iterator();
        while (iterator.hasNext()) {
            Path module = iterator.next();
            String moduleName = module.getFileName().toString();
            if (!Files.isDirectory(module) || !moduleName.startsWith("eureka")) {
                continue;
            }
            List<String> jarFileNames = buildJarFileNames(moduleName);
            List<Path> jarPaths = buildJarPaths(module, jarFileNames);
            copyWithOverride(moduleName, jarPaths);
        }
    }

    private List<String> buildJarFileNames(String moduleName) {
        String jarName = moduleName + "-" + SPRING_VERSION + ".jar";
        String sourceJarName = moduleName + "-" + SPRING_VERSION + "-sources.jar";
        return new ArrayList<>(Arrays.asList(jarName, sourceJarName));
    }

    private List<Path> buildJarPaths(Path module, List<String> jarFileNames) {
        Path jarPath = module.resolve("build").resolve("libs").resolve(jarFileNames.get(0));
        Path sourceJarPath = module.resolve("build").resolve("libs").resolve(jarFileNames.get(1));
        return new ArrayList<>(Arrays.asList(jarPath, sourceJarPath));
    }

    private void copyWithOverride(String moduleName, List<Path> jarPaths) {
        for (Path jarPath : jarPaths) {
            Path mavenDirPath = MAVEN_ROOT_PATH.resolve(moduleName).resolve(SPRING_VERSION);
            Path mavenJarPath = mavenDirPath.resolve(jarPath.getFileName());
            if (Files.exists(jarPath) && Files.exists(mavenJarPath)) {
                try {
                    Files.copy(jarPath, mavenJarPath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Copied: " + mavenJarPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
