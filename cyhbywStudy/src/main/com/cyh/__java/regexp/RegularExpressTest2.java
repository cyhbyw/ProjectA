package com.cyh.__java.regexp;

/**
 * Created by yanhuche on 6/4/2016.
 */
public class RegularExpressTest2 {

    static String[] strings =
            {
                    "at org.springframework.web.client.RestTemplate.doExecute(RestTemplate.java:515)",
                    "at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)",
                    "at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.access$301(ScheduledThreadPoolExecutor.java:180)"};

    public static void main(String[] args) {
        String regexpString = "at [[a-zA-Z\\$\\d]+\\.]+[a-zA-Z\\$\\d]+\\([a-zA-Z\\$\\d]+\\.java:\\d+\\)";

        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i].matches(regexpString));
        }


        System.out.println("a.b.c( $d".matches("a\\.b\\.c\\( \\$d"));
    }


}
