package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
      AListNoResizing<Integer> correct = new AListNoResizing<>();
      BuggyAList<Integer> wrong = new BuggyAList<>();
      correct.addLast(4);
      correct.addLast(5);
      correct.addLast(6);
      wrong.addLast(4);
      wrong.addLast(5);
      wrong.addLast(6);
      assertEquals(correct.removeLast(),wrong.removeLast());
      assertEquals(correct.removeLast(),wrong.removeLast());
      assertEquals(correct.removeLast(),wrong.removeLast());
    }

    @Test
    public void randomizedTest(){
      AListNoResizing<Integer> correct = new AListNoResizing<>();
      BuggyAList<Integer> broken = new BuggyAList<>();

      int N = 5000;
      for (int i = 0; i < N; i += 1) {
        int operationNumber = StdRandom.uniform(0, 4);
        if (operationNumber == 0) {
          // addLast
          int randVal = StdRandom.uniform(0, 100);
          correct.addLast(randVal);
          broken.addLast(randVal);
          System.out.println("addLast(" + randVal + ")");
        } else if (operationNumber == 1) {
          // size
          assertEquals(correct.size(),broken.size());
          int size = correct.size();
          System.out.println("size: " + size);
        } else if (operationNumber == 2) {
          if (correct.size() == 0){
            continue;
          }
          int correct_lst = correct.removeLast();
          int broken_lst = broken.removeLast();
          assertEquals(correct_lst,broken_lst);
          System.out.println("remove,num:" + correct_lst);
        } else if (operationNumber == 3) {
          if (correct.size() == 0){
            continue;
          }
          int correct_lst = correct.getLast();
          int broken_lst = broken.getLast();
          assertEquals(correct_lst,broken_lst);
          System.out.println("get_last=" + correct_lst);
        }
      }
    }
}
