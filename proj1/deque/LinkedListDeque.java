package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
    private IntNode sentinel;
    private IntNode sentiNode;
    private int size;
    private class IntNode {
        private IntNode prev;
        private T item;
        private IntNode next;
        private IntNode(T i, IntNode n) {
            item = i;
            next = n;
        }
    }
    public LinkedListDeque() {
        T item = null;
        sentiNode = new IntNode(null, null);
        sentinel = new IntNode(item, sentiNode);
        sentinel = sentiNode;
        sentiNode.prev = sentiNode;
        sentiNode.next = sentiNode;
        size = 0;
    }
    @Override
    public void addFirst(T item) {
        IntNode node = new IntNode(item, null);
        node.next = sentiNode.next;
        sentiNode.next.prev = node;
        sentiNode.next = node;
        node.prev = sentiNode;
        size += 1;
    }
    @Override
    public void addLast(T item) {
        IntNode node = new IntNode(item, null);
        sentiNode.prev.next = node;
        node.prev = sentiNode.prev;
        sentiNode.prev = node;
        size += 1;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        IntNode p = sentiNode;
        for (int i = 0; i < size; i++) {
            System.out.print(p.next.item);
            System.out.print(" ");
            p = p.next;
        }
    }
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = sentiNode.next.item;
        if (size == 1) {
            sentiNode.next = sentiNode;
            sentiNode.prev = sentiNode;
            size -= 1;
            return item;
        }
        sentiNode.next.next.prev = sentiNode;
        sentiNode.next = sentiNode.next.next;
        size -= 1;
        return item;
    }
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = sentiNode.prev.item;
        sentiNode.prev = sentiNode.prev.prev;
        sentiNode.prev.next = sentiNode;
        size -= 1;
        return item;
    }
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int i = 0;
        IntNode p = sentiNode;
        while (i < index) {
            p = p.next;
            i += 1;
        }
        return p.next.item;
    }
    public T getRecursive(int index) {
        return null;
    }

    public Iterator<T> iterator() {
        Iterator<T> iter = new Iterator<T>() {
            int i = 0;
            @Override
            public boolean hasNext() {
                return i < size;
            }
            @Override
            public T next() {
                T item = get(i);
                i += 1;
                return item;
            }
        };
        return iter;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (this.get(i) != other.get(i)) {
                return false;
            }
        }
        return true;
    }
}
