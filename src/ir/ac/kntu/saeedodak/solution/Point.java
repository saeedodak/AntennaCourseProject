package ir.ac.kntu.saeedodak.solution;

public class Point {

    public Complex x, y, z;

    public Point() {}

    public Point(String s) {
        String [] foo = s.split(",");
        for(int i=0; i<3; i++) {
            String S1 = "", S2 = "";
            int j = 0;
            for(; j<foo[i].length(); j++) {
                if(foo[i].charAt(j+1) == 'j') {
                    S2 += "" + foo[i].charAt(j);
                    j+=2;
                    break;
                }
                else S1 += "" + foo[i].charAt(j);
            }
            for(; j<foo[i].length(); j++) S2 += "" + foo[i].charAt(j);
            if(i == 0) this.x = new Complex(Double.parseDouble(S1), Double.parseDouble(S2));
            if(i == 1) this.y = new Complex(Double.parseDouble(S1), Double.parseDouble(S2));
            if(i == 2) this.z = new Complex(Double.parseDouble(S1), Double.parseDouble(S2));
        }
    }

    public Point(Complex a, Complex b, Complex c) {
        this.x = a;
        this.y = b;
        this.z = c;
    }

    public Point normalize() {
        double value = Math.pow(x.norm(), 2) + Math.pow(y.norm(), 2) + Math.pow(z.norm(), 2);
        value = Math.sqrt(value);
        return new Point(x.div(value), y.div(value), z.div(value));
    }

    public double dot(Point foo) {
        Complex A = x.mul(foo.x);
        Complex B = y.mul(foo.y);
        Complex C = z.mul(foo.z);
        return (A.add(B.add(C))).norm();
    }

}