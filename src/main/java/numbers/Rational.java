package numbers;

/**
 * Hello world!
 *
 */
public class Rational extends Number implements Comparable<Rational>
{   
    // data members
    private int numerator;

    private int denominator;


    // getters
    public int numerator() { return numerator; }

    public int denominator() { return denominator; }

    public double doubleValue() { return (double) this.numerator / (double) this.denominator; }

    public float floatValue() { return (float) this.numerator / (float) this.denominator; }

    public long longValue() { return (long) this.numerator / (long) this.denominator; }

    public int intValue() { return (int) this.numerator / (int) this.denominator; }


    // helpers
    private int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }

    private int lcd(int a, int b) { return (a * b) / gcd(a,b); }

    private Rational toRational(Object o) {
        Rational r = null;
        if (o instanceof Integer) {
            r = new Rational((Integer) o);
        } else if (o instanceof Double) {
            r = new Rational(((Double) o).intValue());
        } else if (o instanceof Long) {
            r = new Rational(((Long) o).intValue());
        } else if (o instanceof Float) {
            r = new Rational(((Float) o).intValue());
        } else {
            r = (Rational) o;
        }
        return r;
    }


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


    // arithmetic
    public Rational opposite() { 
        if (this.numerator == Integer.MIN_VALUE || this.denominator == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("overflow");
        }
        return new Rational(this.numerator*-1,this.denominator); 
    }

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
        } else if ((Integer.MIN_VALUE / lhs.numerator > rhs.numerator) ||
                    (lhs.numerator < 0 && Integer.MIN_VALUE / rhs.numerator > lhs.numerator)) {
            throw new IllegalArgumentException("underflow in numerator");
        }
        return new Rational(rhs.numerator * lhs.numerator, rhs.denominator * lhs.denominator);
    }

    public Rational dividedBy(Rational r) {
        return this.times(r.reciprocal());
    }

    public Rational raisedToThePowerOf(int n) {
        if (n == 0 && this.numerator < 0) {
            throw new IllegalArgumentException("negative to zero");
        } else if (n == 0) {
            return new Rational(1);
        }
        int num = 1;
        int den = 1;
        for (int i=0; i<n; ++i) {
            if (((this.numerator > 0) && Integer.MAX_VALUE / this.numerator < num) ||
                ((this.numerator < 0) && Integer.MAX_VALUE / Math.abs(this.numerator) < Math.abs(num))) {
                throw new IllegalArgumentException("overflow in numerator");
            } else if (Integer.MAX_VALUE / this.denominator < den) {
                throw new IllegalArgumentException("overflow in denominator");
            }
            num *= this.numerator;
            den *= this.denominator;
        }
        return new Rational(num,den);
    }
    

    // operators
    public boolean equals(Object o) { 
        Rational r = toRational(o);
        return (this.numerator == r.numerator) && (this.denominator == r.denominator);
    }

    public boolean greaterThan(Rational r) {
        if (this.denominator == r.denominator) {
            return this.numerator > r.numerator;
        } else {
            int lcd = lcd(this.denominator,r.denominator);
            int rhs = this.numerator * (lcd / this.denominator);
            int lhs = r.numerator * (lcd / r.denominator);
            return rhs > lhs;
        }
    }

    public boolean greaterThan(Number n) { return greaterThan(toRational(n)); }

    public boolean lessThan(Rational r) { return !(this.greaterThan(r)) && !(this.equals(r)); }

    public boolean lessThan(Number n) { return lessThan(toRational(n)); }

    public int compareTo(Rational r) {
        if (this.lessThan(r)) return -1;
        if (this.greaterThan(r)) return 1;
        return 0;
    }

    public boolean isZero() { return this.numerator == 0; }

    public boolean isOne() { return this.numerator == 1; }

    public boolean isMinusOne() { return this.numerator == -1; }

    // return
    public String toString() {
        if (this.denominator == 1) return Integer.toString(this.numerator);
        else return Integer.toString(this.numerator) + "/" + Integer.toString(this.denominator);
    }
}
