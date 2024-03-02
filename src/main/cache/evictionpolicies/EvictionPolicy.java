package main.cache.evictionpolicies;

/**
 * The EvictionPolicy interface defines the methods for eviction policies used
 * in caching mechanisms.
 * It specifies methods for retrieving, storing, and evicting key-value pairs
 * from the cache.
 */
public interface EvictionPolicy<Key, Value> {

    /**
     * Retrieves the value associated with the specified key from the cache.
     * 
     * @param key The key whose associated value is to be retrieved.
     * @return The value associated with the specified key, or null if the key is
     *         not found.
     */
    Value get(Key key);

    /**
     * Stores the specified key-value pair in the cache.
     * 
     * @param key   The key to be stored in the cache.
     * @param value The value to be associated with the key and stored in the cache.
     */
    void put(Key key, Value value);

    /**
     * Evicts a key-value pair from the cache based on the eviction policy.
     * 
     * @return The key of the evicted key-value pair.
     */
    Key evict();

    /**
     * Checks if the key is present in cache or not.
     *
     * @return True if key is present in cache otherwise false.
     */
    boolean isKeyPresent(Key key);

}
