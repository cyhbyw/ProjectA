package com.cyh.__java.nio;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by yanhuche on 7/6/2016.
 */
public class FilesStudy2 {

    public static void main(String[] args) {
        new FilesStudy2().run();

    }

    private void run() {
        Path sourcePath = Paths.get("/home/omc/a/a.txt");
        Path destPath = Paths.get("/home/omc/b/a.txt");
        try {
            Files.move(sourcePath, destPath);
        }  catch (IOException e) {
            System.out.print("catch2");
            e.printStackTrace();
        }
    }


}
