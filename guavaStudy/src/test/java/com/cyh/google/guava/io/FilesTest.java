package com.cyh.google.guava.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.TreeTraverser;
import com.google.common.io.Files;

/**
 * Created by yanhuche on 6/17/2016.
 */
public class FilesTest {

    @Test
    public void test_fileTreeTraverser() {
        String rootFilePath = "D:/packages";
        File rootFile = new File(rootFilePath);
        TreeTraverser<File> fileTreeTraverser = Files.fileTreeTraverser();
        FluentIterable<File> preOrderIterable = fileTreeTraverser.preOrderTraversal(rootFile);
        printOut(preOrderIterable);
        FluentIterable<File> postOrderTraversal = fileTreeTraverser.postOrderTraversal(rootFile);
        printOut(postOrderTraversal);
    }

    private void printOut(FluentIterable<File> fluentIterable) {
        for (File oneFile : fluentIterable) {
            System.out.println(oneFile);
        }
        System.out.println("=====\n\n");
    }

    @Test
    public void test_readFirstLine() throws IOException {
        String rootFilePath = "D:/packages";
        File rootFile = new File(rootFilePath);

        String firstLine = Files.readFirstLine(rootFile, Charset.defaultCharset());

    }

    @Test
    public void test_copy() {
        File fromFile = new File("/txt/6");
        File toFile = new File("/home/omc/cyh/a/b/c/d/6");
        try {
            Files.copy(fromFile, toFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_readLines() throws IOException {
        List<String> lines = Files.readLines(new File("D:/txt/4"), Charset.defaultCharset());
        System.out.println("--\"" + lines + "\"--");
    }


}
