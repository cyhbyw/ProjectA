package com.cyh.__java.text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

public class SimpleDateFormatDemo {

    public static void main(String[] args) {
        String format = "yyyyMMddHHmm";

        int maxTimes = 1000000000;
        while (maxTimes-- > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 12; i++) {
				stringBuilder.append(new Random().nextInt(10));
            }
			try {
				new SimpleDateFormat(format).parse(stringBuilder.toString());
			} catch (ParseException e) {
				e.printStackTrace();
				System.err.println(stringBuilder);
			}
		}

    }

}
