package com.cyh.__java.lang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by yanhuche on 3/31/2016.
 */
public class StringTest_PM {


    public static void main(String[] args) throws IOException {

        long st = System.currentTimeMillis();
        new StringTest_PM().run();

        long en = System.currentTimeMillis();
        System.out.println((en - st) + " ms");
    }

    private void run() throws IOException {
        String filePath = "D:/txt/6.txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filePath)));
        String inputData;
        int length = 0;

        while ((inputData = bufferedReader.readLine()) != null) {
            length += encodeString_pm(inputData).length();
        }
        bufferedReader.close();

        System.out.println(length);
    }

    private String encodeString_pm(String inputStrData) {
        String strData = new String(inputStrData);
        strData = replaceString(strData, "&", "&amp;");
        strData = replaceString(strData, "<", "&lt;");
        strData = replaceString(strData, ">", "&gt;");
        strData = replaceString(strData, "'", "&apos;");
        strData = replaceString(strData, "\"", "&quot;");

        return strData;
    }

    private static String replaceString(String strData, String regex, String replacement) {
        int index = strData.indexOf(regex);
        if (index >= 0) {
            StringBuffer strNew = new StringBuffer(16);
            while (index >= 0) {
                strNew.append(strData.substring(0, index)).append(replacement);
                strData = strData.substring(index + regex.length());
                index = strData.indexOf(regex);
            }
            strNew.append(strData);
            return strNew.toString();
        }
        return strData;
    }


}
