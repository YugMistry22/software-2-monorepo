import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     * Test case for empty map.
     */
    @Test
    public void testEmptyMapSize() {
        Map<String, String> emptyMap = this.constructorTest();
        Map<String, String> refMap = this.constructorRef();
        assertEquals(refMap.size(), emptyMap.size());
    }

    /**
     * Test if empty map has key.
     */
    @Test
    public void testEmptyMapHasKey() {
        Map<String, String> emptyMap = this.constructorTest();
        Map<String, String> refMap = this.constructorRef();
        assertEquals(refMap.hasKey("nonexistent"),
                emptyMap.hasKey("nonexistent"));
    }

    /**
     * Test add to empty map.
     */
    @Test
    public void testAddToEmptyMap() {
        Map<String, String> map = this.constructorTest();
        Map<String, String> refMap = this.constructorRef();

        map.add("key1", "value1");
        refMap.add("key1", "value1");

        assertEquals(refMap.size(), map.size());
        assertEquals(refMap.value("key1"), map.value("key1"));
    }

    /**
     * Duplicate key test.
     */
    @Test(expected = AssertionError.class)
    public void testAddDuplicateKey() {
        Map<String, String> map = this.createFromArgsTest("key1", "value1");
        Map<String, String> refMap = this.createFromArgsRef("key1", "value1");

        map.add("key1", "value2"); // Should trigger assertion error
        refMap.add("key1", "value2"); // Should also trigger assertion error
    }

    /**
     * Remove test.
     */
    @Test
    public void testRemoveKey() {
        Map<String, String> map = this.createFromArgsTest("key1", "value1");
        Map<String, String> refMap = this.createFromArgsRef("key1", "value1");

        Pair<String, String> removed = map.remove("key1");
        Pair<String, String> refRemoved = refMap.remove("key1");

        assertEquals(refRemoved.key(), removed.key());
        assertEquals(refRemoved.value(), removed.value());
        assertEquals(refMap.size(), map.size());
    }

    /**
     * Remove from empty map.
     */
    @Test(expected = AssertionError.class)
    public void testRemoveFromEmptyMap() {
        Map<String, String> emptyMap = this.constructorTest();
        Map<String, String> refMap = this.constructorRef();

        emptyMap.remove("key1"); // Should trigger assertion error
        refMap.remove("key1"); // Should also trigger assertion error
    }

    /**
     * Test removeAny.
     */
    @Test
    public void testRemoveAny() {
        Map<String, String> map = this.createFromArgsTest("key1", "value1",
                "key2", "value2");
        Map<String, String> refMap = this.createFromArgsRef("key1", "value1",
                "key2", "value2");

        Pair<String, String> removed = map.removeAny();
        Pair<String, String> refRemoved = refMap.removeAny();

        assertNotNull(removed);
        assertNotNull(refRemoved);

        assertEquals(refMap.size(), map.size());
        assertFalse(refMap.hasKey(refRemoved.key()));
        assertFalse(map.hasKey(removed.key()));
    }

    /**
     * Value test.
     */
    @Test
    public void testValueRetrieval() {
        Map<String, String> map = this.createFromArgsTest("key1", "value1");
        Map<String, String> refMap = this.createFromArgsRef("key1", "value1");

        assertEquals(refMap.value("key1"), map.value("key1"));
    }

    /**
     * Test for no key.
     */
    @Test(expected = AssertionError.class)
    public void testValueNonexistentKey() {
        Map<String, String> emptyMap = this.constructorTest();
        Map<String, String> refMap = this.constructorRef();

        emptyMap.value("key1"); // Should trigger assertion error
        refMap.value("key1"); // Should also trigger assertion error
    }

    /**
     * Test for has key.
     */
    @Test
    public void testHasKey() {
        Map<String, String> map = this.createFromArgsTest("key1", "value1");
        Map<String, String> refMap = this.createFromArgsRef("key1", "value1");

        assertEquals(refMap.hasKey("key1"), map.hasKey("key1"));
        assertEquals(refMap.hasKey("nonexistent"), map.hasKey("nonexistent"));
    }

    /**
     * Size.
     */
    @Test
    public void testSizeAfterOperations() {
        Map<String, String> map = this.createFromArgsTest("key1", "value1",
                "key2", "value2");
        Map<String, String> refMap = this.createFromArgsRef("key1", "value1",
                "key2", "value2");

        assertEquals(refMap.size(), map.size());

        map.remove("key1");
        refMap.remove("key1");

        assertEquals(refMap.size(), map.size());

        map.add("key3", "value3");
        refMap.add("key3", "value3");

        assertEquals(refMap.size(), map.size());
    }

}
