package main;

import main.cache.Cache;

import main.cache.evictionpolicies.*;

class Main {
    public static void main(String[] args) {

        checkFIFOEvictionPolicy();
        checkLIFOEvictionPolicy();
        checkLruEvictionPolicy();
        checkLFUEvictionPolicy();

    }

    private static void checkLruEvictionPolicy() {
        System.out.println("**** LRU Eviction Policy ****");
        System.out.println();

        // Create a cache with LRU eviction policy and capacity of 2
        EvictionPolicy<Integer, String> lruEvictionPolicy = new LruEvictionPolicy<>();
        Cache<Integer, String> lruCache = new Cache<>(lruEvictionPolicy, 2);

        // Put key-value pairs into the cache
        System.out.println("Adding key 1 and value One to the cache");
        lruCache.put(1, "One");
        System.out.println("Adding key 2 and value Two to the cache");
        lruCache.put(2, "Two");

        // Retrieve and print values from the cache
        System.out.println("Value for key 1: " + lruCache.get(1)); // Expected: One

        System.out.println("Adding key 3 and value Three to the cache");
        lruCache.put(3, "Three"); // cache is full , 2 will be evicted from the cache as 2 is LRU

        System.out.println("Value for key 2: " + lruCache.get(2)); // Expected: null

        System.out.println("Adding key 4 and value Four to the cache");
        lruCache.put(4, "Four");

        System.out.println("Value for key 1: " + lruCache.get(1)); // Expected: null
        System.out.println("Value for key 3: " + lruCache.get(3)); // Expected: Three
        System.out.println("Value for key 4: " + lruCache.get(4)); // Expected: Four

        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println();
        System.out.println();

    }

    private static void checkLFUEvictionPolicy() {
        System.out.println("**** LFU Eviction Policy ****");
        System.out.println();

        // Create a cache with LFU eviction policy and capacity of 2
        EvictionPolicy<Integer, Integer> lfuEvictionPolicy = new LfuEvictionPolicy<>();
        Cache<Integer, Integer> lfuCache = new Cache<>(lfuEvictionPolicy, 2);

        // Put key-value pairs into the cache
        System.out.println("Adding key 1 and value 1 to the cache");
        lfuCache.put(1, 1);
        System.out.println("Adding key 2 and value 2 to the cache");
        lfuCache.put(2, 2);

        // Retrieve and print values from the cache
        System.out.println("Value for key 1: " + lfuCache.get(1)); // Expected: 1

        System.out.println("Adding key 3 and value 3 to the cache");
        lfuCache.put(3, 3);

        System.out.println("Value for key 2: " + lfuCache.get(2)); // Expected: null
        System.out.println("Value for key 3: " + lfuCache.get(3)); // Expected: 3
        // Add more entries to the cache

        System.out.println("Adding key 4 and value 4 to the cache");
        lfuCache.put(4, 4);

        // Retrieve and print values from the cache
        System.out.println("Value for key 1: " + lfuCache.get(1)); // Expected: null
        System.out.println("Value for key 3: " + lfuCache.get(3)); // Expected: 3
        System.out.println("Value for key 4: " + lfuCache.get(4)); // Expected: 4

        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println();
        System.out.println();

    }

    private static void checkFIFOEvictionPolicy() {
        System.out.println("**** FIFO Eviction Policy ****");
        System.out.println();

        // Create a cache with FIFO eviction policy and capacity of 2
        EvictionPolicy<String, Integer> fifoEvictionPolicy = new FifoEvictionPolicy<>();
        Cache<String, Integer> fifoCache = new Cache<>(fifoEvictionPolicy, 2);

        // Put key-value pairs into the cache
        System.out.println("Adding key One and value 1 to the cache");
        fifoCache.put("One", 1);
        System.out.println("Adding key Two and value 2 to the cache");
        fifoCache.put("Two", 2);

        // Retrieve and print values from the cache
        System.out.println("Value for key One: " + fifoCache.get("One")); // Expected: 1
        System.out.println("Value for key Two: " + fifoCache.get("Two")); // Expected: 2

        System.out.println("Adding key Three and value 3 to the cache");
        fifoCache.put("Three", 3);
        System.out.println("Adding key Four and value 4 to the cache");
        fifoCache.put("Four", 4);

        System.out.println("Value for key One: " + fifoCache.get("One")); // Expected: null
        System.out.println("Value for key Two: " + fifoCache.get("Two")); // Expected: null
        System.out.println("Value for key Three: " + fifoCache.get("Three")); // Expected: 3
        System.out.println("Value for key Four: " + fifoCache.get("Four")); // Expected: 4

        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println();
        System.out.println();
    }

    private static void checkLIFOEvictionPolicy() {
        System.out.println("**** LIFO Eviction Policy ****");
        System.out.println();

        // Create a cache with LIFO eviction policy and capacity of 2
        EvictionPolicy<Integer, Integer> lifoEvictionPolicy = new LifoEvictionPolicy<>();
        Cache<Integer, Integer> lifoCache = new Cache<>(lifoEvictionPolicy, 2);

        // Put key-value pairs into the cache
        System.out.println("Adding key 1 and value 1 to the cache");
        lifoCache.put(1, 1);
        System.out.println("Adding key 2 and value 2 to the cache");
        lifoCache.put(2, 2);

        // Retrieve and print values from the cache
        System.out.println("Value for key 1: " + lifoCache.get(1)); // Expected: 1
        System.out.println("Value for key 2: " + lifoCache.get(2)); // Expected: 2

        // Add more entries to the cache
        System.out.println("Adding key 3 and value 3 to the cache");
        lifoCache.put(3, 3);
        System.out.println("Adding key 4 and value 4 to the cache");
        lifoCache.put(4, 4);

        // Retrieve and print values from the cache
        System.out.println("Value for key 1: " + lifoCache.get(1)); // Expected: 1
        System.out.println("Value for key 2: " + lifoCache.get(2)); // Expected: null
        System.out.println("Value for key 3: " + lifoCache.get(3)); // Expected: null
        System.out.println("Value for key 4: " + lifoCache.get(4)); // Expected: 4

        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println();
        System.out.println();
    }
}