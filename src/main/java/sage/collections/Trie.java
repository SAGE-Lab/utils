package sage.collections;

import java.util.Iterator;
import java.util.List;

public class Trie <K> implements Iterable<List<K>> {

    TrieNode<K> root;

    public Trie() {
        root = new TrieNode<>(null, false);
    }

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
