package com.cyh.__java.nio;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * Created by yanhuche on 5/20/2016.
 */
public class PathStudy {

    public static void main(String[] args) {
        new PathStudy().run();
    }

    private void run() {
        Path path = Paths.get("/home", "ftirpuser4", "pm");

        System.out.println("getFileName: " + path.getFileName());

        int pathNameCount = path.getNameCount();
        for (int pathIndex = 0; pathIndex < pathNameCount; pathIndex++) {
            System.out.printf("path.getName(%d) %s\n", pathIndex, path.getName(pathIndex));
        }

        System.out.println("getParent: " + path.getParent());

        System.out.println("getRoot: " + path.getRoot());

        Iterator<Path> pathIterator = path.iterator();
        while (pathIterator.hasNext()) {
            System.out.println("next: " + pathIterator.next());
        }

        System.out.println("resolve: " + path.getName(0).resolve(path.getName(1)));
        System.out.println("resolve: " + path.resolve(Paths.get("subDir")));
        System.out.println("resolve: " + path.resolve("subDir"));
        System.out.println("resolve: " + path.resolve("subDir"));
        System.out.println("resolve: " + path.resolve("subDir"));

        System.out.println("resolveSibling: " + path.resolveSibling(Paths.get("sibling")));
        System.out.println("resolveSibling: " + path.resolveSibling("sibling"));

        System.out.println("startsWith 1: " + path.startsWith(Paths.get("/home")));
        System.out.println("startsWith 2: " + path.startsWith(Paths.get("home")));
        System.out.println("startsWith 3: " + path.startsWith("/home"));
        System.out.println("startsWith 4: " + path.startsWith("home"));

        System.out.printf("subpath(%d, %d): %s\n", 0, 2, path.subpath(0, 2));

        System.out.println("getRoot_subpath: " + path.getRoot().resolve(path.subpath(0, 2)));


    }

}
