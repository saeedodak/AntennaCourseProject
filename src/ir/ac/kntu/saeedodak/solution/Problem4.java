package ir.ac.kntu.saeedodak.solution;

public class Problem4 {

    private static double PI = Math.acos(-1.0);

    public Problem4() {}

    public double solve(double Pt, double f, double R, double Gor, double Got, Point alpha, Point beta) {
        alpha = alpha.normalize();
        beta = beta.normalize();
        double norm2 = Math.pow(alpha.dot(beta), 2);
        double res = norm2 * Gor * Got / Math.pow(4 * PI * R * f, 2);
        return res;
    }

}