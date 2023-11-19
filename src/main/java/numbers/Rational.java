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

    // constructors
    public Rational() {
        this(0,1);
    }

    public Rational(int i) {
        this(i,1);
    }

    public Rational(Rational r) {
        this(r.numerator,r.denominator);
    }

    public Rational(int n, int d) {
        if (n == 0) {
            d = 1;
        } else if (d < 0) {
            d *= -1;
            n *= -1;
        } else if (d == 0) {
            throw new IllegalArgumentException("undefined");
        } 
        this.numerator = n;
        this.denominator = d;
    }

    // functions
    public Rational opposite() {
        return new Rational(this.numerator*-1,this.denominator);
    }
    
}
