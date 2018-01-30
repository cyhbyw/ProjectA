import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RepositoryCompare {


    public static void main(String[] args) throws Exception {
        new RepositoryCompare().run(args);
    }


    private Path srcRepositoryPath;
    private Path destRepositoryPath;

    private void run(String[] args) throws Exception {
        if (args.length < 2) {
            System.err.println("ERROR! Usage: java RepositoryCompare srcRepositoryPath destRepositoryPath <filter>");
            return;
        }

        srcRepositoryPath = Paths.get(args[0]);
        destRepositoryPath = Paths.get(args[1]);

        List<Path> srcRepositories = dfs(srcRepositoryPath.getNameCount(), srcRepositoryPath);
        List<Path> destRepositories = dfs(destRepositoryPath.getNameCount(), destRepositoryPath);

        checkResult(srcRepositories, destRepositories, args);
    }

    private List<Path> dfs(int rootDirNameCount, Path dirPath) throws IOException {
        List<Path> pathList = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dirPath)) {
            Iterator<Path> iterator = directoryStream.iterator();
            while (iterator.hasNext()) {
                Path next = iterator.next();
                if (Files.isDirectory(next)) {
                    pathList.addAll(dfs(rootDirNameCount, next));
                } else {
                    pathList.add(next.subpath(rootDirNameCount, next.getNameCount()));
                }
            }
        }
        return pathList;
    }

    private void checkResult(List<Path> srcRepositories, List<Path> destRepositories, String[] args) throws IOException {
        Path compareResult = srcRepositoryPath.resolve("RepositoryCompareResult");
        if (Files.isDirectory(compareResult)) {
            Files.delete(compareResult);
        }
        Files.createDirectory(compareResult, new FileAttribute[] {});

        for (Path path : srcRepositories) {
            boolean contains = destRepositories.contains(path);
            boolean isFilterOut = filterOut(path, args);

            if (!contains && !isFilterOut) {
                Path resultDirPath = compareResult.resolve(path.getParent());
                if (!Files.isDirectory(resultDirPath)) {
                    Files.createDirectories(resultDirPath, new FileAttribute[] {});
                }

                Path srcPath = srcRepositoryPath.resolve(path);
                Path destPath = resultDirPath.resolve(path.getFileName());

                System.out.println(srcPath + "  --  " + destPath);
                Files.copy(srcPath, destPath);
            }
        }
    }

    private boolean filterOut(Path path, String[] args) {
        String pathString = path.toString();
        for (int argIndex = 2; argIndex < args.length; argIndex++) {
            if (pathString.contains(args[argIndex])) {
                return true;
            }
        }
        return false;
    }


}
