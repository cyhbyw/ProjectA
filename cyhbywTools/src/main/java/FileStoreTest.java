import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by yanhuche on 10/19/2016.
 */
public class FileStoreTest {

    public static void main(String[] args) throws IOException {
        new FileStoreTest().run(args[0]);
    }

    private void run(String path) throws IOException {
        FileStore fileStore = Files.getFileStore(Paths.get(path));
        String name = fileStore.name();
        long totalSpace = fileStore.getTotalSpace();
        long usableSpace = fileStore.getUsableSpace();

        System.out.printf("name: %s\ntotalSpace: %d %d\nusableSpace: %d %d\n", name, totalSpace, totalSpace >> 30,
                usableSpace, usableSpace >> 30);

    }


}
