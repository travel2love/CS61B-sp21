package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList a = new AList<>();
        AList ns = new AList<>();
        AList ti = new AList<>();
        Stopwatch sw_1000 = new Stopwatch();
        for (int i = 0;i < 1000 ;i++){
            a.addLast(1000);
        }
        ns.addLast(1000);
        double time_1000 = sw_1000.elapsedTime();
        ti.addLast(time_1000);

        Stopwatch sw_2000 = new Stopwatch();
        for (int i = 0;i < 2000 ;i++){
            a.addLast(2000);
        }
        ns.addLast(2000);
        double time_2000 = sw_2000.elapsedTime();
        ti.addLast(time_2000);

        Stopwatch sw_4000 = new Stopwatch();
        for (int i = 0;i < 4000 ;i++){
            a.addLast(4000);
        }
        ns.addLast(4000);
        double time_4000 = sw_4000.elapsedTime();
        ti.addLast(time_4000);

        Stopwatch sw_32000 = new Stopwatch();
        for (int i = 0;i < 32000 ;i++){
            a.addLast(32000);
        }
        ns.addLast(32000);
        double time_32000 = sw_32000.elapsedTime();
        ti.addLast(time_32000);

        Stopwatch sw_64000 = new Stopwatch();
        for (int i = 0;i < 64000 ;i++){
            a.addLast(64000);
        }
        ns.addLast(64000);
        double time_64000 = sw_64000.elapsedTime();
        ti.addLast(time_64000);

        printTimingTable(ns,ti,ns);
        // TODO: YOUR CODE HERE
    }
}
