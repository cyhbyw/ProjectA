package com.cyh.hash;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ConsistentHashTest {

    @Test
    public void doSelect() {
        List<ConsistentHash.Invoker> invokers = initInvokers();
        Object[] args = {"Hello"};
        ConsistentHash.Invoker invoker = ConsistentHash.doSelect(invokers, args);
        System.out.println(invoker);
    }

    @Test
    public void doSelect2() {
        List<ConsistentHash.Invoker> invokers = initInvokers();
        loopTest(invokers, new Object[]{"A"});
        loopTest(invokers, new Object[]{"B"});
        loopTest(invokers, new Object[]{3});
        loopTest(invokers, new Object[]{4D});
    }

    private void loopTest(List<ConsistentHash.Invoker> invokers, Object[] args) {
        ConsistentHash.Invoker first = null;
        for (int x = 0; x < 5; x++) {
            ConsistentHash.Invoker invoker = ConsistentHash.doSelect(invokers, args);
            if (x == 0) {
                first = invoker;
            } else {
                Assert.assertEquals(first, invoker);
            }
        }
    }

    private List<ConsistentHash.Invoker> initInvokers() {
        List<ConsistentHash.Invoker> invokers = new ArrayList<>();
        invokers.add(new ConsistentHash.Invoker("192.168.91.1"));
        invokers.add(new ConsistentHash.Invoker("192.168.91.2"));
        invokers.add(new ConsistentHash.Invoker("192.168.91.3"));
        return invokers;
    }
}