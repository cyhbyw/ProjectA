package com.packtpub.java7.concurrency.chapter5.recipe01.task;

import java.util.List;
import java.util.concurrent.RecursiveAction;

import com.packtpub.java7.concurrency.chapter5.recipe01.util.Product;

/**
 * This class implements the tasks that are going to update the
 * price information. If the assigned interval of values is less that 10, it
 * increases the prices of the assigned products. In other case, it divides
 * the assigned interval in two, creates two new tasks and execute them
 */
public class Task extends RecursiveAction {

    private static final long serialVersionUID = 1L;
    private List<Product> products;
    private int first;
    private int last;
    private double increment;

    public Task(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        if (last - first < 10) {
            updatePrices();
        } else {
            int middle = (last + first) >> 1;
            System.out.printf("Task: Pending tasks: %s\n", getQueuedTaskCount());
            Task t1 = new Task(products, first, middle, increment);
            Task t2 = new Task(products, middle, last, increment);
            invokeAll(t1, t2);
        }
    }

    private void updatePrices() {
        for (int i = first; i < last; i++) {
            Product product = products.get(i);
            product.setPrice(product.getPrice() * (1 + increment));
        }
    }

}
