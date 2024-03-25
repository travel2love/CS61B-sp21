package deque;

public class ArrayDeque<T> {

    int N = 8;
    T[] items = (T []) new Object[N];
    int front = 0;
    int rear = 0;
    int size = 0;
    int RFACTOR = 2;
    public ArrayDeque(){
    }
    public void resize(int x){
        T[] a = (T []) new Object[size * RFACTOR];;

    }
    public void addFirst(T item){
        if (size == 0){
            items[front] = item;
            rear += 1;
            size += 1;
        } else if (size < N) {
            front = (front - 1 + N) % N;
            items[front] = item;
            size += 1;
        } else {
            resize(size* RFACTOR);
            items[size * RFACTOR] = item;
            size += 1;
        }
    }
    public void addLast(T item){
        if (size == 0){
            items[rear] = item;
            rear += 1;
            size += 1;
        } else if (size < N) {
            items[rear] = item;
            rear = (rear + 1 + N) % N;
            size += 1;
        } else {
            resize(size* RFACTOR);
            items[size] = item;
            size += 1;
        }
    }
    public boolean isEmpty(){
        if (size == 0){
            return true;
        } return false;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        for (int i = front;i < size + front;i++){
            System.out.print(items[i % N]);
            System.out.print(" ");
        }
    }
    public T removeFirst(){
        if (size == 0){
            return null;
        } else{
            T item = items[front];
            front = (front + 1) % N;
            size -= 1;
            return item;
        }
    }
    public T removeLast(){
        if (size == 0){
            return null;
        } else{
            rear = (rear - 1 + N) % N;
            T item = items[rear];
            size -= 1;
            return item;
        }
    }
    public T get(int index){
        if (size == 0){
            return null;
        }
        return items[(index + front) % N];
    }
}
