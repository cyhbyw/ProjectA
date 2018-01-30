package com.cyh.__java.util.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Created by yanhuche on 3/26/2017.
 */
public class ReadContentFromZipFile {


    public static void main(String[] args) throws IOException {
        new ReadContentFromZipFile().run();
    }

    private void run() throws IOException {
        //        unZipFile();
        unZipFile2();
    }

    private void unZipFile2() throws IOException {
        String fileName =
                "D:\\work\\from_lab\\20170324\\fm-access-bc-17.0.0.23-installer\\fm-access-bc-17.0.0.23-installer.zip";
        File file = new File(fileName);
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            System.out.println(entry.getName());
        }

    }


    public void unZipFile() {
        try {
            FileInputStream fis =
                    new FileInputStream(
                            "D:\\work\\from_lab\\20170324\\fm-access-bc-17.0.0.23-installer\\fm-access-bc-17.0.0.23-installer.zip");
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("Extracting: " + entry);
            }
            zis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
