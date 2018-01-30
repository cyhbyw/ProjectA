package com.cyh.staticFinalInnerClass;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by CYH on 2016/4/28.
 */
public class StaticFinalInnerClassTest {

    private int oneIntValue = 0;

    public void put(String key, Integer value) {
        new ReportCacheProcessor().put(key, value);
    }

    public Integer get(String key) {
        return new ReportCacheProcessor().get(key);
    }

    public void funXX() {}


    private static final class ReportCacheProcessor {
        private static final Map<String, Integer> reportMap = new ConcurrentHashMap<String, Integer>();

        private Integer get(String key) {
            return reportMap.get(key);
        }

        private void put(String key, Integer value) {
            reportMap.put(key, value);
//            oneIntValue--;
//            funXX();
        }

    }

}
