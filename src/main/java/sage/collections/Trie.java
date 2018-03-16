package sage.collections;

import java.util.Iterator;
import java.util.List;

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

    @Override
    public Iterator<List<K>> iterator() {
        return new TrieIterator<>(this);
    }
}
