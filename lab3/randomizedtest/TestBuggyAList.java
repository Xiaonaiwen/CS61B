package randomizedtest;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.SortedMap;

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

        correct.addLast(7);
        wrong.addLast(7);
        correct.addLast(8);
        wrong.addLast(8);
        correct.addLast(9);
        wrong.addLast(9);

        assertEquals(correct.size(), wrong.size());

        for (int i = 0; i < 3; i++){
            assertEquals(correct.removeLast(), wrong.removeLast());
        }
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
            }
            else if (operationNumber == 1) {
                // size
                assertEquals(L.size(), B.size());
            }
            else if (operationNumber == 2) {
                if (L.size() > 0){
                    assertEquals(L.getLast(), B.getLast());
                }
            }
            else{
                if (L.size() > 0){
                    assertEquals(L.removeLast(), B.removeLast());
                }
            }
        }
    }
}
