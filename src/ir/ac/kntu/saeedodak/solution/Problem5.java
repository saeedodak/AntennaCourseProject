package ir.ac.kntu.saeedodak.solution;

public class Problem5 {

    private int n, m;
    private double PI = Math.acos(-1.0);

    public Problem5() {}

    public Problem5(int n, int m) {
        this.n = n;
        this.m = m;
    }

    public double solve() {
        double value = getIntegral();
        return 4 * PI / value;
    }

    private double getIntegral() {
        double sum1 = 0;
        double sum2 = 0;
        double delta = 1e-4;
        for(double deltaPhi = -PI/2; deltaPhi <= PI/2; deltaPhi += delta) sum1 += func1(deltaPhi);
        for(double deltaTheta = 0; deltaTheta <= PI; deltaTheta += delta) sum2 += func2(deltaTheta);
        return sum1 * sum2 * delta * delta;
    }

    private double func1(double arg) {
        double COS = Math.cos(arg);
        return Math.pow(COS, m);
    }

    private double func2(double arg) {
        double SIN = Math.sin(arg);
        return Math.pow(SIN, n+1);
    }

}