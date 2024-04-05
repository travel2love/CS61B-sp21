package deque;

import edu.princeton.cs.algs4.StdAudio;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void gettest(){
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        ad.addFirst(1);
        ad.addLast(22);
        for (int i = 0;i < 6000;i++){
            ad.addFirst(i);
        }
        for (int i = 0;i < 5000;i++){
            ad.removeLast();
        }
        for (int i = 0;i < 6000;i++){
            ad.addFirst(i);
        }
        for (int i = 0;i < 5000;i++){
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

}
