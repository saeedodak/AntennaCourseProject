package ir.ac.kntu.saeedodak.solution;

public class Complex {

    public double a, b;

    public Complex() {}

    public Complex(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public Complex add(Complex foo) {
        return new Complex(a + foo.a, b + foo.b);
    }

    public Complex sub(Complex foo) {
        return new Complex(a - foo.a, b - foo.b);
    }

    public Complex mul(Complex foo) {
        return new Complex(a * foo.a - b * foo.b, a * foo.b + b * foo.a);
    }

    public Complex div(double foo) {
        return new Complex(a / foo, b / foo);
    }

    public double norm() {
        return Math.sqrt(a * a + b * b);
    }

}