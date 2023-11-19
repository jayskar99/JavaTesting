package numbers;

/**
 * Hello world!
 *
 */
public class Rational 
{   
    private int numerator;
    private int denominator;

    public Rational() {
        this.numerator = 0;
        this.denominator = 1;
    }

    public Rational(int i) {
        this.numerator = i;
        this.denominator = 1;
    }

    public Rational(int n, int d) {
        this.numerator = n;
        this.denominator = d;
    }

    public int numerator() { return numerator; }

    public int denominator() { return denominator; }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
