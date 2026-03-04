import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    /*
     * Adding multiple elements in insertion mode:
     */
    @Test
    public final void testAddMultiple() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "orange");
        m.add("green");
        m.add("orange");
        assertEquals(mExpected, m);
    }

    /*
     * Adding elements in extraction mode:
     */
    @Test
    public final void testAddInExtractionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        assertThrows(AssertionError.class, () -> m.add("green"));
        assertThrows(AssertionError.class, () -> mExpected.add("green"));
        assertEquals(mExpected, m);
    }

    /*
     * Adding duplicate elements
     */
    @Test
    public final void testAddDuplicates() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "green");
        m.add("green");
        m.add("green");

        assertEquals(mExpected, m);
    }

    /*
     * Switching to extraction mode with an empty machine
     */
    @Test
    public final void testChangeToExtractionModeEmptyMachine() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        assertEquals(mExpected, m);
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /*
     * Switching to extraction mode with a non-empty machine
     */
    @Test
    public final void testChangeToExtractionModeNonEmptyMachine() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "orange", "blue");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "orange", "blue");
        assertEquals(mExpected, m);
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /*
     * Switching to extraction mode multiple times.
     */
    @Test
    public final void testChangeToExtractionModeMultipleTimes() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "orange", "blue");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "orange", "blue");
        assertEquals(mExpected, m);
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();
        assertEquals(mExpected, m);

        assertThrows(AssertionError.class, () -> m.changeToExtractionMode());
        assertThrows(AssertionError.class,
                () -> mExpected.changeToExtractionMode());

        assertEquals(mExpected, m);
    }

    /*
     * Switching to extraction mode when already in extraction mode.
     */
    @Test
    public final void testChangeToExtractionModeWhenAlreadyInExtractionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "orange", "blue");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "orange", "blue");
        assertEquals(mExpected, m);
        assertThrows(AssertionError.class, () -> m.changeToExtractionMode());
        assertThrows(AssertionError.class,
                () -> mExpected.changeToExtractionMode());

        assertEquals(mExpected, m);
    }

    /*
     * Switching to extraction mode after extracting elements.
     */
    @Test
    public final void testChangeToExtractionModeAfterExtractingElements() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "orange", "blue");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "orange", "blue");
        assertEquals(mExpected, m);

        m.removeFirst();
        m.removeFirst();
        m.removeFirst();

        mExpected.removeFirst();
        mExpected.removeFirst();
        mExpected.removeFirst();

        assertEquals(mExpected, m);

        assertThrows(AssertionError.class, () -> m.changeToExtractionMode());
        assertThrows(AssertionError.class,
                () -> mExpected.changeToExtractionMode());

        assertEquals(mExpected, m);
    }

    /*
     * Removing from an empty machine.
     */
    @Test
    public final void testRemoveFirstFromEmptyMachine() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        assertEquals(mExpected, m);

        assertThrows(AssertionError.class, () -> m.removeFirst());
        assertThrows(AssertionError.class, () -> mExpected.removeFirst());

        assertEquals(mExpected, m);
    }

    /*
     * Removing from a non-empty machine.
     */
    @Test
    public final void testRemoveFirstFromNonEmptyMachine() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "blue", "yellow");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "blue", "yellow");
        assertEquals(mExpected, m);

        String first = m.removeFirst();
        String expectedFirst = mExpected.removeFirst();

        assertEquals(expectedFirst, first);
        assertEquals(mExpected, m);
    }

    /*
     * Removing from a single-element machine.
     */
    @Test
    public final void testRemoveFirstFromSingleElementMachine() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green");
        assertEquals(mExpected, m);

        String first = m.removeFirst();
        String expectedFirst = mExpected.removeFirst();

        assertEquals(expectedFirst, first);
        assertEquals(mExpected, m);
    }

    /*
     * Removing from a multiple-element machine.
     */
    @Test
    public final void testRemoveFirstFromMultipleElementMachine() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "blue", "yellow");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "blue", "yellow");
        assertEquals(mExpected, m);

        String first = m.removeFirst();
        String expectedFirst = mExpected.removeFirst();

        assertEquals(expectedFirst, first);
        assertEquals(mExpected, m);
    }

    /*
     * Removing until machine is empty.
     */
    @Test
    public final void testRemoveFirstUntilMachineIsEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "blue", "yellow");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "blue", "yellow");
        assertEquals(mExpected, m);

        assertEquals(mExpected.removeFirst(), m.removeFirst());
        assertEquals(mExpected.removeFirst(), m.removeFirst());
        assertEquals(mExpected.removeFirst(), m.removeFirst());

        assertEquals(mExpected, m);

        assertThrows(AssertionError.class, () -> m.removeFirst());
        assertThrows(AssertionError.class, () -> mExpected.removeFirst());
    }

    /*
     * Removing in insertion mode.
     */
    @Test
    public final void testRemoveFirstInInsertionmode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue", "yellow");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "blue", "yellow");
        assertEquals(mExpected, m);

        assertThrows(AssertionError.class, () -> m.removeFirst());
        assertThrows(AssertionError.class, () -> mExpected.removeFirst());

        assertEquals(mExpected, m);
    }

    /*
     * Check for true insertion mode in empty machine
     */
    @Test
    public final void testIsInInsertionModeTrueEmptyMachine() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        assertEquals(mExpected, m);
        assertTrue(m.isInInsertionMode());
        assertTrue(mExpected.isInInsertionMode());
    }

    /*
     * Check for false insertion mode in empty machine
     */
    @Test
    public final void testIsInInsertionModeFalseEmptyMachine() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        assertEquals(mExpected, m);
        assertFalse(m.isInInsertionMode());
        assertFalse(mExpected.isInInsertionMode());
    }

    /*
     * Check for true insertion mode in non-empty machine
     */
    @Test
    public final void testIsInInsertionModeTrueNonEmptyMachine() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue", "yellow");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "blue", "yellow");
        assertEquals(mExpected, m);
        assertTrue(m.isInInsertionMode());
        assertTrue(mExpected.isInInsertionMode());
    }

    /*
     * Check for false insertion mode in nonempty machine
     */
    @Test
    public final void testIsInInsertionModeFalseNonEmptyMachine() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "blue", "yellow");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "blue", "yellow");
        assertEquals(mExpected, m);
        assertFalse(m.isInInsertionMode());
        assertFalse(mExpected.isInInsertionMode());
    }

    /*
     * Check for insertion mode before and after switching to extraction mode
     */
    @Test
    public final void testIsInInsertionModeBeforeAndAfterSwitchingToExtractionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue", "yellow");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "blue", "yellow");
        assertEquals(mExpected, m);
        assertTrue(m.isInInsertionMode());
        assertTrue(mExpected.isInInsertionMode());

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        assertEquals(mExpected, m);
        assertFalse(m.isInInsertionMode());
        assertFalse(mExpected.isInInsertionMode());
    }

    /*
     * Check for insertion mode before and after performing extractions
     */
    @Test
    public final void testIsInInsertionModeBeforeAndAfterPerformingExtractions() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "blue", "yellow");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "blue", "yellow");
        assertEquals(mExpected, m);
        assertFalse(m.isInInsertionMode());
        assertFalse(mExpected.isInInsertionMode());

        m.removeFirst();
        m.removeFirst();
        m.removeFirst();

        mExpected.removeFirst();
        mExpected.removeFirst();
        mExpected.removeFirst();

        assertEquals(mExpected, m);
        assertFalse(m.isInInsertionMode());
        assertFalse(mExpected.isInInsertionMode());
    }

    /*
     * Create sorting machine with custom order
     */
    @Test
    public final void testOrder() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        assertEquals(ORDER, m.order());
        assertEquals(ORDER, mExpected.order());
        assertEquals(mExpected, m);
    }

    /*
     * Check size of empty machine.
     */
    @Test
    public final void testSizeEmptyMachine() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        assertEquals(mExpected.size(), m.size());
    }

    /*
     * Check size after adding elements.
     */
    @Test
    public final void testSizeAfterAddingElements() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue",
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "blue", "green");
        assertEquals(mExpected.size(), m.size());
    }

    /*
     * Check size after switching to extraction mode.
     */
    @Test
    public final void testSizeAfterSwitchingToExtractionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue",
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "blue", "green");
        m.changeToExtractionMode();
        assertEquals(mExpected.size(), m.size());
    }

    /*
     * Check size after removing elements.
     */
    @Test
    public final void testSizeAfterRemovingElements() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue",
                "green", "red");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "blue", "green", "red");
        m.changeToExtractionMode();

        m.removeFirst();
        mExpected.removeFirst();
        assertEquals(mExpected.size(), m.size());
        assertEquals(mExpected, m);

        m.removeFirst();
        mExpected.removeFirst();
        assertEquals(mExpected.size(), m.size());
        assertEquals(mExpected, m);

        m.removeFirst();
        mExpected.removeFirst();
        assertEquals(mExpected.size(), m.size());
        assertEquals(mExpected, m);

    }

    /*
     * Check size after clearing machine.
     */
    @Test
    public final void testSizeAfterClearingMachine() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue",
                "green", "red");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "blue", "green", "red");
        m.clear();
        mExpected.clear();
        assertEquals(mExpected.size(), m.size());
        assertEquals(mExpected, m);

    }

}
