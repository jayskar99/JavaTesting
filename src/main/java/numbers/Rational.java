package numbers;

/**
 * Hello world!
 *
 */
public class Rational 
{   
    // data members
    private int numerator;

    private int denominator;


    // getters
    public int numerator() { return numerator; }

    public int denominator() { return denominator; }

    // helpers
    private int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b);}

    // constructors
    public Rational() { this(0,1); }

    public Rational(int i) { this(i,1); }

    public Rational(Rational r) { this(r.numerator,r.denominator); }

    public Rational(int n, int d) {
        if (n == 0) {
            d = 1;
        } else if (d == 0) {
            throw new IllegalArgumentException("undefined");
        } else if (d < 0) {
            d *= -1;
            n *= -1;
        } 
        int gcd = gcd(Math.abs(n),Math.abs(d));
        this.numerator = n / gcd;
        this.denominator = d / gcd;
    }


    // methods
    public Rational opposite() { return new Rational(this.numerator*-1,this.denominator); }

    public Rational reciprocal() { return new Rational(this.denominator,this.numerator); }
    
}
