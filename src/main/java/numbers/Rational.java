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

    // constructors
    public Rational() {
        this.numerator = 0;
        this.denominator = 1;
    }

    public Rational(int i) {
        this(i,1);
    }

    public Rational(Rational original) {
        this(original.numerator,original.denominator);
    }

    public Rational(int n, int d) {
        if (d < 0) {
            d *= -1;
            n *= -1;
        } else if (d == 0) {
            throw new IllegalArgumentException("undefined");
        }
        this.numerator = n;
        this.denominator = d;
    }

    // getters
    public int numerator() { return numerator; }
    public int denominator() { return denominator; }
}
