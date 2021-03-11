package Tree;

import java.util.Iterator;
import java.util.Queue;

/**
 * The implement of Binary Search Tree
 * A binary search tree (BST) is a binary tree where each node has a Comparable key
 * (and an associated value) and satisfies the restriction that the key in any node
 * is larger than the keys in all nodes in that node's left subtree and smaller
 * than the keys in all nodes in that node's right subtree.
 */
public class BST <Key extends Comparable<Key>, Value>{

    private Node root; // The root pointer

    /**
     * The Node class.
     */
    private class Node {
        private Key key;  // Key
        private Value value;  // Value
        private Node left, right;  // left Node, right Node
        private int N;  // The num of sub Node under this Node

        /**
         * Initialize a Node
         * @param key
         * @param value
         * @param N
         */
        public Node(Key key, Value value, int N) {
            this.key = key;
            this.N = N;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Return the size of this BST.
     * @return
     */
    public int size() {
        return size(this.root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.N;
    }

    /**
     * Find the value of Key key.
     * @param key
     * @return
     */
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else return node.value;
    }

    /**
     * Find the key and update it, if not make a new node.
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        root =  put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) return new Node(key, value, 1);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left =  put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.value = value;
        node.N = size(node.left) + size(node.right) + 1;
        // Update N, since it maybe have a new subtree
        return node;
    }

    /**
     * return the minimum of the tree
     * @return
     */
    public Key min() {
        return min(root).key;
    }

    private Node min(Node node){
        if (node.left == null) return node;
        return min(node.left);
    }

    /**
     * return the maximum of the tree.
     * @return
     */
    public Key max() {
        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null) return node;
        return max(node.right);
    }

    /**
     * Find the largest key in the BST less than or equal to key.
     * @param key
     * @return
     */
    public Key floor(Key key) {
        Node node = floor(root, key);
        if (node == null) return null;
        return node.key;
    }

    private Node floor(Node node, Key key){
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        if (cmp < 0) return floor(node.left, key);
        Node t = floor(node.right, key);
        if (t != null) return t;
        return node;
    }

    public Key ceil(Key key) {
        Node node = ceil(root, key);
        if (node == null) return null;
        return node.key;
    }

    private Node ceil(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        if (cmp > 0) return ceil(node.right, key);
        Node t = ceil(node.left, key);
        if (t == null) return null;
        return node;
    }

    /**
     * Seek the key of rank k (the key such that precisely k other keys in the BST are smaller).
     * @param k
     * @return
     */
    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node node, int k) {
        if (node == null) return null;
        int t = size(node);
        if (t > k) return select(node.left, k);
        else if (t < k) return select(node.right, k);
        else return node;
    }

    /**
     * Rank. If the given key is equal to the key at the root, we return the number of keys t
     * in the left subtree; if the given key is less than the key at the root, we return the
     * rank of the key in the left subtree; and if the given key is larger than the key at
     * the root, we return t plus one (to count the key at the root) plus the rank of the
     * key in the right subtree.
     * @param key
     * @return
     */
    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node node, Key key) {
        if (node == null) return 0;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return rank(node, key);
        else if (cmp > 0) return 1 + size(node.left) + rank(node.right, key);
        else return size(node.left);
    }

    /**
     * Delete the minimum of the tree.
     */
    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * Delete the maximum of the tree.
     */
    public void deleteMax() {
        root = deleteMin(root);
    }

    private Node deleteMax(Node node) {
        if (node.right == null) return node.left;
        node.right = deleteMin(node.right);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * Delete the Node with Key key.
     * @param key
     */
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp > 0) node.right = delete(node.right, key);
        else if (cmp < 0) node.left = delete(node.left, key);
        // Key = Node.key
        else {
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;
            Node temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        node.N = size(node.right) + size(node.left) + 1;
        return node;
    }

    /**
     * Return keys, between low, and high
     * @return
     */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key low, Key high) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, low, high);
        return queue;
    }

    private void keys(Node node, Queue<Key> queue, Key low, Key high) {
        if (node == null) return;
        int cmpLow = low.compareTo(node.key);
        int cmpHigh = high.compareTo(node.key);
        if (cmpLow < 0) keys(node.left, queue, low, high);
        if (cmpLow <= 0 && cmpHigh >= 0) queue.enqueue(node.key);
        if (cmpHigh > 0) keys(node.right, queue, low, high);
    }


}

































