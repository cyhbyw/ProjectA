package com.cyh.google.guava.cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Created by yanhuche on 6/21/2016.
 */
public class LoadingCacheTest {


    @Test
    public void test_a() {
        LoadingCache<Integer, Double> cache =
                CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(10, TimeUnit.MINUTES)
                        .build(new CacheLoader<Integer, Double>() {
                            @Override
                            public Double load(Integer key) throws InterruptedException {
                                System.out.println("==key " + key);
                                TimeUnit.SECONDS.sleep(2);
                                return Math.random() * key;
                            }
                        });
        try {
            System.out.println(cache.get(3));
            System.out.println(cache.get(3));
            System.out.println(cache.get(3));
            System.out.println(cache.get(4));
            System.out.println(cache.get(4));
            System.out.println(cache.get(4));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}
