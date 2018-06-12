import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * 删除 mvnRepo 目录及子目录中以 .lastUpdated 结尾的文件
 */
public class DeleteLastUpdatedFilesForMaven {

    public static void main(String[] args) throws IOException {
        new DeleteLastUpdatedFilesForMaven().run();
    }

    private static final Path MAVEN_ROOT = Paths.get("D:\\mvnRepo");

    private void run() throws IOException {
        dfs(MAVEN_ROOT);
    }

    private void dfs(Path rootPath) throws IOException {
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(rootPath);
        Iterator<Path> iterator = directoryStream.iterator();
        while (iterator.hasNext()) {
            Path next = iterator.next();
            if (Files.isDirectory(next)) {
                dfs(next);
            } else {
                fileHandler(next);
            }
        }
    }

    private void fileHandler(Path next) {
        if (next.getFileName().toString().endsWith(".lastUpdated")) {
            try {
                Files.delete(next);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
