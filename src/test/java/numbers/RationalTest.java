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

    public void constructorInt(int a, int b)
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
        constructorInt(2,2);
        constructorInt(-2,-2);
        constructorInt(0,0);
    }


    public void constructorBoth(int a, int b, int c, int d)
    {
        // Given that I have created a Rational value using arguments `a` and `b`
        Rational value = new Rational(a, b);
        // Then the value should have numerator `c`
        assertThat("the numerator should be " + String.valueOf(c), value.numerator(), is(c));
        // And the value should have denominator `d`
        assertThat("the denominator should be " + String.valueOf(d), value.denominator(), is(d));
    }
    public void testConstructorBoth()
    {   
        assertThrows(IllegalArgumentException.class, () -> new Rational(1, 0));
        constructorBoth(0,3,0,1);
        constructorBoth(0,-3,0,1);
        constructorBoth(2,3,2,3);
        constructorBoth(3,2,3,2);
        constructorBoth(2,2,2,2);
        constructorBoth(-2,3,-2,3);
        constructorBoth(2,-3,-2,3);
        constructorBoth(-2,-3,2,3);
    }


    public void constructorRational(int a, int b, int c, int d)
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
    public void testConstructorRational()
    {   
        constructorRational(2,3,2,3);
        constructorRational(3,2,3,2);
        constructorRational(2,2,2,2);
        constructorRational(-2,3,-2,3);
        constructorRational(2,-3,-2,3);
        constructorRational(-2,-3,2,3);
    }
}
