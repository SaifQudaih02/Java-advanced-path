package kollections.maps;

import kollections.Collection;
import kollections.sets.Set;

public class HashMap <K,V> implements Map<K,V>{
    private static final int DEFAULT_CAPACITY = 100;

    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V>[] buckets;
    private int size = 0;

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    public HashMap(int capacity) {
        buckets = new Node[capacity];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    @Override
    public void put(K key, V value) {
        int hashkey = hash(key);
        Node<K, V> bucket = buckets[hashkey];
        if(bucket != null){
            while(bucket.next != null){
                bucket = bucket.next;
            }
            bucket.next = new Node<>(key, value);
        }
        else{
            buckets[hashkey] = new Node<>(key, value);
        }
        size++;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        Node<K, V> node = buckets[index];
        if (node == null){
            return null;
        }
        if (node.key == key){
            return node.value;
        }
        else {
            while(node.next != null){
                node = node.next;
                if (node.key == key){
                    return node.value;
                }
            }
        }
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

//    @Override
//    public List<V> values() {
//        List<V> values = new ArrayList<>(size);
//        for (Node<K, V> bucket : buckets) {
//            Node<K, V> node = bucket;
//            while (node != null) {
//                values.add(node.value);
//                node = node.next;
//            }
//        }
//        return values;
//    }

    @Override
    public Set<K> keySet() {
        return null;
    }

//    @Override
//    public Set<K> keySet() {
//        Set<K> keySet = new ArraySet<>();
//        for (Node<K, V> bucket : buckets) {
//            Node<K, V> node = bucket;
//            while (node != null) {
//                keySet.add(node.key);
//                node = node.next;
//            }
//        }
//        return keySet;
//    }

}
