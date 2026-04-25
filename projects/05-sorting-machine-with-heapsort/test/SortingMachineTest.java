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
 * @author Aarush, Yug
 */
public abstract class SortingMachineTest {

        protected abstract SortingMachine<String> constructorTest(
                        Comparator<String> order);

        protected abstract SortingMachine<String> constructorRef(
                        Comparator<String> order);

        private SortingMachine<String> createFromArgsTest(
                        Comparator<String> order, boolean insertionMode,
                        String... args) {
                SortingMachine<String> sm = this.constructorTest(order);
                for (String s : args) {
                        sm.add(s);
                }
                if (!insertionMode) {
                        sm.changeToExtractionMode();
                }
                return sm;
        }

        private SortingMachine<String> createFromArgsRef(
                        Comparator<String> order, boolean insertionMode,
                        String... args) {
                SortingMachine<String> sm = this.constructorRef(order);
                for (String s : args) {
                        sm.add(s);
                }
                if (!insertionMode) {
                        sm.changeToExtractionMode();
                }
                return sm;
        }

        private static class StringLT implements Comparator<String> {
                @Override
                public int compare(String s1, String s2) {
                        return s1.compareToIgnoreCase(s2);
                }
        }

        private static final StringLT ORDER = new StringLT();

        @Test
        public final void testConstructor() {
                SortingMachine<String> m = this.constructorTest(ORDER);
                SortingMachine<String> mExpected = this.constructorRef(ORDER);
                assertEquals(mExpected, m);
        }

