package bstmap;

import edu.princeton.cs.algs4.BST;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K,V>{
    private BSTNode root;
    private int size;
    private class BSTNode {
        private BSTNode left;
        private BSTNode right;
        private K key;
        private V value;
        public BSTNode(K key,V value) {
            this.key = key;
            this.value = value;
        }
    }
    @Override
    public void clear() {
        root.left = null;
        root.right = null;
        root.key = null;
        root.value = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        boolean find = false;
        BSTNode search_node = root;
        if (search_node == null) {
            return false;
        }
        while (find != true){
            if (key.equals(search_node.key)) {
                return true;
            } else if (search_node.left != null && key.compareTo(search_node.key) < 0) {
                search_node = search_node.left;
            } else if (search_node.right != null && key.compareTo(search_node.key) > 0){
                search_node =search_node.right;
            } else {
                find = true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        } else {
            boolean find = false;
            BSTNode search_node = root;
            while (find != true){
                if (key.equals(search_node.key)) {
                    return search_node.value;
                } else if (search_node.left != null && key.compareTo(search_node.key) < 0) {
                    search_node = search_node.left;
                } else if (search_node.right != null && key.compareTo(search_node.key) > 0){
                    search_node =search_node.right;
                } else {
                    find = true;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (containsKey(key)) {
        } else if (size == 0) {
            root = new BSTNode(key, value);
            size += 1;
        } else {
            BSTNode new_node = new BSTNode(key, value);
            size += 1;
            BSTNode insert_node = root;
            boolean insert = false;
            while (insert != true) {
                if (insert_node.left == null && key.compareTo(insert_node.key) < 0) {
                    insert_node.left = new_node;
                    insert = true;
                } else if (insert_node.left != null && key.compareTo(insert_node.key) < 0) {
                    insert_node = insert_node.left;
                } else if (insert_node.right == null && key.compareTo(insert_node.key) > 0) {
                    insert_node.right = new_node;
                    insert = true;
                } else if (insert_node.right != null && key.compareTo(insert_node.key) > 0) {
                    insert_node = insert_node.right;
                }
            }
        }
    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }


    @Override
    public Iterator iterator() {
        return null;
    }

    public void printInOrder() {

    }
}
