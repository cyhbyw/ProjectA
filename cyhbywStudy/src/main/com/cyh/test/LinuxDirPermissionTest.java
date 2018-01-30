package com.cyh.test;

import java.io.File;

/**
 * Created by yanhuche on 1/14/2016.
 */
public class LinuxDirPermissionTest {


    public static void main(String[] args) {
        String path = "/home//omc/cyh/aa/bb/cc//dd";
        new File(path).mkdirs();
    }

}
