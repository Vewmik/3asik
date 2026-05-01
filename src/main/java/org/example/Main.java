package org.example;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // --- Тестирование Part 1: HashTable ---
        System.out.println("=== TEST PART 1: HASH TABLE ===");
        MyHashTable<MyTestingClass, String> hashTable = new MyHashTable<>(11);
        Random rng = new Random();

        for (int i = 0; i < 10000; i++) {
            int id = rng.nextInt(100000);
            hashTable.put(new MyTestingClass(id, "User" + id), "Val" + i);
        }
        hashTable.printBucketSizes();

        System.out.println("\n");

        // --- Тестирование Part 2: BST ---
        System.out.println("=== TEST PART 2: BINARY SEARCH TREE ===");
        BST<Integer, String> bst = new BST<>();

        bst.put(45, "Data_45");
        bst.put(15, "Data_15");
        bst.put(75, "Data_75");
        bst.put(35, "Data_35");
        bst.put(95, "Data_95");

        System.out.println("Current BST size: " + bst.size());

        System.out.println("Tree elements in-order:");
        for (var node : bst) {
            System.out.println("Key: " + node.getKey() + " | Value: " + node.getValue());
        }

        System.out.println("\nRemoving key 45 (root)...");
        bst.delete(45);
        System.out.println("New BST size: " + bst.size());

        for (var node : bst) {
            System.out.println("Key: " + node.getKey() + " | Value: " + node.getValue());
        }
    }
}