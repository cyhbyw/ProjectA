package com.cyh.__java.text;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class DecimalFormatDemo {

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.UP);
        for (double d = 0.00; d < 1.11; d += 0.01) {
            System.out.println(df.format(d));
        }
        for (double d = 0.00; d < 1.11; d += 0.1) {
            System.out.println(df.format(d));
        }
    }

}
