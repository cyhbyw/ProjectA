package com.cyh.streams;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Created by yanhuche on 3/29/2017.
 */
public class ReadFileLineMain {

    public static void main(String[] args) throws IOException {
        new ReadFileLineMain().run();
    }

    private void run() throws IOException {
        String filename = "/txt/2.txt";
        final Path path = new File(filename).toPath();
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            lines.onClose(() -> System.out.println("Done!")).forEach(System.out::println);
        }
    }


}
