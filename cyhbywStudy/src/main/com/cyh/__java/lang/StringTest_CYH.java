package com.cyh.__java.lang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by yanhuche on 3/31/2016.
 */
public class StringTest_CYH {

    private static final char[] NEED_ENCODE_STRING = {'&', '>', '<', '\'', '"'};
    private static final String[] REPLACE_TO_STRING = {"&amp;", "&lt;", "&gt;", "&apos;", "&quot;"};



    public static void main(String[] args) throws IOException {

        long st = System.currentTimeMillis();
        new StringTest_CYH().run();

        long en = System.currentTimeMillis();
        System.out.println((en - st) + " ms");
    }

    private void run() throws IOException {
        String filePath = "D:/txt/6.txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filePath)));
        String inputData;
        int length = 0;

        while ((inputData = bufferedReader.readLine()) != null) {
            length += encodeString_cyh(inputData).length();
        }
        bufferedReader.close();

        System.out.println(length);
    }

    private String encodeString_cyh(String inputStrData) {
        if (!containSpecialCharacter(inputStrData)) {
            return inputStrData;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < inputStrData.length(); i++) {
            int x = 0;
            for (; x < NEED_ENCODE_STRING.length; x++) {
                if (inputStrData.charAt(i) == NEED_ENCODE_STRING[x]) {
                    result.append(REPLACE_TO_STRING[x]);
                    break;
                }
            }
            if (x == NEED_ENCODE_STRING.length) {
                result.append(inputStrData.charAt(i));
            }
        }
        return result.toString();
    }

    private boolean containSpecialCharacter(String inputStrData) {
        for (int i = 0; i < inputStrData.length(); i++) {
            /*if (Character.isLetterOrDigit(inputStrData.charAt(i))) {
                continue;
            }*/
            for (int j = 0; j < NEED_ENCODE_STRING.length; j++) {
                if (inputStrData.charAt(i) == NEED_ENCODE_STRING[j]) {
                    return true;
                }
            }
        }
        return false;
    }


}
