package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {

    private int N = 8;
    private T[] items = (T []) new Object[N];
    private int front = 0;
    private int rear = 0;
    private int size = 0;
    public ArrayDeque() {
        T[] newitem = (T []) new Object[N];
    }
    public void resize(int x) {
        T[] a = (T []) new Object[x];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }
    @Override
    public void addFirst(T item) {
        if (size == 0) {
            items[front] = item;
            rear += 1;
            size += 1;
        } else if (size < items.length) {
            front = (front - 1 + N) % N;
            items[front] = item;
            size += 1;
        } else {
            resize(size * 2);
            items[size] = item;
            size += 1;
        }
    }
    @Override
    public void addLast(T item) {
        if (size == 0) {
            items[rear] = item;
            rear += 1;
            size += 1;
        } else if (size < items.length) {
            items[rear] = item;
            rear = (rear + 1 + N) % N;
            size += 1;
        } else {
            resize(size * 2);
            items[size] = item;
            size += 1;
        }
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        for (int i = front; i < size + front; i++) {
            System.out.print(items[i % N]);
            System.out.print(" ");
        }
    }
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T item = items[front];
            front = (front + 1) % N;
            size -= 1;
            double len = items.length;
            if (size / len <= 0.5) {
                resize(items.length / 2);
            }
            return item;
        }
    }
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            rear = (rear - 1 + N) % N;
            T item = items[rear];
            size -= 1;
            double len = items.length;
            if (size / len <= 0.25) {
                resize(items.length / 2);
            }
            return item;
        }
    }
    @Override
    public T get(int index) {
        if (size == 0) {
            return null;
        }
        return items[(index + front) % N];
    }

    public Iterator<T> iterator() {
        return new aditerator();
    }
    private class aditerator implements Iterator<T>{
        int i = 0;
        public boolean hasNext(){
            return i < size;
        }
        public T next(){
            T item = get(i);
            i += 1;
            return item;
        }
    }
    @Override
    public boolean equals(Object o) {
        return o instanceof ArrayDeque && o.equals(this);
    }
}