        @Test
        public final void testAddEmpty() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                true, "green");

                m.add("green");

                assertEquals(mExpected, m);
        }

        @Test
        public final void testAddMultiple() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                true, "green", "orange");

                m.add("green");
                m.add("orange");

                assertEquals(mExpected, m);
        }

        @Test
        public final void testAddInExtractionMode() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER,
                                false);
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                false);

                assertThrows(AssertionError.class, () -> m.add("green"));
                assertThrows(AssertionError.class,
                                () -> mExpected.add("green"));

                assertEquals(mExpected, m);
        }

        @Test
        public final void testAddDuplicates() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                true, "green", "green");

                m.add("green");
                m.add("green");

                assertEquals(mExpected, m);
        }

        @Test
        public final void testChangeToExtractionModeEmptyMachine() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                true);

                m.changeToExtractionMode();
                mExpected.changeToExtractionMode();

                assertEquals(mExpected, m);
        }

        @Test
        public final void testChangeToExtractionModeNonEmptyMachine() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                                "green", "orange", "blue");
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                true, "green", "orange", "blue");

                m.changeToExtractionMode();
                mExpected.changeToExtractionMode();

                assertEquals(mExpected, m);
        }

        @Test
        public final void testChangeToExtractionModeMultipleTimes() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                                "green", "orange", "blue");
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                true, "green", "orange", "blue");

                m.changeToExtractionMode();
                mExpected.changeToExtractionMode();

                assertThrows(AssertionError.class,
                                () -> m.changeToExtractionMode());
                assertThrows(AssertionError.class,
                                () -> mExpected.changeToExtractionMode());

                assertEquals(mExpected, m);
        }

        @Test
        public final void testChangeToExtractionModeWhenAlreadyInExtractionMode() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                                "green", "orange", "blue");
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                false, "green", "orange", "blue");

                assertThrows(AssertionError.class,
                                () -> m.changeToExtractionMode());
                assertThrows(AssertionError.class,
                                () -> mExpected.changeToExtractionMode());

                assertEquals(mExpected, m);
        }

        @Test
        public final void testChangeToExtractionModeAfterExtractingElements() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                                "green", "orange", "blue");
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                false, "green", "orange", "blue");

                m.removeFirst();
                m.removeFirst();
                m.removeFirst();

                mExpected.removeFirst();
                mExpected.removeFirst();
                mExpected.removeFirst();

                assertEquals(mExpected, m);

                assertThrows(AssertionError.class,
                                () -> m.changeToExtractionMode());
                assertThrows(AssertionError.class,
                                () -> mExpected.changeToExtractionMode());

                assertEquals(mExpected, m);
        }

        @Test
        public final void testRemoveFirstFromEmptyMachine() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER,
                                false);
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                false);

                assertThrows(AssertionError.class, () -> m.removeFirst());
                assertThrows(AssertionError.class,
                                () -> mExpected.removeFirst());

                assertEquals(mExpected, m);
        }

        @Test
        public final void testRemoveFirstFromNonEmptyMachine() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                                "green", "blue", "yellow");
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                false, "green", "blue", "yellow");

                String first = m.removeFirst();
                String expectedFirst = mExpected.removeFirst();

                assertEquals(expectedFirst, first);
                assertEquals(mExpected, m);
        }

        @Test
        public final void testRemoveFirstFromSingleElementMachine() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                                "green");
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                false, "green");

                String first = m.removeFirst();
                String expectedFirst = mExpected.removeFirst();

                assertEquals(expectedFirst, first);
                assertEquals(mExpected, m);
        }

        @Test
        public final void testRemoveFirstFromMultipleElementMachine() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                                "green", "blue", "yellow");
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                false, "green", "blue", "yellow");

                String first = m.removeFirst();
                String expectedFirst = mExpected.removeFirst();

                assertEquals(expectedFirst, first);
                assertEquals(mExpected, m);
        }

        @Test
        public final void testRemoveFirstUntilMachineIsEmpty() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                                "green", "blue", "yellow");
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                false, "green", "blue", "yellow");

                assertEquals(mExpected.removeFirst(), m.removeFirst());
                assertEquals(mExpected.removeFirst(), m.removeFirst());
                assertEquals(mExpected.removeFirst(), m.removeFirst());

                assertEquals(mExpected, m);

                assertThrows(AssertionError.class, () -> m.removeFirst());
                assertThrows(AssertionError.class,
                                () -> mExpected.removeFirst());
        }

        @Test
        public final void testRemoveFirstInInsertionmode() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                                "green", "blue", "yellow");
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                true, "green", "blue", "yellow");

                assertThrows(AssertionError.class, () -> m.removeFirst());
                assertThrows(AssertionError.class,
                                () -> mExpected.removeFirst());

                assertEquals(mExpected, m);
        }

        @Test
        public final void testIsInInsertionModeTrueEmptyMachine() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                true);

                assertTrue(m.isInInsertionMode());
                assertTrue(mExpected.isInInsertionMode());

                assertEquals(mExpected, m);
        }

        @Test
        public final void testIsInInsertionModeFalseEmptyMachine() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER,
                                false);
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                false);

                assertFalse(m.isInInsertionMode());
                assertFalse(mExpected.isInInsertionMode());

                assertEquals(mExpected, m);
        }

        @Test
        public final void testIsInInsertionModeTrueNonEmptyMachine() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                                "green", "blue", "yellow");
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                true, "green", "blue", "yellow");

                assertTrue(m.isInInsertionMode());
                assertTrue(mExpected.isInInsertionMode());

                assertEquals(mExpected, m);
        }

        @Test
        public final void testIsInInsertionModeFalseNonEmptyMachine() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                                "green", "blue", "yellow");
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                false, "green", "blue", "yellow");

                assertFalse(m.isInInsertionMode());
                assertFalse(mExpected.isInInsertionMode());

                assertEquals(mExpected, m);
        }

        @Test
        public final void testIsInInsertionModeBeforeAndAfterSwitchingToExtractionMode() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                                "green", "blue", "yellow");
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                true, "green", "blue", "yellow");

                m.changeToExtractionMode();
                mExpected.changeToExtractionMode();

                assertFalse(m.isInInsertionMode());
                assertFalse(mExpected.isInInsertionMode());

                assertEquals(mExpected, m);
        }

        @Test
        public final void testIsInInsertionModeBeforeAndAfterPerformingExtractions() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                                "green", "blue", "yellow");
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                false, "green", "blue", "yellow");

                m.removeFirst();
                m.removeFirst();
                m.removeFirst();

                mExpected.removeFirst();
                mExpected.removeFirst();
                mExpected.removeFirst();

                assertFalse(m.isInInsertionMode());
                assertFalse(mExpected.isInInsertionMode());

                assertEquals(mExpected, m);
        }

        @Test
        public final void testOrder() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER,
                                false);
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                false);

                assertEquals(ORDER, m.order());
                assertEquals(mExpected.order(), m.order());

                assertEquals(mExpected, m);
        }

        @Test
        public final void testOrderInInsertionMode() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                                "banana", "apple", "cherry");

                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                true, "banana", "apple", "cherry");

                assertEquals(ORDER, m.order());
                assertEquals(mExpected.order(), m.order());

                assertEquals(mExpected, m);
        }

        @Test
        public final void testSizeEmptyMachine() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                true);

                assertEquals(mExpected.size(), m.size());
                assertEquals(mExpected, m);
        }

        @Test
        public final void testSizeAfterAddingElements() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                                "blue", "green");

                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                true, "blue", "green");

                assertEquals(mExpected.size(), m.size());
                assertEquals(mExpected, m);
        }

        @Test
        public final void testSizeAfterSwitchingToExtractionMode() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                                "blue", "green");

                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                false, "blue", "green");

                m.changeToExtractionMode();

                assertEquals(mExpected.size(), m.size());
                assertEquals(mExpected, m);
        }

        @Test
        public final void testSizeAfterRemovingElements() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                                "blue", "green", "red");

                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                false, "blue", "green", "red");

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

        @Test
        public final void testSizeAfterClearingMachine() {
                SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                                "blue", "green", "red");

                SortingMachine<String> mExpected = this.createFromArgsRef(ORDER,
                                true, "blue", "green", "red");

                m.clear();
                mExpected.clear();

                assertEquals(mExpected.size(), m.size());
                assertEquals(mExpected, m);
        }
}