package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
    private IntNode sentinel;
    int size;
    public class IntNode {
        public IntNode prev;
        public T item;
        public IntNode next;
        public IntNode(T i, IntNode n) {
            item = i;
            next = n;
        }
    }
    public LinkedListDeque() {
        T item = null;
        sentinel = new IntNode(item,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    @Override
    public void addFirst(T item) {
        IntNode node = new IntNode(item,null);
        node.next = sentinel.next;
        sentinel.next.prev = node;
        sentinel.next = node;
        node.prev = sentinel;
        size += 1;
    }
    @Override
    public void addLast(T item) {
        IntNode node = new IntNode(item,null);
        sentinel.prev.next = node;
        node.prev = sentinel.prev;
        sentinel.prev = node;
        size += 1;
    }
    @Override
    public int size(){
        return size;
    }
    @Override
    public void printDeque() {
        for (int i = 0;i < size;i++) {
            IntNode p = sentinel;
            System.out.print(p.next.item);
            System.out.print(" ");
            p = p.next;
        }
    }
    @Override
    public T removeFirst() {
        if (size == 0){
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return item;
    }
    @Override
    public T removeLast() {
        if (size == 0){
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return item;
    }
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int i = 0;
        IntNode p = sentinel;
        while(i < index){
            p = p.next;
            i += 1;
        }
        return p.next.item;
    }
    public T getRecursive(int index) {
        int i = 0;
        IntNode p = sentinel;
        if (i == index){
            return p.next.item;
        } else{
            p = p.next;
            return getRecursive(i - 1);
        }
    }

    public Iterator<T> iterator() {
        LinkedListDeque<T> ld = new LinkedListDeque<>();
        return ld.iterator();
    }
    @Override
    public boolean equals(Object o) {
        return o instanceof LinkedListDeque && o.equals(this);
    }
}
