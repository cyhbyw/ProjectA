package com.cyh.concurrentModificationException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CYH on 2016/3/9.
 */
public class Directories {

    List<String> directoryList = new ArrayList<String>();

    public List<String> getDirectoryList() {
        return directoryList;
    }
}
