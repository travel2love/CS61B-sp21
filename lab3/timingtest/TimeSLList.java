package timingtest;
import edu.princeton.cs.algs4.Stopwatch;
import org.checkerframework.checker.units.qual.A;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        int N = 1000;
        int M = 1000;
        SLList sl = new SLList<>();
        AList ns = new AList<>();
        AList tm = new AList<>();
        AList ops = new AList<>();
        for (int i = 0;i < N;i++){
            sl.addLast(N);
        }ns.addLast(1000);
        Stopwatch sw = new Stopwatch();
        for (int i = 0;i < M;i++){
            sl.getLast();
        }
        double times = sw.elapsedTime();
        tm.addLast(times);
        ops.addLast(10000);
        printTimingTable(ns,tm,ops);
        // TODO: YOUR CODE HERE
    }

}
