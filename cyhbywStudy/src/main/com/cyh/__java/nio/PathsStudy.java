package com.cyh.__java.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by yanhuche on 7/19/2016.
 */
public class PathsStudy {

    public static void main(String[] args) {
        new PathsStudy().run();
    }

    private void run() {
        Path path = Paths.get("/home", "", "", "omc", "cyh");
        System.out.printf("%s %s\n", path, path.toString());
    }


}
