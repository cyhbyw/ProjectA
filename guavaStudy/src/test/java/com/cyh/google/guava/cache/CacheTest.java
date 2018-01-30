package com.cyh.google.guava.cache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * Created by yanhuche on 6/21/2016.
 */
public class CacheTest {

    // look Ma, no CacheLoader
    private Cache<Integer, Double> cache = CacheBuilder.newBuilder().maximumSize(1000).build();


    @Test
    public void test_a() {
        System.out.println(retrieveValue(1));
        System.out.println(retrieveValue(1));
        System.out.println(retrieveValue(1));
        System.out.println(retrieveValue(2));
        System.out.println(retrieveValue(2));
        System.out.println(retrieveValue(2));
    }

    private Double retrieveValue(final int key) {
        try {
            // If the key wasn't in the "easy to compute" group, we need to do things the hard way.
            return cache.get(key, new Callable<Double>() {
                @Override
                public Double call() throws InterruptedException {
                    System.out.println("key " + key);
                    TimeUnit.SECONDS.sleep(2);
                    return Math.random();
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


}
