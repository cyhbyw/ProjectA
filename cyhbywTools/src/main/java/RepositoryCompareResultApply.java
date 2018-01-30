import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RepositoryCompareResultApply {

    public static void main(String[] args) throws IOException {
        new RepositoryCompareResultApply().run(args);
    }


    private Path compareResultPath;
    private Path repositoryPath;

    private void run(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("ERROR! Usage: java RepositoryCompareResultApply CompareResultPath RepositoryPath");
            return;
        }

        compareResultPath = Paths.get(args[0]);
        repositoryPath = Paths.get(args[1]);

        List<Path> pathList = dfs(compareResultPath.getNameCount(), compareResultPath);
        apply(pathList);
    }

    private List<Path> dfs(int nameCount, Path dirPath) throws IOException {
        List<Path> pathList = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dirPath)) {
            Iterator<Path> iterator = directoryStream.iterator();
            while (iterator.hasNext()) {
                Path next = iterator.next();
                if (Files.isDirectory(next)) {
                    pathList.addAll(dfs(nameCount, next));
                } else {
                    pathList.add(next.subpath(nameCount, next.getNameCount()));
                }
            }
        }
        return pathList;
    }

    private void apply(List<Path> pathList) throws IOException {
        for (Path path : pathList) {
            Path destFilePath = repositoryPath.resolve(path);
            if (Files.isRegularFile(destFilePath)) {
                continue;
            }

            Path destDirPath = repositoryPath.resolve(path.getParent());
            if (!Files.isDirectory(destDirPath)) {
                Files.createDirectories(destDirPath, new FileAttribute[] {});
            }
            Path srcFilePath = compareResultPath.resolve(path);
            Files.copy(srcFilePath, destFilePath);
        }
    }


}
