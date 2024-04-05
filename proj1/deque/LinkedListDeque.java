package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
    private IntNode sentinel;
    private IntNode senti_node;
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
        senti_node = new IntNode(null,null);
        sentinel = new IntNode(item,senti_node);
        sentinel.next = senti_node;
        senti_node.prev = senti_node;
        senti_node.next = senti_node;
        size = 0;
    }
    @Override
    public void addFirst(T item) {
        IntNode node = new IntNode(item,null);
        node.next = senti_node.next;
        senti_node.next.prev = node;
        senti_node.next = node;
        node.prev = senti_node;
        size += 1;
    }
    @Override
    public void addLast(T item) {
        IntNode node = new IntNode(item,null);
        senti_node.prev.next = node;
        node.prev = senti_node.prev;
        senti_node.prev = node;
        size += 1;
    }
    @Override
    public int size(){
        return size;
    }
    @Override
    public void printDeque() {
        IntNode p = senti_node;
        for (int i = 0;i < size;i++) {
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
        T item = senti_node.next.item;
        senti_node.next.next.prev = sentinel;
        senti_node.next = senti_node.next.next;
        size -= 1;
        return item;
    }
    @Override
    public T removeLast() {
        if (size == 0){
            return null;
        }
        T item = senti_node.prev.item;
        senti_node.prev = senti_node.prev.prev;
        senti_node.prev.next = senti_node;
        size -= 1;
        return item;
    }
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int i = 0;
        IntNode p = senti_node;
        while(i < index){
            p = p.next;
            i += 1;
        }
        return p.next.item;
    }
    public T getRecursive(int index) {
        int i = 0;
        IntNode p = senti_node;
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
