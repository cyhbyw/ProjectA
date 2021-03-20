package _2021;

import org.apache.commons.io.FileUtils;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * 删除开源 Java 项目下的 src/test 目录
 *
 * @auther yanhua.chen
 * @date: 2021/3/20 0020 7:49
 */
public class RemoveTestDirectory {

	public static void main(String[] args) throws Exception {
		Path rootDir = Paths.get("D:\\open-sources\\Nacos");
		DirectoryStream<Path> directoryStream = Files.newDirectoryStream(rootDir);
		Iterator<Path> iterator = directoryStream.iterator();
		while (iterator.hasNext()) {
			Path next = iterator.next();
			if (!Files.isDirectory(next)) {
				continue;
			}
			Path test = next.resolve("src/test");
			if (Files.exists(test)) {
				FileUtils.deleteDirectory(test.toFile());
				System.out.println("Delete dir: " + test);
			}
		}
	}

}
