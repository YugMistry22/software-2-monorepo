import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    /**
    *
    */
    @Test
    public void testEmptyConstructor() {
        NaturalNumber test = this.constructorTest();
        NaturalNumber ref = this.constructorRef();
        assertEquals(ref, test);
    }

    @Test
    public void testIntConstructor() {
        NaturalNumber test = this.constructorTest(5);
        NaturalNumber ref = this.constructorRef(5);
        assertEquals(ref, test);
    }

    @Test
    public void testStringConstructor() {
        NaturalNumber test = this.constructorTest("123");
        NaturalNumber ref = this.constructorRef("123");
        assertEquals(ref, test);
    }

    @Test
    public void testNaturalNumberConstructor() {
        NaturalNumber ref = this.constructorRef(42);
        NaturalNumber test = this.constructorTest(ref);
        assertEquals(ref, test);
    }

    @Test
    public void testMultiplyBy10() {
        NaturalNumber testNaturalNumber = this.constructorRef(3);
        NaturalNumber test = this.constructorTest(testNaturalNumber);
        NaturalNumber ref = this.constructorRef(34);

        test.multiplyBy10(4);

        assertEquals(ref, test);
    }

    @Test
    public void testMultiplyBy10WithZero() {
        NaturalNumber test = this.constructorTest();
        NaturalNumber ref = this.constructorRef(0);

        test.multiplyBy10(0);

        assertEquals(ref, test);
    }

    @Test
    public void testMultiplyBy10WithCarry() {
        NaturalNumber test = this.constructorTest("57");
        NaturalNumber ref = this.constructorRef("579");

        test.multiplyBy10(9);

        assertEquals(ref, test);
    }

    @Test
    public void testDivideBy10() {
        NaturalNumber test = this.constructorTest("123");
        NaturalNumber ref = this.constructorRef("12");

        int testRemainder = test.divideBy10();
        int refRemainder = 3;

        assertEquals(ref, test);
        assertEquals(refRemainder, testRemainder);
    }

    @Test
    public void testDivideBy10ToZero() {
        NaturalNumber test = this.constructorTest(5);
        NaturalNumber ref = this.constructorRef(0);

        int testRemainder = test.divideBy10();
        int refRemainder = 5;

        assertEquals(ref, test);
        assertEquals(refRemainder, testRemainder);
    }

    @Test
    public void testDivideBy10WhenZero() {
        NaturalNumber test = this.constructorTest();
        NaturalNumber ref = this.constructorRef();

        int testRemainder = test.divideBy10();
        int refRemainder = 0;

        assertEquals(ref, test);
        assertEquals(refRemainder, testRemainder);
    }

    @Test
    public void testIsZeroTrue() {
        NaturalNumber test = this.constructorTest();
        NaturalNumber ref = this.constructorRef();
        assertTrue(test.isZero());
        assertTrue(ref.isZero());
    }

    @Test
    public void testIsZeroFalse() {
        NaturalNumber test = this.constructorTest(15);
        NaturalNumber ref = this.constructorRef(15);

        assertEquals(ref.isZero(), test.isZero());
    }

    @Test
    public void testIsZeroAfterDivideBy10() {
        NaturalNumber test = this.constructorTest(1);
        NaturalNumber ref = this.constructorRef(0);

        test.divideBy10();

        assertEquals(ref.isZero(), test.isZero());
    }
}