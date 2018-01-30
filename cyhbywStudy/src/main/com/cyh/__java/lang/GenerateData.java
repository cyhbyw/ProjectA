package com.cyh.__java.lang;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Created by yanhuche on 6/7/2016.
 */
public class GenerateData {

    private static final char[] NEED_ENCODE_STRING = {'&', '>', '<', '\'', '"'};

    private String string = "";


    public static void main(String[] args) throws IOException {
        new GenerateData().run();
    }

    private void run() throws IOException {
        init();

        String filePath = "D:/txt/6.txt";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(filePath)));
        int maxLineNumber = 500000;

        while (maxLineNumber-- > 0) {
            int lineCharacterNumber = new Random().nextInt(200) + 1;
            StringBuilder oneLineData = new StringBuilder();
            while (lineCharacterNumber-- > 0) {
                int nextInt = new Random().nextInt(string.length());
                oneLineData.append(string.charAt(nextInt));
            }
            bufferedWriter.write(oneLineData.toString());
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }

        bufferedWriter.close();
    }

    private void init() {
        for (int i = 0; i < 26; i++) {
            string += (char) (i + 'a');
        }

        /*for (int i = 0; i < NEED_ENCODE_STRING.length; i++) {
            string += NEED_ENCODE_STRING[i];
        }*/

        System.out.println(string.length());
    }


}
