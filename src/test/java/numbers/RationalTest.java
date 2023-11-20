package numbers;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThrows;

/**
 * Unit test for simple Rational.
 */
public class RationalTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public RationalTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( RationalTest.class );
    }

    public void testRational()
    {
        // When I create a default `Rational` value
        Rational value = new Rational();
        // Then the value should have numerator 0
        assertThat("the numerator should be 0", value.numerator(), is(0));
        // And the value should have denominator 1
        assertThat("the denominator should be 1", value.denominator(), is(1));
    }

    public void ConstructorIntTest(int a, int b)
    {
        // Given that I have constructed a `Rational` value using the argument `a`
        Rational value = new Rational(a);
        // Then the value should have numerator `b`
        assertThat("the numerator should be " + String.valueOf(b), value.numerator(), is(b));
        // And the value should have denominator `1`
        assertThat("the denominator should be 1", value.denominator(), is(1));
    }
    public void testConstructorInt()
    {
        ConstructorIntTest(2,2);
        ConstructorIntTest(-2,-2);
        ConstructorIntTest(0,0);
    }


    public void constructorBothTest(int a, int b, int c, int d)
    {
        // Given that I have created a Rational value using arguments `a` and `b`
        Rational value = new Rational(a, b);
        // Then the value should have numerator `c`
        assertThat("the numerator should be " + String.valueOf(c), value.numerator(), is(c));
        // And the value should have denominator `d`
        assertThat("the denominator should be " + String.valueOf(d), value.denominator(), is(d));
    }
    public void testconstructorBoth()
    {   
        assertThrows(IllegalArgumentException.class, () -> new Rational(1, 0));
        constructorBothTest(0,3,0,1);
        constructorBothTest(0,-3,0,1);
        constructorBothTest(1,1,1,1);
        constructorBothTest(2,3,2,3);
        constructorBothTest(3,2,3,2);
        constructorBothTest(-2,3,-2,3);
        constructorBothTest(2,-3,-2,3);
        constructorBothTest(-2,-3,2,3);

        // versions with simplification
        constructorBothTest(2,2,1,1);
        constructorBothTest(4,6,2,3);
        constructorBothTest(6,4,3,2);
        constructorBothTest(-4,6,-2,3);
        constructorBothTest(4,-6,-2,3);
        constructorBothTest(-4,-6,2,3);
    }


    public void constructorRationalTest(int a, int b, int c, int d)
    {
        // Given that I have created the `Rational` value `a/b`
        Rational original = new Rational(a, b);
        // When I create a `Rational` value as a copy of the original `Rational` value
        Rational value = new Rational(original);
        // Then the value should have numerator `c`
        assertThat("the numerator should be " + String.valueOf(c), value.numerator(), is(c));
        // And the value should have denominator `d`
        assertThat("the denominator should be " + String.valueOf(d), value.denominator(), is(d));
    }
    public void testconstructorRational()
    {   
        constructorRationalTest(0,3,0,1);
        constructorRationalTest(0,-3,0,1);
        constructorRationalTest(2,3,2,3);
        constructorRationalTest(3,2,3,2);
        constructorRationalTest(2,2,1,1);
        constructorRationalTest(-2,3,-2,3);
        constructorRationalTest(2,-3,-2,3);
        constructorRationalTest(-2,-3,2,3);
    }


    public void oppositeTest(int a, int b, int c, int d)
    {
        Rational original = new Rational(a, b);
        Rational value = original.opposite();
        assertThat("the numerator should be " + String.valueOf(c), value.numerator(), is(c));
        assertThat("the denominator should be " + String.valueOf(d), value.denominator(), is(d));
    }
    public void oppositeErrorTest(int a, int b) 
    {
        Rational original = new Rational(a, b);
        assertThrows(IllegalArgumentException.class, () -> original.opposite());
    }
    public void testOpposite() 
    {   
        oppositeTest(1,1,-1,1);
        oppositeTest(-1,1,1,1);
        oppositeTest(2,3,-2,3);
        oppositeTest(-2,3,2,3);
        oppositeTest(3,2,-3,2);
        oppositeTest(-3,2,3,2);
        oppositeTest(0,2,0,1);

        // overflow
        oppositeErrorTest(Integer.MIN_VALUE,1);
        oppositeErrorTest(1,Integer.MIN_VALUE);
    }

    public void reciprocalTest(int a, int b, int c, int d)
    {
        Rational original = new Rational(a, b);
        Rational value = original.reciprocal();
        assertThat("the numerator should be " + String.valueOf(c), value.numerator(), is(c));
        assertThat("the denominator should be " + String.valueOf(d), value.denominator(), is(d));
    }
    public void testReciprocal() 
    {
        Rational test = new Rational(0,1);
        assertThrows(IllegalArgumentException.class, () -> test.reciprocal());
        reciprocalTest(1,1,1,1);
        reciprocalTest(-1,1,-1,1);
        reciprocalTest(2,3,3,2);
        reciprocalTest(3,2,2,3);
        reciprocalTest(-2,3,-3,2);
        reciprocalTest(-3,2,-2,3);
    }


    public void timesTest(int a, int b, int c, int d, int e, int f)
    {
        Rational first = new Rational(a, b);
        Rational second = new Rational(c, d);
        Rational value = first.times(second);
        assertThat("the numerator should be " + String.valueOf(e), value.numerator(), is(e));
        assertThat("the denominator should be " + String.valueOf(f), value.denominator(), is(f));
    }
    public void timesErrorTest(int a, int b, int c, int d)
    {
        Rational first = new Rational(a, b);
        Rational second = new Rational(c, d);
        assertThrows(IllegalArgumentException.class, () -> first.times(second));
    }
    public void testTimes() 
    {   
        timesTest(0,1,1,1,0,1);
        timesTest(1,1,1,1,1,1);
        timesTest(2,3,2,3,4,9);
        timesTest(-2,3,2,3,-4,9);
        timesTest(-2,-3,2,3,4,9);

        // would overflow/underflow directly
        timesTest(1073741830,1,3,10,322122549,1);
        timesTest(-1073741830,1,3,10,-322122549,1);

        // overflow/underflow error
        timesErrorTest(1073741830,1,2,1); // + +
        timesErrorTest(-1073741830,1,-2,1); // - -
        timesErrorTest(-1073741830,1,2,1); // - +
        timesErrorTest(1073741830,1,-2,1); // + -
        timesErrorTest(1,1073741830,1,2); // denom
    }


    public void dividedByTest(int a, int b, int c, int d, int e, int f)
    {
        Rational first = new Rational(a, b);
        Rational second = new Rational(c, d);
        Rational value = first.dividedBy(second);
        assertThat("the numerator should be " + String.valueOf(e), value.numerator(), is(e));
        assertThat("the denominator should be " + String.valueOf(f), value.denominator(), is(f));
    }
    public void dividedByErrorTest(int a, int b, int c, int d)
    {
        Rational first = new Rational(a, b);
        Rational second = new Rational(c, d);
        assertThrows(IllegalArgumentException.class, () -> first.dividedBy(second));
    }
    public void testDividedBy() 
    {   
        dividedByTest(0,1,1,1,0,1);
        dividedByTest(1,1,1,1,1,1);
        dividedByTest(2,3,2,3,1,1);
        dividedByTest(-2,3,2,3,-1,1);
        dividedByTest(-2,-3,2,3,1,1);

        // would overflow/underflow directly
        dividedByTest(1073741830,1,10,3,322122549,1);
        dividedByTest(-1073741830,1,10,3,-322122549,1);

        // overflow/underflow error
        dividedByErrorTest(1073741830,1,1,1073741830); // + +
        dividedByErrorTest(-1073741830,1,-1,1073741830); // - -
        dividedByErrorTest(-1073741830,1,1,1073741830); // - +
        dividedByErrorTest(1073741830,1,-1,1073741830); // + -
        dividedByErrorTest(1,1073741830,1073741830,1); // denom
        dividedByErrorTest(1,1,0,1);
    }


    public void plusTest(int a, int b, int c, int d, int e, int f)
    {
        Rational first = new Rational(a, b);
        Rational second = new Rational(c, d);
        Rational value = first.plus(second);
        assertThat("the numerator should be " + String.valueOf(e), value.numerator(), is(e));
        assertThat("the denominator should be " + String.valueOf(f), value.denominator(), is(f));
    }
    public void plusErrorTest(int a, int b, int c, int d)
    {
        Rational first = new Rational(a, b);
        Rational second = new Rational(c, d);
        assertThrows(IllegalArgumentException.class, () -> first.plus(second));
    }
    public void testPlus() 
    {   
        plusTest(0,1,0,1,0,1);
        plusTest(2,3,2,3,4,3);
        plusTest(-2,3,2,3,0,1);
        plusTest(-2,3,1,1,1,3);

        // would overflow 
        plusTest(715827883,2,3,4,1431655769,4);
        plusTest(-715827883,2,-3,4,-1431655769,4);

        // overflow/underflow error
        plusErrorTest(1073741830,1,1073741900,1);
        plusErrorTest(-1073741830,1,-1073741900,1);
    }


    public void minusTest(int a, int b, int c, int d, int e, int f)
    {
        Rational first = new Rational(a, b);
        Rational second = new Rational(c, d);
        Rational value = first.minus(second);
        assertThat("the numerator should be " + String.valueOf(e), value.numerator(), is(e));
        assertThat("the denominator should be " + String.valueOf(f), value.denominator(), is(f));
    }
    public void minusErrorTest(int a, int b, int c, int d)
    {
        Rational first = new Rational(a, b);
        Rational second = new Rational(c, d);
        assertThrows(IllegalArgumentException.class, () -> first.minus(second));
    }
    public void testMinus() 
    {
        minusTest(1,1,1,1,0,1);
        minusTest(0,1,0,1,0,1);
        minusTest(2,3,-2,3,4,3);
        minusTest(-2,3,-2,3,0,1);
        minusTest(-2,3,-1,1,1,3);

        // would overflow 
        minusTest(715827883,2,-3,4,1431655769,4);
        minusTest(-715827883,2,3,4,-1431655769,4);

        // overflow/underflow error
        minusErrorTest(1073741830,1,-1073741900,1);
        minusErrorTest(-1073741830,1,1073741900,1);
    }


    public void raisedToThePowerOfTest(int a, int b, int c, int d, int e) 
    {
        Rational original = new Rational(a,b);
        Rational value = original.raisedToThePowerOf(c);
        assertThat("the numerator should be " + String.valueOf(d), value.numerator(), is(d));
        assertThat("the denominator should be " + String.valueOf(e), value.denominator(), is(e));
    }
    public void raisedToThePowerOfErrorTest(int a, int b, int c)
    {
        Rational original = new Rational(a,b);
        assertThrows(IllegalArgumentException.class, () -> original.raisedToThePowerOf(c));
    }
    public void testRaisedToThePowerOf()
    {
        raisedToThePowerOfTest(1,1,0,1,1);
        raisedToThePowerOfTest(1,1,2,1,1);
        raisedToThePowerOfTest(2,1,2,4,1);
        raisedToThePowerOfTest(2,3,2,4,9);
        raisedToThePowerOfTest(-2,3,2,4,9);

        // overflow/underflow
        raisedToThePowerOfErrorTest(-1,1,0);
        raisedToThePowerOfErrorTest(5,1,14);
        raisedToThePowerOfErrorTest(-6,1,13);
        raisedToThePowerOfErrorTest(1,5,14);
    }


    public void equalsRationalTest(int a, int b, int c, int d, boolean e)
    {
        Rational first = new Rational(a, b);
        Rational second = new Rational(c, d);
        assertThat("equals?", first.equals(second), is(e));
    }
    public void testEquals()
    {
        equalsRationalTest(1,2,1,2,true);
    }
}
