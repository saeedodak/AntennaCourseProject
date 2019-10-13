package ir.ac.kntu.saeedodak.solution;

public class Problem3 {

    private static double PI = Math.acos(-1.0);

    public Problem3() {}

    public double solvePartA(Point alpha, Point beta) {
        alpha = alpha.normalize();
        beta = beta.normalize();
        double norm2 = Math.pow(alpha.dot(beta), 2);
        return norm2;
    }

    public String getPolarization(Point A) {
        if(A.x.a == 0 && A.y.a == 0) return "LP";
        if(A.x.b == 0 && A.y.b == 0) return "LP";
        double normX = A.x.norm();
        double normY = A.y.norm();
        if(normX == 0 || normY == 0) return "LP";
        double ratio = normX / normY;
        Complex I = new Complex(1, 0);
        Complex foo = I.mul(new Complex(ratio, 0));
        Complex bar = foo.mul(A.y);
        if(bar.a == A.x.a && bar.b == A.x.b) return "LP";
        foo.a *= -1;
        bar = foo.mul(A.y);
        if(bar.a == A.x.a && bar.b == A.x.b) return "LP";
        if(normX == normY) {
            Complex J = new Complex(0, 1);
            foo = J.mul(A.x);
            if(foo.a == A.y.a && foo.b == A.y.b) return "CP";
            foo = J.mul(A.y);
            if(foo.a == A.x.a && foo.b == A.x.b) return "CP";
        }
        return "EP";
    }

}