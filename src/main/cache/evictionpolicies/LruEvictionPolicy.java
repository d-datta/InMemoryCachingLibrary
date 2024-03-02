package main.cache.evictionpolicies;

import main.cache.datastructureforevictionpolicies.DoublyLinkedList;
import main.cache.datastructureforevictionpolicies.Node;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The LRUEvictionPolicy class implements the Least Recently Used (LRU) eviction
 * policy for caching mechanisms.
 * It evicts key-value pairs from the cache based on their least recently used
 * order, with the least recently used
 * item being evicted first.
 */
public class LruEvictionPolicy<Key, Value> implements EvictionPolicy<Key, Value> {

    private final DoublyLinkedList<Key, Value> doublyLinkedList;
    private final Map<Key, Node<Key, Value>> linkedListNodeMap;

    /**
     * Constructs a new LRUEvictionPolicy with an empty doubly linked list and a
     * ConcurrentHashMap.
     */
    public LruEvictionPolicy() {
        this.doublyLinkedList = new DoublyLinkedList<>();
        this.linkedListNodeMap = new ConcurrentHashMap<>();
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
            doublyLinkedList.remove(node);
            doublyLinkedList.addFirst(node);
            return node.getValue();
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
        if (linkedListNodeMap.containsKey(key)) {
            doublyLinkedList.remove(linkedListNodeMap.get(key));
            linkedListNodeMap.remove(key);
        }

        Node<Key, Value> newNode = new Node<>(key, value);
        doublyLinkedList.addFirst(newNode);
        linkedListNodeMap.put(key, newNode);
    }

    /**
     * Evicts a key-value pair from the cache based on the LRU eviction policy.
     * 
     * @return The key of the evicted key-value pair.
     */
    @Override
    public Key evict() {
        Key evictedKey = doublyLinkedList.removeLast();
        linkedListNodeMap.remove(evictedKey);
        System.out.println("Cache capacity is full, hence removing the the Least Recently Used Key :" + evictedKey);
        return evictedKey;
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
