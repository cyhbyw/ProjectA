package com.cyh.google.guava.concurrency;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * 在使用ListenableFuture前， 最好看下JDK的Future使用
 *
 * @author wenniuwuren
 *
 */
public class ListenableFutureTest {

    public static void main(String[] args) throws InterruptedException {
        jdkFuture();
        guavaFuture();
    }

    private static void jdkFuture() {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> future = executor.submit(() -> 1);
        try {
            Integer count = future.get();
            System.out.println("count: " + count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void guavaFuture() throws InterruptedException {
        // Guava封装后带有执行结束监听任务执行结束的功能
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        ListenableFuture<String> listenableFuture = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("Task done.");
            return "task success ";
        });
//        listenableFuture.addListener(() -> System.out.println("running done"), executorService);

        // 运行成功，将会返回 "task success successfully" 解决了listenableFuture 结束监听版本不能对结果进行操作问题
        FutureCallbackImpl callback = new FutureCallbackImpl();
        // 和计算结果同步运行
        Futures.addCallback(listenableFuture, callback);

        // 如果计算较大， 结果的访问使用异步 将会使用executorService线程去异步执行
        //        Futures.addCallback(listenableFuture, callback, executorService);

        System.out.println(callback.getCallbackResult());
    }
}


class FutureCallbackImpl implements FutureCallback<String> {
    private StringBuilder builder = new StringBuilder();

    @Override
    public void onSuccess(String result) {
        System.out.println("Task already done. Enter onSuccess(). " + new Date());
        builder.append(result).append("successfully");
    }

    @Override
    public void onFailure(Throwable t) {
        builder.append(t.toString());
    }

    public String getCallbackResult() {
        return builder.toString();
    }
}
