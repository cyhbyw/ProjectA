package com.cyh.__java.io;

import java.io.File;
import java.nio.file.Paths;

/**
 * Created by yanhuche on 4/7/2016.
 */
public class FileTest {



    public static void main(String[] args) {
        System.out.println(new File("D:/txt/rename.txt").renameTo(new File("D:/txt/ren ame.txt")));

        System.out.println(Paths.get("/home", "//ftirpuser/", "\\pm/hostname\\\\rename.txt"));
    }

}
