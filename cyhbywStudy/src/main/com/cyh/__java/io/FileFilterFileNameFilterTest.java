package com.cyh.__java.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

/**
 * Created by yanhuche on 4/1/2016.
 */
public class FileFilterFileNameFilterTest {



    public static void main(String[] args) {
        FileFilterFileNameFilterTest cyh = new FileFilterFileNameFilterTest();
        cyh.run();
    }

    private void run() {
        File rootFile = new File("D:\\eclipseWPlace_ALL\\prac\\snmp4jSrcStudy\\src\\org\\snmp4j");

        File[] files = rootFile.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                System.out.println("pathname: " + pathname);
                return pathname.getName().endsWith("java");
            }
        });
        printout(files);

        File[] files2 = rootFile.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                System.out.println("dir: " + dir + ", name: " + name);
                return name.endsWith("java");
            }
        });
        printout(files2);
    }

    private void printout(File[] files) {
        StringBuilder sb = new StringBuilder();
        for (File file : files){
            sb.append(file.getName()).append("\n");
        }
        System.out.println(sb.toString() + "---------------\n\n");
    }


}
