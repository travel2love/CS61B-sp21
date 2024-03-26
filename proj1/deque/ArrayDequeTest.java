package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void gettest(){
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        ad.addFirst(1);
        ad.addLast(22);
        System.out.println(ad.N);
        ad.printDeque();
        int a = ad.get(1);
        assertEquals(22,a);
    }
}
