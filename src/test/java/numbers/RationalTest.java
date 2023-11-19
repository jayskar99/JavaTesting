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

    /**
     * Rigourous Test :-)
     */
    public void testRational()
    {
        // Given no preconditions
        // When I create a default `Rational` value
        Rational value = new Rational();
        // Then the value should have numerator 0
        assertThat("the numerator should be 0", value.numerator(), is(0));
        // And the value should have denominator 1
        assertThat("the denominator should be 1", value.denominator(), is(1));
    }

    public void testConstructorInt()
    {
        // Given that I have constructed a `Rational` value using the argument `2`
        Rational value = new Rational(2);
        // Then the value should have numerator `2`
        assertThat("the numerator should be 2", value.numerator(), is(2));
        // And the value should have denominator `1`
        assertThat("the denominator should be 1", value.denominator(), is(1));
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
        constructorBoth(2,3,2,3);
        constructorBoth(-2,3,-2,3);
        constructorBoth(2,-3,-2,3);
        constructorBoth(-2,-3,2,3);
    }
}
