package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
    private IntNode sentinel;
    private IntNode SentiNode;
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
        SentiNode = new IntNode(null, null);
        sentinel = new IntNode(item, SentiNode);
        sentinel.next = SentiNode;
        SentiNode.prev = SentiNode;
        SentiNode.next = SentiNode;
        size = 0;
    }
    @Override
    public void addFirst(T item) {
        IntNode node = new IntNode(item, null);
        node.next = SentiNode.next;
        SentiNode.next.prev = node;
        SentiNode.next = node;
        node.prev = SentiNode;
        size += 1;
    }
    @Override
    public void addLast(T item) {
        IntNode node = new IntNode(item, null);
        SentiNode.prev.next = node;
        node.prev = SentiNode.prev;
        SentiNode.prev = node;
        size += 1;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        IntNode p = SentiNode;
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
        T item = SentiNode.next.item;
        SentiNode.next.next.prev = SentiNode;
        SentiNode.next = SentiNode.next.next;
        size -= 1;
        return item;
    }
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = SentiNode.prev.item;
        SentiNode.prev = SentiNode.prev.prev;
        SentiNode.prev.next = SentiNode;
        size -= 1;
        return item;
    }
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int i = 0;
        IntNode p = SentiNode;
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
        if (this.size()!= other.size()) {
            return false;
        }
        for (int i = 0;i < size;i++){
            if (this.get(i) != other.get(i)) {
                return false;
            }
        }
        return true;
    }
}
