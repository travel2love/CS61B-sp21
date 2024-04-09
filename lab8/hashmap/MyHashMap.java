package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private double maxLoadFactor = 0.75;
    private double loadfactor;
    private int N = 16;
    private int size = 0;

    /**
     * Constructors
     */
    public MyHashMap() {
        int initialSize = 16;
        buckets = new Collection[initialSize];
        N = initialSize;
    }

    public MyHashMap(int initialSize) {
        buckets = new Collection[initialSize];
        N = initialSize;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad     maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        buckets = new Collection[initialSize];
        maxLoadFactor = maxLoad;
        N = initialSize;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     * <p>
     * The only requirements of a hash table bucket are that we can:
     * 1. Insert items (`add` method)
     * 2. Remove items (`remove` method)
     * 3. Iterate through items (`iterator` method)
     * <p>
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     * <p>
     * Override this method to use different data structures as
     * the underlying bucket type
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return buckets;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    @Override
    public void clear() {
        buckets = new Collection[16];
        size = 0;
        loadfactor = 0;
        maxLoadFactor = 0;
        N = 16;
    }

    @Override
    public boolean containsKey(K key) {
        if (buckets[(key.hashCode() % N + N) % N] == null) {
            return false;
        } else {
            for (int i = 0; i < buckets[(key.hashCode() % N + N) % N].size(); i++) {
                Iterator<Node> iterator = buckets[(key.hashCode() % N + N) % N].iterator();
                while (iterator.hasNext()) {
                    if (iterator.next().key.equals(key)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @Override
    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        } else {
            for (int i = 0; i < buckets[(key.hashCode() % N + N) % N].size(); i++) {
                Iterator<Node> iterator = buckets[(key.hashCode() % N + N) % N].iterator();
                while (iterator.hasNext()) {
                    Node node = iterator.next();
                    K k = node.key;
                    V v = node.value;
                    if (k.equals(key)) {
                        return v;
                    }
                }
            }
            return null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (containsKey(key)) {
            if (value != get(key)) {
                Iterator<Node> iterator = buckets[(key.hashCode() % N + N) % N].iterator();
                while (iterator.hasNext()) {
                    Node old_node = iterator.next();
                    if (old_node.key == key) {
                        old_node.value = value;
                    }
                }
            }
        } else {
            int bucket_number = (key.hashCode() % N + N) % N;
            Node node = new Node(key, value);
            if (buckets[bucket_number] == null) {
                buckets[bucket_number] = createBucket();
            }
            size += 1;
            buckets[bucket_number].add(node);
            double size_for_compute = size;
            loadfactor = size_for_compute / N;
            if (loadfactor > maxLoadFactor) {
                MyHashMap<K, V> new_map = new MyHashMap<K, V>(2 * N, maxLoadFactor);
                for (int i = 0; i < N; i++) {
                    if (buckets[i] == null) {
                        continue;
                    }
                    for (int j = 0; j < buckets[i].size(); j++) {
                        Iterator<Node> iterator = buckets[i].iterator();
                        while (iterator.hasNext()) {
                            Node new_node = iterator.next();
                            K k = new_node.key;
                            V v = new_node.value;
                            new_map.put(k, v);
                        }
                    }
                }
                this.N = new_map.N;
                this.size = new_map.size;
                this.buckets = new_map.buckets;
            }

        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            if (buckets[i] == null) {
                continue;
            }
            for (int j = 0; j < buckets[i].size(); j++) {
                Iterator<Node> iterator = buckets[i].iterator();
                while (iterator.hasNext()) {
                    Node new_node = iterator.next();
                    K k = new_node.key;
                    set.add(k);
                }
            }
        }
        return set;
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
    public Iterator<K> iterator() {

        return new HashIterator();
    }

    private class HashIterator implements Iterator<K> {
        int i = 0;

        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        public K next() {
            for (int i = 0; i < N; i++) {
                if (buckets[i] == null) {
                    continue;
                }
                for (int j = 0; j < buckets[i].size(); j++) {
                    Iterator<Node> iterator = buckets[i].iterator();
                    while (iterator.hasNext()) {
                        Node new_node = iterator.next();
                        return new_node.key;
                    }
                }
            }
            return null;
        }
    }
}
