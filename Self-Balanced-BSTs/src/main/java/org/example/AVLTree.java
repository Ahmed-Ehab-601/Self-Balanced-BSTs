package org.example;

import java.util.*;


public class AVLTree<T extends Comparable<T>> implements Iterable<T>, SelfBalancedBTS<T> {


    private int n;
    private Node<T> root;


    private void update(Node<T> node) {
        if (node == null) return;

        int leftHeight = -1, rightHeight = -1;
        if (node.left != null) leftHeight = node.left.height;
        if (node.right != null) rightHeight = node.right.height;
        node.height = 1 + Math.max(leftHeight, rightHeight);
        node.bf = rightHeight - leftHeight;
    }

    private Node<T> rightRotation(Node<T> node) {
        Node<T> leftChild = node.left;
        node.left = leftChild.right;
        leftChild.right = node;
        update(node);
        update(leftChild);
        return leftChild;
    }

    private Node<T> leftRotation(Node<T> node) {
        Node<T> rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;
        update(node);
        update(rightChild);
        return rightChild;
    }

    private Node<T> balance(Node<T> node) {
        if (node == null) return null;

        if (node.bf == -2) { // Left heavy
            if (node.left != null && node.left.bf <= 0) {
                // Left-Left Case
                return rightRotation(node);
            } else if (node.left != null) {
                // Left-Right Case
                node.left = leftRotation(node.left);
                return rightRotation(node);
            }
        } else if (node.bf == 2) { // Right heavy
            if (node.right != null && node.right.bf >= 0) {
                // Right-Right Case
                return leftRotation(node);
            } else if (node.right != null) {
                // Right-Left Case
                node.right = rightRotation(node.right);
                return leftRotation(node);
            }
        }

        return node;
    }

    private Node<T> insert(Node<T> node, T key) {
        if (node == null) return new Node<>(key);
        int cmp = key.compareTo(node.getKey());
        if (cmp > 0) {
            node.right = insert(node.right, key);
        } else if (cmp < 0) {

            node.left = insert(node.left, key);
        } else {
            // Duplicate Key
            return node;
        }

        update(node);
        return balance(node);
    }

    @Override
    public boolean insert(T key) {
        if (key == null) return false;
        if (search(key)) return false;

        this.n++;
        this.root = this.insert(this.root, key);
        return true;
    }

    private Node<T> getMax(Node<T> node) {
        if (node == null) return null;
        while (node.right != null)
            node = node.right;
        return node;
    }

    private Node<T> delete(Node<T> node, T key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.getKey());

        if (cmp > 0) {
            node.right = this.delete(node.right, key);
        } else if (cmp < 0) {
            node.left = this.delete(node.left, key);
        } else {
            // Found the node to delete
            if (node.left == null && node.right == null) {
                // Case 1: Leaf node
                return null;
            } else if (node.left == null) {

                return node.right;
            } else if (node.right == null) {

                return node.left;
            } else {

                Node<T> successor = getMax(node.left);
                node.key = successor.key;
                node.left = delete(node.left, successor.key);
            }
        }

        update(node);
        return balance(node);
    }

    @Override
    public boolean delete(T key) {
        if (key == null || !search(key)) return false;

        this.root = delete(this.root, key);
        this.n--;
        return true;
    }

    private Node<T> search(Node<T> node, T key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.getKey());
        if (cmp > 0) return search(node.right, key);
        else if (cmp < 0) return search(node.left, key);
        else return node;
    }

    @Override
    public boolean search(T key) {
        if (key == null) return false;
        return search(this.root, key) != null;
    }

    @Override
    public int height() {
        return root == null ? 0 : root.height;
    }
    @Override
    public ArrayList<Integer> insert(ArrayList<T> keys){
        int newElements = 0;
        int oldElements = 0;
        for(T key : keys){
            if(insert(key)){
                newElements++;
            }else{
                oldElements++;
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(newElements);
        ans.add(oldElements);

        return ans;
    }

    @Override
    public ArrayList<Integer> delete(ArrayList<T> keys) {
        int newElements = 0;
        int oldElements = 0;
        for(T key : keys){
            if(delete(key)){
                newElements++;
            }else{
                oldElements++;
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(newElements);
        ans.add(oldElements);

        return ans;
    }


    @Override
    public int size() {
        return this.n;
    }
    @Override
    public java.util.Iterator<T> iterator() {

        final int expectedNodeCount = n;
        final java.util.Stack<Node<T>> stack = new java.util.Stack<>();
        stack.push(root);

        return new java.util.Iterator<T>() {
            Node<T> trav = root;

            @Override
            public boolean hasNext() {
                if (expectedNodeCount != n) throw new java.util.ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {

                if (expectedNodeCount != n) throw new java.util.ConcurrentModificationException();

                while (trav != null && trav.left != null) {
                    stack.push(trav.left);
                    trav = trav.left;
                }

                Node<T> node = stack.pop();

                if (node.right != null) {
                    stack.push(node.right);
                    trav = node.right;
                }

                return node.key;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };

    }

    @Override
    public String toString() {
        return TreePrinter.getTreeDisplay(root);
    }

    public static void main(String[] args) {
        // Test case 1: Basic insertion and tree structure
        System.out.println("========== Test Case 1: Basic Insertion ==========");
        AVLTree<Integer> tree1 = new AVLTree<>();
        tree1.insert(10);
        tree1.insert(5);
        tree1.insert(15);
        tree1.insert(3);
        tree1.insert(7);
        //tree1.toString();
        System.out.println("Height: " + tree1.height());
        System.out.println("Size: " + tree1.size());

        // Test case 2: Testing rotations
        System.out.println("\n========== Test Case 2: Testing Rotations ==========");
        AVLTree<Integer> tree2 = new AVLTree<>();
        System.out.println("Inserting elements that trigger rotations:");
        System.out.println("Insert 30");
        tree2.insert(30);
        System.out.println("Insert 20 (should trigger no rotation)");
        tree2.insert(20);
        System.out.println(tree2.toString());

        System.out.println("Insert 10 (should trigger right rotation - left-left case)");
        tree2.insert(10);
        System.out.println(tree2.toString());
        System.out.println("Insert 25 (no rotation)");
        tree2.insert(25);
        System.out.println("Insert 40 (no rotation)");
        tree2.insert(40);
        System.out.println(tree2.toString());
        System.out.println("Insert 50 (left rotation - right-right case)");
        tree2.insert(50);
        System.out.println(tree2.toString());

        AVLTree<String> tree = new AVLTree<>();
        tree.insert("Ahmed");
        tree.insert("Samaa");
        tree.insert("Maged");
        tree.insert("Fares");
        tree.insert("Sama");
        tree.insert("Nour");
        System.out.println(tree.toString());
        for(String s : tree){
            System.out.println(s);
        }
    }
}