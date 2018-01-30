package com.cyh.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Created by yanhuche on 3/30/2017.
 */
public class FilesTest01 {


    @Test
    public void test_list() throws IOException {
        Path rootPath = Paths.get("D:/txt");
        Stream<Path> pathStream = Files.list(rootPath);
        pathStream.filter(x -> x.toString().endsWith(".xml")).forEach(System.out::println);
    }


}
