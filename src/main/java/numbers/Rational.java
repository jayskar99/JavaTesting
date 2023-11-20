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

    private int lcd(int a, int b) { return (a * b) / gcd(a,b); }

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

    public Rational plus(Rational r) { 
        int lcd = lcd(this.denominator,r.denominator);
        int rhs = this.numerator * (lcd / this.denominator);
        int lhs = r.numerator * (lcd / r.denominator);
        if (((rhs > 0 && lhs > 0) || (rhs < 0 && lhs < 0)) && 
            Integer.MAX_VALUE - Math.abs(rhs) < Math.abs(lhs)) {
            throw new IllegalArgumentException("overflow in numerator");
        }
        return new Rational(rhs + lhs,lcd);
    }

    public Rational minus(Rational r) { return this.plus(r.opposite()); }

    public Rational times(Rational r) {
        Rational rhs = new Rational(this.numerator,r.denominator);
        Rational lhs = new Rational(r.numerator,this.denominator);
        if (Integer.MAX_VALUE / rhs.denominator < lhs.denominator) {
            throw new IllegalArgumentException("overflow in denominator");
        } else if (((rhs.numerator > 0 && lhs.numerator > 0) || (rhs.numerator < 0 && lhs.numerator < 0)) &&
                    (Integer.MAX_VALUE / Math.abs(rhs.numerator) < Math.abs(lhs.numerator))) {
            throw new IllegalArgumentException("overflow in numerator");
        } else if ((rhs.numerator < 0 && Integer.MIN_VALUE / lhs.numerator > rhs.numerator) ||
                    (lhs.numerator < 0 && Integer.MIN_VALUE / rhs.numerator > lhs.numerator)) {
            throw new IllegalArgumentException("underflow in numerator");
        }
        return new Rational(rhs.numerator * lhs.numerator, rhs.denominator * lhs.denominator);
    }
    
}
