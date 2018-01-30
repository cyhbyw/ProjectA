package com.cyh.__java.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanhuche on 2/16/2016.
 */
public class ClosedByInterruptExceptionTest {

    public static void main(String[] args) {

        ClosedByInterruptExceptionTest cyh = new ClosedByInterruptExceptionTest();
        try {
            cyh.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run() throws IOException {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new AggregationTrigger(), 0, 2, TimeUnit.SECONDS);
    }


    class AggregationTrigger implements Runnable {
        ExecutorService service = Executors.newSingleThreadExecutor();
        AggregationTask aggregationTask = new AggregationTask();
        Future<?> future;

        @Override
        public void run() {
            System.out.println("future == null  " + (future == null));
            if (future != null) {
                System.out.println("future.isDone() " + (future.isDone()));
                future.cancel(true);
            }

            future = service.submit(aggregationTask);
        }
    }

    class AggregationTask implements Runnable {

        @Override
        public void run() {
            try {
                for (int e=1; /*e <= 1*/; e++) {
                    RandomAccessFile raf = new RandomAccessFile("D:/txt/3.txt", "rw");
                    FileChannel fc = raf.getChannel();
                    int len = (int) (Math.random() * 100);
                    MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 1, len);
                    byte[] bytes = new byte[len];
                    mbb.get(bytes);
//                    System.out.println("==");
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }

        }
    }


}
