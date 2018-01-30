package com.cyh.__java.nio;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanhuche on 5/25/2016.
 */
public class FilesStudy {

    private static final EnumSet<FileVisitOption> FILE_VISIT_OPTIONS = EnumSet.allOf(FileVisitOption.class);

    private final String rootDirPath = "/home/omc/cyh/cyh";

    public static void main(String[] args) throws IOException {
        FilesStudy cyh = new FilesStudy();
        cyh.run();
    }

    private void run() throws IOException {
        Path startPath = Paths.get(rootDirPath);
        MyVisit visit = new MyVisit();
        Files.walkFileTree(startPath, FILE_VISIT_OPTIONS, 2, visit);
        System.out.println("a " + visit.getCount());
    }

    class MyVisit extends SimpleFileVisitor<Path> {

        int count = 0;

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            System.out.println("file: getFileName " + file.getFileName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            return super.visitFile(file, attrs);
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            System.out.println("dir getFileName(): " + dir.getFileName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return super.preVisitDirectory(dir, attrs);
        }

        public int getCount() {
            return count;
        }
    }

}
