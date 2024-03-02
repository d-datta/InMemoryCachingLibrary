package main.cache.evictionpolicies;

import main.cache.datastructureforevictionpolicies.DoublyLinkedList;
import main.cache.datastructureforevictionpolicies.Node;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The LFUEvictionPolicy class implements the Least Frequently Used (LFU)
 * eviction policy for caching mechanisms.
 * It evicts key-value pairs from the cache based on their least frequency of
 * access.
 */
public class LfuEvictionPolicy<Key, Value> implements EvictionPolicy<Key, Value> {

    private final Map<Key, Integer> keyFrequencyMap;
    private final Map<Key, Node<Key, Value>> linkedListNodeMap;

    private final SortedMap<Integer, DoublyLinkedList<Key, Value>> frequencyMap;

    /**
     * Constructs a new LFUEvictionPolicy with empty maps and a synchronized
     * sortedmap.
     */
    public LfuEvictionPolicy() {
        this.linkedListNodeMap = new ConcurrentHashMap<>();
        this.keyFrequencyMap = new ConcurrentHashMap<>();
        this.frequencyMap = Collections.synchronizedSortedMap(new TreeMap<>());
    }

    /**
     * Retrieves the value associated with the specified key from the cache.
     *
     * @param key The key whose associated value is to be retrieved.
     * @return The value associated with the specified key, or null if the key is
     *         not found.
     */
    @Override
    public Value get(Key key) {
        if (linkedListNodeMap.containsKey(key)) {
            Node<Key, Value> node = linkedListNodeMap.get(key);
            Node<Key, Value> newNode = new Node<>(key, node.getValue());
            int frequency = keyFrequencyMap.get(key);
            frequencyMap.get(frequency).remove(node);
            removeIfListEmpty(frequency);
            linkedListNodeMap.remove(key);
            keyFrequencyMap.remove(key);
            keyFrequencyMap.put(key, frequency + 1);
            linkedListNodeMap.put(key, newNode);
            frequencyMap.computeIfAbsent(frequency + 1, newList -> new DoublyLinkedList<>()).addFirst(newNode);

            return newNode.getValue();
        } else {
            return null;
        }
    }

    /**
     * Stores the specified key-value pair in the cache.
     *
     * @param key   The key to be stored in the cache.
     * @param value The value to be associated with the key and stored in the cache.
     */
    @Override
    public void put(Key key, Value value) {
        Node<Key, Value> newNode = new Node<>(key, value);
        int frequency = 0;
        if (linkedListNodeMap.containsKey(key)) {
            Node<Key, Value> nodeToDelete = linkedListNodeMap.get(key);
            frequency = keyFrequencyMap.get(key);
            frequencyMap.get(frequency).remove(nodeToDelete);
            removeIfListEmpty(frequency);
            keyFrequencyMap.remove(key);
            linkedListNodeMap.remove(key);

        }

        frequencyMap.computeIfAbsent(frequency + 1, newList -> new DoublyLinkedList<>()).addFirst(newNode);
        keyFrequencyMap.put(key, frequency + 1);
        linkedListNodeMap.put(key, newNode);

    }

    /**
     * Evicts a key-value pair from the cache based on the LFU eviction policy.
     *
     * @return The key of the evicted key-value pair.
     */
    @Override
    public Key evict() {
        int minFrequency = this.frequencyMap.firstKey();
        Key evictedKey = frequencyMap.get(minFrequency).removeLast();
        removeIfListEmpty(minFrequency);
        linkedListNodeMap.remove(evictedKey);
        keyFrequencyMap.remove(evictedKey);
        System.out.println("Cache capacity is full, hence removing the the Lfu Key :" + evictedKey);
        return evictedKey;
    }

    /**
     * Removes the frequency list from the frequency map if it becomes empty after
     * eviction.
     * 
     * @param frequency The frequency of access of the key-value pairs.
     */
    private void removeIfListEmpty(int frequency) {
        if (frequencyMap.get(frequency).getSize() == 0) {
            frequencyMap.remove(frequency);
        }
    }

    /**
     * Checks if the key is present in cache or not.
     *
     * @return True if key is present in cache otherwise false.
     */
    @Override
    public boolean isKeyPresent(Key key){
        return linkedListNodeMap.containsKey(key);
    }
}
