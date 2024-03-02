package main;

import main.cache.Cache;
import main.cache.evictionpolicies.EvictionPolicy;
import main.cache.evictionpolicies.FifoEvictionPolicy;
import main.cache.evictionpolicies.LifoEvictionPolicy;
import main.cache.evictionpolicies.LruEvictionPolicy;

public class MainThreadSafe {
    public static void main(String[] args) throws InterruptedException {

        int cacheCapacity = 2;
        EvictionPolicy<Integer, String> lruEvictionPolicy = new LruEvictionPolicy<>();

        Cache<Integer, String> lruCache = new Cache<>(lruEvictionPolicy, cacheCapacity);

        Thread t1 = new Thread(() -> {
            lruCache.put(1, "One");
            lruCache.put(2, "Two");
        });
        Thread t2 = new Thread(() -> System.out.println("In lru cache Value for key 1: " + lruCache.get(1)));
        Thread t3 = new Thread(() -> {
            lruCache.put(3, "Three");
        });
        Thread t4 = new Thread(() -> System.out.println("In lru cache Value for key 2: " + lruCache.get(2)));
        Thread t5 = new Thread(() -> {
            lruCache.put(4, "Four");
            System.out.println("Value for key 1: " + lruCache.get(1));
        });
        Thread t6 = new Thread(() -> System.out.println("In lru cache Value for key 3: " + lruCache.get(3)));
        Thread t7 = new Thread(() -> System.out.println("In lru cache Value for key 4: " + lruCache.get(4)));

        EvictionPolicy<String, Integer> fifoEvictionPolicy = new FifoEvictionPolicy<>();

        Cache<String, Integer> fifoCache = new Cache<>(fifoEvictionPolicy, 2);

        Thread t8 = new Thread(() -> fifoCache.put("One", 1));
        Thread t9 = new Thread(() -> fifoCache.put("Two", 2));
        Thread t10 = new Thread(
                () -> System.out.println("In fifo cache Value for key One: " + fifoCache.get("One")));
        Thread t11 = new Thread(
                () -> System.out.println("In fifo cache Value for key Two: " + fifoCache.get("Two")));
        Thread t12 = new Thread(() -> fifoCache.put("Three", 3));
        Thread t13 = new Thread(() -> fifoCache.put("Four", 4));

        EvictionPolicy<String, Integer> lifoEvictionPolicy = new LifoEvictionPolicy<>();

        Cache<String, Integer> lifoCache = new Cache<>(lifoEvictionPolicy, 2);

        Thread t14 = new Thread(() -> lifoCache.put("One", 1));
        Thread t15 = new Thread(() -> lifoCache.put("Two", 2));
        Thread t16 = new Thread(() -> System.out.println("In lifo cache Value for key One: " + lifoCache.get("One")));
        Thread t17 = new Thread(() -> System.out.println("In lifo cache Value for key Two: " + lifoCache.get("Two")));
        Thread t18 = new Thread(() -> lifoCache.put("Three", 3));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();
        t12.start();
        t13.start();
        t14.start();
        t15.start();
        t16.start();
        t17.start();
        t18.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            t7.join();

            t8.join();
            t9.join();
            t10.join();
            t11.join();
            t12.join();
            t13.join();

            t14.join();
            t15.join();
            t16.join();
            t17.join();
            t18.join();

            System.out.println("Size of lru cache : " + lruCache.getSize());
            System.out.println("Size of lifo cache : " + lifoCache.getSize());
            System.out.println("Size of fifo cache : " + fifoCache.getSize());
            System.out.println("----------------");
            System.out.println("----------------");

        } catch (InterruptedException e) {
            e.printStackTrace();

        }

    }

}
