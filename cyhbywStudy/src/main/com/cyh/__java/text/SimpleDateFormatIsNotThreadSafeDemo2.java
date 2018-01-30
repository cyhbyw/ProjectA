package com.cyh.__java.text;



import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by yanhuche on 6/7/2016.
 */
public class SimpleDateFormatIsNotThreadSafeDemo2 {

    public static void main(String[] args) throws Exception {
        new SimpleDateFormatIsNotThreadSafeDemo2().testUnThreadSafe();


    }


    public void testUnThreadSafe() throws Exception {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,S");

        final String[] dateStrings =
                {"2014-04-30 18:51:01,61", "2014-04-30 18:51:01,461", "2014-04-30 18:51:01,361",
                        "2014-04-30 18:51:01,261", "2014-04-30 18:51:01,161",};
        int threadNum = 5;
        Thread[] parseThreads = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            parseThreads[i] = new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < dateStrings.length; j++) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " " + sdf.parse(dateStrings[j]));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            parseThreads[i].start();
        }

        for (int i = 0; i < threadNum; i++) {
            parseThreads[i].join();
        }
    }


}
