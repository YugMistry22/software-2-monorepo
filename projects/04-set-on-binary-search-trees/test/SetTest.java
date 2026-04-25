import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 * 
 * @author Aarush and Yug
 */
public abstract class SetTest {

    protected abstract Set<String> constructorTest();

    protected abstract Set<String> constructorRef();

    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            set.add(s);
        }
        return set;
    }

    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            set.add(s);
        }
        return set;
    }

    /** Test constructor */
    @Test
    public void testConstructor() {
        Set<String> test = this.constructorTest();
        Set<String> ref = this.constructorRef();

        assertEquals(ref.size(), test.size());
        assertFalse(test.contains("a"));
    }

    /** Test add method */
    @Test
    public void testAdd() {
        Set<String> test = this.constructorTest();

        test.add("a");
        Set<String> ref1 = this.createFromArgsRef("a");
        assertEquals(ref1, test);

        test.add("b");
        Set<String> ref2 = this.createFromArgsRef("a", "b");
        assertEquals(ref2, test);

        assertThrows(AssertionError.class, () -> test.add("a"));

        test.add("c");
        Set<String> ref3 = this.createFromArgsRef("a", "b", "c");
        assertEquals(ref3, test);

        test.add("d");
        Set<String> ref4 = this.createFromArgsRef("a", "b", "c", "d");
        assertEquals(ref4, test);
    }

    /** Test remove method */
    @Test
    public void testRemove() {
        Set<String> test = this.createFromArgsTest("x", "y", "z", "w", "v");

        test.remove("y");
        Set<String> ref1 = this.createFromArgsRef("x", "z", "w", "v");
        assertEquals(ref1, test);

        test.remove("z");
        Set<String> ref2 = this.createFromArgsRef("x", "w", "v");
        assertEquals(ref2, test);

        assertThrows(AssertionError.class, () -> test.remove("y"));

        test.remove("x");
        Set<String> ref3 = this.createFromArgsRef("w", "v");
        assertEquals(ref3, test);
    }

    /** Test removeAny method */
    @Test
    public void testRemoveAny() {
        Set<String> test = this.createFromArgsTest("x", "y", "z", "w", "v");

        String removed = test.removeAny();

        Set<String> ref = this.constructorRef();
        ref.add("x");
        ref.add("y");
        ref.add("z");
        ref.add("w");
        ref.add("v");

        // We cannot predict which element is removed, so only check size + absence
        assertEquals(4, test.size());
        assertFalse(test.contains(removed));

        assertThrows(AssertionError.class,
                () -> this.constructorTest().removeAny());
    }

    /** Test contains method */
    @Test
    public void testContains() {
        Set<String> test = this.createFromArgsTest("apple", "banana", "cherry",
                "date", "elderberry");

        assertTrue(test.contains("banana"));
        assertFalse(test.contains("fig"));

        assertThrows(AssertionError.class, () -> test.contains(null));
    }

    /** Test size method */
    @Test
    public void testSize() {
        Set<String> test = this.createFromArgsTest("one", "two", "three");

        assertEquals(3, test.size());

        test.add("four");
        assertEquals(4, test.size());

        test.remove("two");
        assertEquals(3, test.size());

        assertEquals(0, this.constructorTest().size());

        test.add("five");
        assertEquals(4, test.size());
    }
}