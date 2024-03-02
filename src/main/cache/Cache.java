package main.cache;

import main.cache.evictionpolicies.EvictionPolicy;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * The Cache class represents an in-memory caching system that stores key-value
 * pairs.
 * It utilizes an eviction policy to manage the cache's contents and ensure it
 * stays within its capacity.
 * The cache supports concurrent access and provides thread-safe operations.
 */
public class Cache<Key, Value> {
    private final int maxSize;
    private int size;
    private final EvictionPolicy<Key, Value> evictionPolicy;
    private final ReentrantReadWriteLock lock;

    /**
     * Constructs a new Cache with the specified eviction policy and capacity.
     * 
     * @param evictionPolicy The eviction policy to be used for managing cache
     *                       entries.
     * @param capacity       The maximum capacity of the cache.
     */
    public Cache(EvictionPolicy<Key, Value> evictionPolicy, int capacity) {
        this.evictionPolicy = evictionPolicy;
        this.maxSize = capacity;
        this.size = 0;
        this.lock = new ReentrantReadWriteLock();
    }

    /**
     * Retrieves the value associated with the specified key from the cache.
     * 
     * @param key The key whose associated value is to be retrieved.
     * @return The value associated with the specified key, or null if the key is
     *         not found in the cache.
     */
    public Value get(Key key) {
        this.lock.readLock().lock();
        try {
            return evictionPolicy.get(key);
        } catch (Exception e) {
            return null;
        } finally {
            this.lock.readLock().unlock();
        }
    }

    /**
     * Stores the specified key-value pair in the cache.
     * If the cache is full, the eviction policy is invoked to make space for the
     * new entry
     * 
     * @param key   The key with which the specified value is to be associated.
     * @param value The value to be associated with the specified key.
     */
    public void put(Key key, Value value) {
        this.lock.writeLock().lock();
        try {
            if (isCacheFull() && !this.evictionPolicy.isKeyPresent(key)) {
                //if the cache capacity is full and the key is new key , not present in our cache then
                // only eviction method will be called and if the key is already present in cache then
                // the key value will be updated
                Key evictedKey = this.evictionPolicy.evict();
                this.size--;
            }
            this.evictionPolicy.put(key, value);
            this.size++;
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    /**
     * Retrieves the current size of the cache.
     * 
     * @return The number of key-value pairs currently stored in the cache.
     */
    public int getSize() {
        return size;
    }

    /**
     * Checks if the cache is full or not.
     *
     * @return True if cache is full otherwise false .
     */
    public boolean isCacheFull() {
        return this.size >= this.maxSize;
    }
}
