package com.cyh.commons.apache.io;

import com.google.common.io.Files;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yanhuche on 6/17/2016.
 */
public class FileUtilsTest {

    Queue<String> queue = new LinkedList<String>();


    @Test
    public void test_() {
        /*
         * The underlying realizing code uses "listFiles". And it will list all sub-directory recursively.
         */
        FileUtils.iterateFilesAndDirs(null, null, null);
        FileUtils.listFilesAndDirs(null, null, null);
    }

    @Test
    public void test_readFileToString() throws IOException {
        String filePath = SystemUtils.USER_DIR + "/test.txt";
        File testFile = new File(filePath);
        FileUtils.writeStringToFile(testFile, "data\r");

        String data = FileUtils.readFileToString(testFile);
        System.out.printf("==%s==\n", data);

        data = Files.readFirstLine(testFile, Charset.defaultCharset());
        System.out.printf("==%s==\n", data);
    }


}
