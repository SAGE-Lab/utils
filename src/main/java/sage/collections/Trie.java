package sage.collections;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Implementation of a Trie data structure, not limited to character sequences.
 * This class can handle sequences of arbitrary elements K and retrieve them with an iterator.
 *
 * @param <K> The type of key used in the Trie
 * @author Simone Vuotto
 */
public class Trie <K> implements Iterable<List<K>> {

    TrieNode<K> root;

    public Trie() {
        root = new TrieNode<>(null, false);
    }

    /**
     * Insert a sequence of elements K in the trie.
     * @param path the list of elements to insert
     */
    public void insert(List<K> path) {

        TrieNode<K> t = root;

        for(K element: path) {
            t = t.add(element, false);
        }

        t.isLeaf = true;
    }

    /**
     * Check if the trie contains the sequence of elements specified in input.
     * This method only checks if the elements exist, not if the sequence is complete.
     * @see #containsSequence(List) to check if the sequence is complete
     * @param path the sequence to check
     */
    public boolean contains(List<K> path) {
        return find(path) != null;
    }

    /**
     * Check if the trie contains the sequence specified in input.
     * This method checks that the sequence of elements exists and that the last one is marked as leaf,
     * i.e. it represent a full sequence of keys.
     * @see #contains(List)
     * @param path the sequence to check
     */
    public boolean containsSequence(List<K> path) {
        TrieNode<K> t = find(path);
        return t!= null && t.isLeaf;
    }

    private TrieNode<K> find(List<K> path) {
        TrieNode<K> t = root;

        for(K element: path) {
            t = t.get(element);
            if(t == null)
                return null;
        }

        return t;
    }

    @Override
    public Iterator<List<K>> iterator() {
        return new TrieIterator<>(this);
    }
}
