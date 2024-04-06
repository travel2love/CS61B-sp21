package deque;

import edu.princeton.cs.algs4.StdAudio;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void gettest(){
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
//        ad.addFirst(1);
//        ad.addLast(22);
        for (int i = 0;i < 40;i++){
            ad.addFirst(i);
        }
        for (int i = 0;i < 40;i++){
            ad.removeFirst();
        }
        for (int i = 0;i < 40;i++){
            ad.addLast(i);
        }
        for (int i = 0;i < 30;i++){
            ad.removeLast();
        }
        //ad.printDeque();
        int a = ad.get(1);
        System.out.print(a);
    }
    @Test
    public void test2(){
        Deque<Integer> a = new ArrayDeque<>();
        a.addFirst(10);
        Iterator<Integer> i = a.iterator();
        System.out.println(i.next());
    }
    @Test
    public void addfirstaddlasttest(){
        Deque<Integer> dq = new ArrayDeque<>();
        dq.addFirst(10);
        dq.addLast(12);
        int y = dq.removeFirst();
        System.out.println(y);
        System.out.println(dq.size());
        dq.printDeque();
        int x = dq.removeLast();
        System.out.println(x);
        assertEquals(0,dq.size());
    }

    @Test
    public void equalstest() {
        ArrayDeque<Integer> ad7 = new ArrayDeque<>();
        ad7.addLast(1);
        ad7.addLast(3);
        ad7.addLast(5);
        ArrayDeque<Integer> ad8 = new ArrayDeque<>();
        ad8.addLast(1);
        ad8.addLast(3);
        ad8.addLast(5);
        System.out.println(ad7.equals(ad8));
    }
    @Test
    public void addgettest() {
        ArrayDeque<Integer> ad9 = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            ad9.addLast(i);
        }
        for (int i = 0; i < 10; i++){
            int x = ad9.get(i);
        }
    }
    @Test
    public void iteratortest() {
        ArrayDeque<Integer> ad10 = new ArrayDeque<>();
        for (int i = 0; i < 10; i++){
            ad10.addLast(i);
        }
        Iterator<Integer> iter1 = ad10.iterator();
        while (iter1.hasNext()){
            System.out.println(iter1.next());
        }
        Iterator<Integer> iter2 = ad10.iterator();
        while (iter2.hasNext()){
            System.out.println(iter2.next());
        }
    }
}
