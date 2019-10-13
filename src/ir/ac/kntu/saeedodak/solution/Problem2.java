package ir.ac.kntu.saeedodak.solution;

public class Problem2 {

    private final double PI = Math.acos(-1);

    public Problem2() {}

    public double exactValue(int n) { return  2 * n + 2; }

    public double krausValue(int n) {
        double arg = Math.acos(Math.pow(0.5, 1.0 / n));
        return (PI / (arg * arg)) ;
    }

    public double taiVlaue(int n) {
        double arg = Math.acos(Math.pow(0.5, 1.0 / n));
        return 2.7725887 / (arg * arg);
    }

    public double solvePart2(double t1, double t2, double p1, double p2) {
        t1 *= PI / 180;
        t2 *= PI / 180;
        p1 *= PI / 180;
        p2 *= PI / 180;
        return  4 * PI / ( (Math.abs(p1 - p2)) * Math.abs(Math.cos(t1) - Math.cos(t2)) );
    }

}
