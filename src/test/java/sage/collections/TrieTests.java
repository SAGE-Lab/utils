package sage.collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;


public class TrieTests {

    @Test
    public void testInsertStringKeys() {

        List<String> p1 = Arrays.asList("a", "bc", "def");
        List<String> p2 = Arrays.asList("a", "bc", "x");
        List<String> p3 = Arrays.asList("a", "bc");

        Trie<String> trie = new Trie<>();
        trie.insert(p1);
        trie.insert(p2);
        trie.insert(p3);

        assertFalse(trie.root.isLeaf);
        assertEquals(1, trie.root.children.size());
        assertTrue(trie.root.contains("a"));

        TrieNode<String> t1 = trie.root.get("a");
        assertFalse(t1.isLeaf);
        assertEquals(1, t1.children.size());
        assertTrue(t1.contains("bc"));

        TrieNode<String> t2 = t1.get("bc");
        assertTrue(t2.isLeaf);
        assertEquals(2, t2.children.size());
        assertTrue(t2.contains("def"));
        assertTrue(t2.contains("x"));

        assertTrue(t2.get("def").isLeaf);
        assertEquals(0, t2.get("def").children.size());

        assertTrue(t2.get("x").isLeaf);
        assertEquals(0, t2.get("x").children.size());
    }

    @Test
    public void testInsertIntegerKeys() {

        List<Integer> p1 = Arrays.asList(1, 2, 3);
        List<Integer> p2 = Arrays.asList(3, 4, 5);
        List<Integer> p3 = Arrays.asList(1, 2, 10);
        List<Integer> p4 = Arrays.asList(3);

        Trie<Integer> trie = new Trie<>();
        trie.insert(p1);
        trie.insert(p2);
        trie.insert(p3);
        trie.insert(p4);

        assertFalse(trie.root.isLeaf);
        assertEquals(2, trie.root.children.size());
        assertTrue(trie.root.contains(1));
        assertTrue(trie.root.contains(3));

        TrieNode<Integer> t1 = trie.root.get(1);
        assertFalse(t1.isLeaf);
        assertEquals(1, t1.children.size());
        assertTrue(t1.contains(2));

        TrieNode<Integer> t2 = trie.root.get(3);
        assertTrue(t2.isLeaf);
        assertEquals(1, t2.children.size());
        assertTrue(t2.contains(4));

        TrieNode<Integer> t3 = t1.get(2);
        assertFalse(t3.isLeaf);
        assertEquals(2, t3.children.size());
        assertTrue(t3.contains(3));
        assertTrue(t3.contains(10));

        assertTrue(t3.get(3).isLeaf);
        assertTrue(t3.get(3).children.isEmpty());
        assertTrue(t3.get(10).isLeaf);
        assertTrue(t3.get(10).children.isEmpty());

        TrieNode<Integer> t4 = t2.get(4);
        assertFalse(t4.isLeaf);
        assertEquals(1, t4.children.size());
        assertTrue(t4.contains(5));
        assertTrue(t4.get(5).isLeaf);
        assertTrue(t4.get(5).children.isEmpty());
    }

    @Test
    public void testIterator() {
        List<String> p1 = Arrays.asList("a", "bc", "def");
        List<String> p2 = Arrays.asList("a", "bc", "x");
        List<String> p3 = Arrays.asList("a", "bc");

        Trie<String> trie = new Trie<>();
        trie.insert(p1);
        trie.insert(p2);
        trie.insert(p3);

        Iterator<List<String>> iterator = trie.iterator();
        assertTrue(iterator.hasNext());
        List<String> r1 = iterator.next();
        assertEquals(p3, r1);
        assertTrue(iterator.hasNext());
        List<String> r2 = iterator.next();
        assertTrue(iterator.hasNext());
        List<String> r3 = iterator.next();
        assertFalse(iterator.hasNext());

        assertNotEquals(r2, r3);
        assertTrue(r2.equals(p1) || r2.equals(p2));
        assertTrue(r3.equals(p1) || r3.equals(p2));
    }



}
