package org.example;
import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;

public class BST<K extends Comparable<K>, V> implements Iterable<BST<K, V>.TreeNode> {
    private TreeNode rootNode;
    private int elementCount = 0;

    public class TreeNode {
        private K key;
        private V value;
        private TreeNode leftChild, rightChild;

        public TreeNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    public void put(K key, V value) {
        if (rootNode == null) {
            rootNode = new TreeNode(key, value);
            elementCount++;
            return;
        }

        TreeNode focusNode = rootNode;
        TreeNode parentNode = null;
        int comparison = 0;

        while (focusNode != null) {
            parentNode = focusNode;
            comparison = key.compareTo(focusNode.key);
            if (comparison < 0) {
                focusNode = focusNode.leftChild;
            } else if (comparison > 0) {
                focusNode = focusNode.rightChild;
            } else {
                focusNode.value = value;
                return;
            }
        }

        if (comparison < 0) {
            parentNode.leftChild = new TreeNode(key, value);
        } else {
            parentNode.rightChild = new TreeNode(key, value);
        }
        elementCount++;
    }

    public V get(K key) {
        TreeNode focusNode = rootNode;
        while (focusNode != null) {
            int comparison = key.compareTo(focusNode.key);
            if (comparison < 0) focusNode = focusNode.leftChild;
            else if (comparison > 0) focusNode = focusNode.rightChild;
            else return focusNode.value;
        }
        return null;
    }

    public void delete(K key) {
        TreeNode parent = null;
        TreeNode current = rootNode;

        while (current != null && !current.key.equals(key)) {
            parent = current;
            if (key.compareTo(current.key) < 0) current = current.leftChild;
            else current = current.rightChild;
        }

        if (current == null) return;

        if (current.leftChild == null || current.rightChild == null) {
            TreeNode replacement = (current.leftChild == null) ? current.rightChild : current.leftChild;
            if (parent == null) rootNode = replacement;
            else if (current == parent.leftChild) parent.leftChild = replacement;
            else parent.rightChild = replacement;
        } else {
            TreeNode heirParent = current;
            TreeNode heir = current.rightChild;
            while (heir.leftChild != null) {
                heirParent = heir;
                heir = heir.leftChild;
            }

            current.key = heir.key;
            current.value = heir.value;

            if (heirParent != current) heirParent.leftChild = heir.rightChild;
            else heirParent.rightChild = heir.rightChild;
        }
        elementCount--;
    }

    public int size() {
        return elementCount;
    }

    @Override
    public Iterator<TreeNode> iterator() {
        return new TreeOrderIterator();
    }

    private class TreeOrderIterator implements Iterator<TreeNode> {
        private Stack<TreeNode> nodeStack = new Stack<>();

        public TreeOrderIterator() {
            moveLeft(rootNode);
        }

        private void moveLeft(TreeNode node) {
            while (node != null) {
                nodeStack.push(node);
                node = node.leftChild;
            }
        }

        @Override
        public boolean hasNext() {
            return !nodeStack.isEmpty();
        }

        @Override
        public TreeNode next() {
            if (!hasNext()) throw new NoSuchElementException();
            TreeNode target = nodeStack.pop();
            moveLeft(target.rightChild);
            return target;
        }
    }
}