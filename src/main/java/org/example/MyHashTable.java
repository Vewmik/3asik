package org.example;

public class MyHashTable<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> nextEntry;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry<K, V>[] buckets;
    private int capacity = 11;
    private int totalElements = 0;

    @SuppressWarnings("unchecked")
    public MyHashTable() {
        this.buckets = new Entry[capacity];
    }

    @SuppressWarnings("unchecked")
    public MyHashTable(int initialCapacity) {
        this.capacity = initialCapacity;
        this.buckets = new Entry[capacity];
    }

    private int getIndex(K key) {
        int h = key.hashCode();
        return (h & 0x7fffffff) % capacity;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Entry<K, V> current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.nextEntry;
        }

        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.nextEntry = buckets[index];
        buckets[index] = newEntry;
        totalElements++;
    }

    public V get(K key) {
        int index = getIndex(key);
        Entry<K, V> node = buckets[index];
        while (node != null) {
            if (node.key.equals(key)) return node.value;
            node = node.nextEntry;
        }
        return null;
    }

    public V remove(K key) {
        int index = getIndex(key);
        Entry<K, V> current = buckets[index];
        Entry<K, V> previous = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (previous != null) previous.nextEntry = current.nextEntry;
                else buckets[index] = current.nextEntry;
                totalElements--;
                return current.value;
            }
            previous = current;
            current = current.nextEntry;
        }
        return null;
    }

    public void printBucketSizes() {
        for (int i = 0; i < capacity; i++) {
            int count = 0;
            Entry<K, V> temp = buckets[i];
            while (temp != null) {
                count++;
                temp = temp.nextEntry;
            }
            System.out.println("Ячейка " + i + ": " + count);
        }
    }
}