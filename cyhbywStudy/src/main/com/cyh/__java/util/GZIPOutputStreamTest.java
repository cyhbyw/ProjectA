package com.cyh.__java.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by yanhuche on 2/2/2016.
 */
public class GZIPOutputStreamTest {


    public static void main(String[] args) {
        GZIPOutputStreamTest cyh = new GZIPOutputStreamTest();
        try {
            cyh.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run() throws IOException {
        File file = new File("D:/txt/correct.xml.gz");
        OutputStream os = new FileOutputStream(file);
        GZIPOutputStream gzos = new GZIPOutputStream(os);
        gzos.write("<A><B></B></A>".getBytes());
        gzos.flush();
        gzos.finish();
    }


}
