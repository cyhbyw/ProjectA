package com.cyh.__java.nio;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by yanhuche on 2/23/2017.
 */
public class Test {


    public static void main(String[] args) {
        printFileContent(new File("D:/txt/1"));
    }

    private static void printFileContent(File file) {
        try {
            byte[] allBytes = Files.readAllBytes(Paths.get(file.getPath()));
            String content = allBytes.toString();
            System.out.println("cyhbyw_d: content: " + content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
