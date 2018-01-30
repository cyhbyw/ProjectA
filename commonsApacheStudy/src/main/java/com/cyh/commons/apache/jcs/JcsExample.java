package com.cyh.commons.apache.jcs;

import java.io.Serializable;
import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;
import org.apache.commons.jcs.access.exception.CacheException;

public class JcsExample {

    public static void main(String[] args) {
        JcsExample example = new JcsExample();
        example.testCache();
    }

    private CacheAccess<String, City> cache = null;

    public JcsExample() {
        try {
            cache = JCS.getInstance("default");
        } catch (CacheException e) {
            System.out.println(String.format("Problem initializing cache: %s", e.getMessage()));
        }
    }

    public void testCache() {
        City zurich = new City("Zürich", "Switzerland", 366765);
        putInCache(zurich);

        City berlin = new City("Berlin", "Germany", 3502000);
        putInCache(berlin);

        City johannesburg = new City("Johannesburg", "South Africa", 12200000);
        putInCache(johannesburg);

        City retrievedCity1 = retrieveFromCache("Berlin");
        if (retrievedCity1 != null) {
            System.out.println(retrievedCity1.toString());
        } else {
            System.out.println("No object was found in the cache for the key \"Berlin\"");
        }

        City retrievedCity2 = retrieveFromCache("New York");
        if (retrievedCity2 != null) {
            System.out.println(retrievedCity2.toString());
        } else {
            System.out.println("No object was found in the cache for the key \"New York\"");
        }
    }

    public void putInCache(City city) {
        String key = city.name;
        try {
            cache.put(key, city);
        } catch (CacheException e) {
            System.out.println(String.format("Problem putting city %s in the cache, for key %s%n%s", city.name, key,
                    e.getMessage()));
        }
    }

    public City retrieveFromCache(String cityKey) {
        return cache.get(cityKey);
    }

    // defined as a nested inner class to reduce number of .java files in the example
    public class City implements Serializable {
        private static final long serialVersionUID = 6392376146163510146L;
        public String name;
        public String country;
        public int population;

        public City(String name, String country, int population) {
            this.name = name;
            this.country = country;
            this.population = population;
        }

        @Override
        public String toString() {
            return String.format("%s is a city in the country %s with a population of %d", name, country, population);
        }
    }
}
