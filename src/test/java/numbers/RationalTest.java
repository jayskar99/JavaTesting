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


    public void doubleTest(int a, int b, double c)
    {
        Rational value = new Rational(a, b);
        assertThat("should be" + String.valueOf(c), value.doubleValue(), is(c));
    }
    public void floatTest(int a, int b, float c)
    {
        Rational value = new Rational(a, b);
        assertThat("should be" + String.valueOf(c), value.floatValue(), is(c));
    }
    public void longTest(int a, int b, long c)
    {
        Rational value = new Rational(a, b);
        assertThat("should be" + String.valueOf(c), value.longValue(), is(c));
    }
    public void intTest(int a, int b, int c)
    {
        Rational value = new Rational(a, b);
        assertThat("should be" + String.valueOf(c), value.intValue(), is(c));
    }
    public void testCasts()
    {
        doubleTest(0,1,0.0);
        doubleTest(1,1,1.0);
        doubleTest(1,2,0.5);
        doubleTest(-1,2,-0.5);
        
        floatTest(0,1,(float) 0.0);
        floatTest(1,1,(float) 1.0);
        floatTest(1,2,(float) 0.5);
        floatTest(-1,2,(float) -0.5);

        longTest(0,1,(long) 0);
        longTest(1,1,(long) 1);
        longTest(2,1,(long) 2);
        longTest(-2,1,(long) -2);

        intTest(0,1,(int) 0);
        intTest(1,1,(int) 1);
        intTest(2,1,(int) 2);
        intTest(-2,1,(int) -2);
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
    public void equalsNumberTest(int a, int b, Number c, boolean e)
    {
        Rational first = new Rational(a, b);
        assertThat("equals?", first.equals(c), is(e));
    }
    public void testEquals()
    {
        equalsRationalTest(1,2,1,2,true);
        equalsRationalTest(1,2,2,1,false);
        equalsRationalTest(1,2,1,1,false);
        equalsRationalTest(2,1,1,1,false);

        equalsNumberTest(1,1,1,true);
        equalsNumberTest(1,1,2,false);
        equalsNumberTest(1,1,1.0,true);
        equalsNumberTest(1,1,2.0,false);
        equalsNumberTest(1,1,57.0,false);
        equalsNumberTest(57,1,1.0,false);
        equalsNumberTest(1,1,(long)1,true);
        equalsNumberTest(1,1,(long)2,false);
        equalsNumberTest(1,1,(float)1.0,true);
        equalsNumberTest(1,1,(float)2.0,false);
        equalsNumberTest(1,3,(float) 0.3333333333333333, true);
        equalsNumberTest(1,3,(float) 0.333333333333333333333333333333333333, true);
        equalsNumberTest(1,3,(float) 57, false);
        equalsNumberTest(57,1,(float) 1, false);
        equalsNumberTest(0,1,(float) .00000000000000001, true);

    }

    public void greaterThanRationalTest(int a, int b, int c, int d, boolean e)
    {
        Rational first = new Rational(a, b);
        Rational second = new Rational(c, d);
        assertThat("greater?", first.greaterThan((Rational) second), is(e));
    }
    public void greaterThanNumberTest(int a, int b, Number c, boolean e)
    {
        Rational first = new Rational(a, b);
        assertThat("greater?", first.greaterThan(c), is(e));
    }
    public void testGreaterThan()
    {
        greaterThanRationalTest(1,1,0,1,true);
        greaterThanRationalTest(1,1,1,2,true);
        greaterThanRationalTest(1,1,2,1,false);
        greaterThanRationalTest(1,2,1,1,false);
        greaterThanRationalTest(1,1,1,1,false);

        greaterThanNumberTest(6,5,1,true);
        greaterThanNumberTest(1,2,1,false);
        greaterThanNumberTest(1,1,1,false);
        greaterThanNumberTest(6,5,(double)1.125,true);
        greaterThanNumberTest(1,2,(double)0.75,false);
        greaterThanNumberTest(0,1,(double) -0.00000000000000001, false);
        greaterThanNumberTest(6,5,(float)1.125,true);
        greaterThanNumberTest(1,2,(float)0.75,false);
        greaterThanNumberTest(6,5,(long)1,true);
        greaterThanNumberTest(1,2,(long)1,false);
        greaterThanNumberTest(0,1,(float) -0.00000000000000001, false);
    }


    public void lessThanRationalTest(int a, int b, int c, int d, boolean e)
    {
        Rational first = new Rational(a, b);
        Rational second = new Rational(c, d);
        assertThat("less?", first.lessThan(second), is(e));
    }
    public void lessThanNumberTest(int a, int b, Number c, boolean e)
    {
        Rational first = new Rational(a, b);
        assertThat("less?", first.lessThan(c), is(e));
    }
    public void testLessThan()
    {
        lessThanRationalTest(1,1,0,1,false);
        lessThanRationalTest(1,1,1,2,false);
        lessThanRationalTest(1,1,2,1,true);
        lessThanRationalTest(1,2,1,1,true);
        lessThanRationalTest(1,1,1,1,false);
        lessThanRationalTest(-1,1,-1,2,true);
        lessThanRationalTest(-1,1,1,2,true);
        lessThanRationalTest(-1,1,-2,1,false);

        lessThanNumberTest(6,5,1,false);
        lessThanNumberTest(1,2,1,true);
        lessThanNumberTest(1,1,1,false);
        lessThanNumberTest(6,5,(double)1.125,false);
        lessThanNumberTest(1,2,(double)0.75,true);
        lessThanNumberTest(0,1,(double) 0.00000000000000001, false);
        lessThanNumberTest(6,5,(float)1.125,false);
        lessThanNumberTest(1,2,(float)0.75,true);
        lessThanNumberTest(0,1,(float) 0.00000000000000001, false);
        lessThanNumberTest(6,5,(long)1,false);
        lessThanNumberTest(1,2,(long)1,true);
    }

    public void compareToTest(int a, int b, int c, int d, int e)
    {
        Rational first = new Rational(a, b);
        Rational second = new Rational(c, d);
        assertThat("compare", first.compareTo(second), is(e));
    }
    public void testCompareTo()
    {
        compareToTest(1,1,-1,1,1);
        compareToTest(-1,1,1,1,-1);
        compareToTest(1,1,1,1,0);
    }

    public void isZeroTest(int a, int b, boolean c) 
    {
        Rational value = new Rational(a,b);
        assertThat("zero?", value.isZero(), is(c));
    }
    public void testIsZero()
    {
        isZeroTest(0,1,true);
        isZeroTest(1,1,false);
    }

    public void isOneTest(int a, int b, boolean c) 
    {
        Rational value = new Rational(a,b);
        assertThat("one?", value.isOne(), is(c));
    }
    public void testIsOne()
    {
        isOneTest(0,1,false);
        isOneTest(1,1,true);
    }

    public void isMinusOneTest(int a, int b, boolean c) 
    {
        Rational value = new Rational(a,b);
        assertThat("minus one?", value.isMinusOne(), is(c));
    }
    public void testIsMinusOne()
    {
        isMinusOneTest(0,1,false);
        isMinusOneTest(-1,1,true);
    }

    public void toStringTest(int a, int b, String c) 
    {
        Rational value = new Rational(a,b);
        assertThat("printed right?", value.toString(), is(c));
    }
    public void testToString()
    {
        toStringTest(0,1,"0");
        toStringTest(1,-1,"-1");
        toStringTest(2,3,"2/3");
        toStringTest(3,2,"1 1/2");
        toStringTest(-2,3,"-2/3");
        toStringTest(7,3,"2 1/3");
        toStringTest(-7,3,"-2 1/3");
    }
}
