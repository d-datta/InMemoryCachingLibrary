package main.cache.datastructureforevictionpolicies;

/**
 * The Node class represents a node in a doubly linked list.
 */
public class Node<Key, Value> {
    Key key;
    Value value;
    Node<Key, Value> prev;
    Node<Key, Value> next;

    /**
     * Constructs a new node with the specified key and value.
     *
     * @param key   The key of the node.
     * @param value The value of the node.
     */
    public Node(Key key, Value value) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }

    /**
     * Gets the value stored in the node.
     *
     * @return The value stored in the node.
     */
    public Value getValue() {
        return value;
    }

    /**
     * Gets the key stored in the node.
     *
     * @return The key stored in the node.
     */
    public Key getKey() {
        return key;
    }

    /**
     * Setting the key.
     */
    public void setKey(Key key){
        this.key = key;
    }

    /**
     * Setting the value.
     */
    public void setValue(Value value){
        this.value = value;
    }


}
