package main.cache.datastructureforevictionpolicies;

/**
 * The DoublyLinkedList class represents a doubly linked list data structure.
 * It provides methods to add, remove nodes in the list, as well as utility
 * methods
 * to retrieve the size of the list and check if it is empty.
 */
public class DoublyLinkedList<Key, Value> {

    private final Node<Key, Value> head;
    private final Node<Key, Value> tail;
    private int size;

    /**
     * Constructs an empty DoublyLinkedList with head and tail nodes initialized and
     * pointing each other.
     */
    public DoublyLinkedList() {
        head = new Node<>(null, null);
        tail = new Node<>(null, null);

        head.next = tail;
        tail.next = head;
        size = 0;
    }

    /**
     * Adds a new node after the head node of the list and increments the size by 1.
     *
     * @param newNode The node to be added to the list.
     * @throws RuntimeException if the newNode is null.
     */
    public void addFirst(Node<Key, Value> newNode) {

        if (newNode == null)
            throw new RuntimeException("Node is null");
        newNode.next = head.next;
        newNode.next.prev = newNode;
        head.next = newNode;
        newNode.prev = head;
        size++;

    }

    /**
     * Adds a new node before the tail node of the list and increments the size by
     * 1.
     *
     * @param newNode The node to be added to the list.
     * @throws RuntimeException if the newNode is null.
     */
    public void addLast(Node<Key, Value> newNode) {

        if (newNode == null)
            throw new RuntimeException("Node is null");
        Node<Key, Value> tailPrev = tail.prev;
        tailPrev.next = newNode;
        newNode.next = tail;
        tail.prev = newNode;
        newNode.prev = tailPrev;
        size++;

    }

    /**
     * Removes the first node of the list and decrements the size by 1.
     *
     * @return The key of the removed node.
     * @throws RuntimeException if the list is empty.
     */
    public Key removeFirst() {

        if (isEmpty()) {
            throw new RuntimeException("List is empty");
        }
        Node<Key, Value> headNextNode = head.next;
        Key removedKey = headNextNode.getKey();
        head.next = headNextNode.next;
        headNextNode.next.prev = head;
        size--;
        return removedKey;

    }

    /**
     * Removes the last node of the list and decrements the size by 1.
     *
     * @return The key of the removed node.
     * @throws RuntimeException if the list is empty.
     */
    public Key removeLast() {

        if (isEmpty()) {
            throw new RuntimeException("List is empty");
        }
        Node<Key, Value> tailPrevNode = tail.prev;
        Key removedData = tailPrevNode.getKey();
        tailPrevNode.prev.next = tail;
        tail.prev = tailPrevNode.prev;
        size--;
        return removedData;

    }

    /**
     * Removes the specified node from the list and decrements the size by 1.
     *
     * @param node The node to be removed from the list.
     * @throws RuntimeException if the list is empty or if the input node is null.
     */
    public void remove(Node<Key, Value> node) {

        if (isEmpty()) {
            throw new RuntimeException("List is empty");
        }
        if (node == null) {
            throw new RuntimeException("Node is Null");
        }

        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;

    }

    /**
     * Retrieves the current size of the list.
     *
     * @return The number of nodes currently stored in the list.
     */
    public int getSize() {
        return size;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list contains no nodes, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

}
