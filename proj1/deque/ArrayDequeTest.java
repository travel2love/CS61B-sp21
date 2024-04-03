package deque;

import edu.princeton.cs.algs4.StdAudio;
import org.junit.Test;
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
        a = new ArrayDeque<>();
        
    }
}
