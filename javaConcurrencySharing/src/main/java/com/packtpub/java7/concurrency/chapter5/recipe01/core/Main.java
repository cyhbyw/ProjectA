package com.packtpub.java7.concurrency.chapter5.recipe01.core;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import com.packtpub.java7.concurrency.chapter5.recipe01.task.Task;
import com.packtpub.java7.concurrency.chapter5.recipe01.util.Product;
import com.packtpub.java7.concurrency.chapter5.recipe01.util.ProductListGenerator;

/**
 * Main class of the example. It creates a list of products, a ForkJoinPool and 
 * a task to execute the actualization of products. 
 */
public class Main {

    public static void main(String[] args) {
        ProductListGenerator generator = new ProductListGenerator();
        List<Product> products = generator.generate(10000);

        Task task = new Task(products, 0, products.size(), 0.20);

        ForkJoinPool pool = new ForkJoinPool();

        pool.execute(task);

        do {
            System.out.printf("Main: Thread Count: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: Thread Steal: %d\n", pool.getStealCount());
            System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());

        pool.shutdown();

        if (task.isCompletedNormally()) {
            System.out.printf("Main: The process has completed normally.\n");
        }

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getPrice() != 12) {
                System.out.printf("Product %s: %f\n", product.getName(), product.getPrice());
            }
        }

        System.out.println("Main: End of the program.\n");

    }

}
