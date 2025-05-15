package org.example;

import java.util.ArrayList;

/**
 * A Red-Black Tree implementation with generic comparable types.
 * This implementation follows the classic approach with a NIL sentinel node.
 */
public class RedBlackTree<T extends Comparable<T>> implements Iterable<T>, SelfBalancedBTS<T> {

    // Color constants
    public static final boolean RED = true;
    public static final boolean BLACK = false;

    /**
     * Node class representing elements in the Red-Black Tree.
     */
    public class Node implements PrintableNode {
        private boolean color = RED;  // Default color for new nodes is RED
        private T value;
        private Node left, right, parent;

        /**
         * Creates a new node with the specified color and value.
         */
        public Node(boolean color, T value) {
            this.color = color;
            this.value = value;
        }

        /**
         * Constructor with all properties.
         */
        public Node(T value, boolean color, Node parent, Node left, Node right) {
            this.value = value;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public boolean getColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public T getValue() {
            return value;
        }
        @Override
        public Node getLeft() {
            if(left == this){
                return null;
            }
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }
        @Override
        public Node getRight() {
            if(right == this){
                return null;
            }
            return right;
        }

        @Override
        public String getText() {
            if(value != null){
                return value.toString()+" "+(color ? "red" : "black");
            }else{
                return "NIL";
            }

        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }
    }

    // The root node of the RB tree
    private Node root;

    // Sentinel NIL node used for leaf nodes and parent of root
    private final Node NIL;

    // Tracks the number of nodes in the tree
    private int nodeCount = 0;

    /**
     * Constructs an empty Red-Black Tree.
     */
    public RedBlackTree() {
        NIL = new Node(BLACK, null);
        NIL.setLeft(NIL);
        NIL.setRight(NIL);
        NIL.setParent(NIL);
        root = NIL;
    }

    /**
     * Returns the number of nodes in the tree.
     */
    @Override
    public int size() {
        return nodeCount;
    }

    /**
     * Checks if the tree is empty.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Checks if the tree contains the given value.
     */
    public boolean contains(T value) {
        return search(value);
    }

    /**
     * Searches for a value in the tree.
     * @param value The value to search for
     * @return true if the value is found, false otherwise
     */
    @Override
    public boolean search(T value) {
        if (value == null) return false;
        return searchNode(value, root) != NIL;
    }

    /**
     * Helper method to search for a node containing the given value.
     */
    private Node searchNode(T value, Node current) {
        if (current == NIL) return NIL;

        int cmp = value.compareTo(current.getValue());
        if (cmp == 0) return current;
        else if (cmp < 0) return searchNode(value, current.getLeft());
        else return searchNode(value, current.getRight());
    }

    /**
     * Inserts a value into the tree.
     * @param value The value to insert
     * @return true if the value was inserted, false if it already exists
     */
    @Override
    public boolean insert(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Red-Black tree does not allow null values.");
        }

        // Find where to insert the new node
        Node parent = NIL;
        Node current = root;

        while (current != NIL) {
            parent = current;
            int cmp = value.compareTo(current.getValue());

            if (cmp < 0) {
                current = current.getLeft();
            } else if (cmp > 0) {
                current = current.getRight();
            } else {
                // Value already exists
                return false;
            }
        }

        // Create the new node
        Node newNode = new Node(value, RED, parent, NIL, NIL);

        // Link the new node to its parent
        if (parent == NIL) {
            root = newNode;
        } else if (value.compareTo(parent.getValue()) < 0) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }

        // Fix the tree to maintain Red-Black properties
        insertFixup(newNode);
        nodeCount++;
        return true;
    }

    /**
     * Restores the Red-Black properties after insertion.
     */
    private void insertFixup(Node node) {
        Node uncle;

        // Continue until we reach the root or the parent is black
        while (node.getParent().getColor() == RED) {
            if (node.getParent() == node.getParent().getParent().getLeft()) {
                uncle = node.getParent().getParent().getRight();

                // Case 1: Uncle is red - recolor
                if (uncle.getColor() == RED) {
                    node.getParent().setColor(BLACK);
                    uncle.setColor(BLACK);
                    node.getParent().getParent().setColor(RED);
                    node = node.getParent().getParent();
                } else {
                    // Case 2: Node is a right child - convert to Case 3
                    if (node == node.getParent().getRight()) {
                        node = node.getParent();
                        leftRotate(node);
                    }

                    // Case 3: Node is a left child with black uncle
                    node.getParent().setColor(BLACK);
                    node.getParent().getParent().setColor(RED);
                    rightRotate(node.getParent().getParent());
                }
            } else {
                // Mirror cases for when parent is right child
                uncle = node.getParent().getParent().getLeft();

                // Case 1: Uncle is red - recolor
                if (uncle.getColor() == RED) {
                    node.getParent().setColor(BLACK);
                    uncle.setColor(BLACK);
                    node.getParent().getParent().setColor(RED);
                    node = node.getParent().getParent();
                } else {
                    // Case 2: Node is a left child - convert to Case 3
                    if (node == node.getParent().getLeft()) {
                        node = node.getParent();
                        rightRotate(node);
                    }

                    // Case 3: Node is a right child with black uncle
                    node.getParent().setColor(BLACK);
                    node.getParent().getParent().setColor(RED);
                    leftRotate(node.getParent().getParent());
                }
            }

            // Break if we've reached the root
            if (node == root) break;
        }

        // Ensure root is black
        root.setColor(BLACK);
    }

    /**
     * Performs a left rotation on the given node.
     */
    private void leftRotate(Node x) {
        Node y = x.getRight();
        x.setRight(y.getLeft());

        if (y.getLeft() != NIL) {
            y.getLeft().setParent(x);
        }

        y.setParent(x.getParent());

        if (x.getParent() == NIL) {
            root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }

        y.setLeft(x);
        x.setParent(y);
    }

    /**
     * Performs a right rotation on the given node.
     */
    private void rightRotate(Node y) {
        Node x = y.getLeft();
        y.setLeft(x.getRight());

        if (x.getRight() != NIL) {
            x.getRight().setParent(y);
        }

        x.setParent(y.getParent());

        if (y.getParent() == NIL) {
            root = x;
        } else if (y == y.getParent().getLeft()) {
            y.getParent().setLeft(x);
        } else {
            y.getParent().setRight(x);
        }

        x.setRight(y);
        y.setParent(x);
    }

    /**
     * Deletes a value from the tree.
     * @param value The value to delete
     * @return true if the value was deleted, false if it doesn't exist
     */
    @Override
    public boolean delete(T value) {
        if (value == null) return false;

        Node nodeToDelete = searchNode(value, root);
        if (nodeToDelete == NIL) return false;

        deleteNode(nodeToDelete);
        nodeCount--;
        return true;
    }

    /**
     * Helper method to delete a node from the tree.
     */
    private void deleteNode(Node z) {
        Node x;
        Node y = z;
        boolean yOriginalColor = y.getColor();

        if (z.getLeft() == NIL) {
            x = z.getRight();
            transplant(z, z.getRight());
        } else if (z.getRight() == NIL) {
            x = z.getLeft();
            transplant(z, z.getLeft());
        } else {
            y = findMinimum(z.getRight());
            yOriginalColor = y.getColor();
            x = y.getRight();

            if (y.getParent() == z) {
                x.setParent(y);
            } else {
                transplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }

            transplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            y.setColor(z.getColor());
        }

        if (yOriginalColor == BLACK) {
            deleteFixup(x);
        }
    }

    /**
     * Restores the Red-Black properties after deletion.
     */
    private void deleteFixup(Node x) {
        while (x != root && x.getColor() == BLACK) {
            if (x == x.getParent().getLeft()) {
                Node w = x.getParent().getRight();

                // Case 1: Sibling is red
                if (w.getColor() == RED) {
                    w.setColor(BLACK);
                    x.getParent().setColor(RED);
                    leftRotate(x.getParent());
                    w = x.getParent().getRight();
                }

                // Case 2: Sibling has two black children
                if (w.getLeft().getColor() == BLACK && w.getRight().getColor() == BLACK) {
                    w.setColor(RED);
                    x = x.getParent();
                } else {
                    // Case 3: Sibling's right child is black
                    if (w.getRight().getColor() == BLACK) {
                        w.getLeft().setColor(BLACK);
                        w.setColor(RED);
                        rightRotate(w);
                        w = x.getParent().getRight();
                    }

                    // Case 4: Sibling's right child is red
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(BLACK);
                    w.getRight().setColor(BLACK);
                    leftRotate(x.getParent());
                    x = root;
                }
            } else {
                // Mirror cases for when x is a right child
                Node w = x.getParent().getLeft();

                // Case 1: Sibling is red
                if (w.getColor() == RED) {
                    w.setColor(BLACK);
                    x.getParent().setColor(RED);
                    rightRotate(x.getParent());
                    w = x.getParent().getLeft();
                }

                // Case 2: Sibling has two black children
                if (w.getRight().getColor() == BLACK && w.getLeft().getColor() == BLACK) {
                    w.setColor(RED);
                    x = x.getParent();
                } else {
                    // Case 3: Sibling's left child is black
                    if (w.getLeft().getColor() == BLACK) {
                        w.getRight().setColor(BLACK);
                        w.setColor(RED);
                        leftRotate(w);
                        w = x.getParent().getLeft();
                    }

                    // Case 4: Sibling's left child is red
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(BLACK);
                    w.getLeft().setColor(BLACK);
                    rightRotate(x.getParent());
                    x = root;
                }
            }
        }

        x.setColor(BLACK);
    }

    /**
     * Replaces one subtree with another subtree.
     */
    private void transplant(Node u, Node v) {
        if (u.getParent() == NIL) {
            root = v;
        } else if (u == u.getParent().getLeft()) {
            u.getParent().setLeft(v);
        } else {
            u.getParent().setRight(v);
        }
        v.setParent(u.getParent());
    }

    /**
     * Finds the minimum value node in a subtree.
     */
    private Node findMinimum(Node node) {
        while (node.getLeft() != NIL) {
            node = node.getLeft();
        }
        return node;
    }

    /**
     * Finds the maximum value node in a subtree.
     */
    private Node findMaximum(Node node) {
        while (node.getRight() != NIL) {
            node = node.getRight();
        }
        return node;
    }

    /**
     * Returns the height of the tree.
     */
    @Override
    public int height() {
        return calculateHeight(root);
    }

    /**
     * Helper method to calculate the height of a subtree.
     */
    private int calculateHeight(Node node) {
        if (node == NIL) {
            return 0;
        }
        return 1 + Math.max(calculateHeight(node.getLeft()), calculateHeight(node.getRight()));
    }

    @Override
    public String toString() {
        return TreePrinter.getTreeDisplay(root);
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


    /**
     * Returns an in-order iterator over the tree's elements.
     */
    @Override
    public java.util.Iterator<T> iterator() {
        final int expectedNodeCount = nodeCount;
        final java.util.Stack<Node> stack = new java.util.Stack<>();

        return new java.util.Iterator<T>() {
            private Node current = root;

            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) {
                    throw new java.util.ConcurrentModificationException();
                }
                return !stack.isEmpty() || current != NIL;
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) {
                    throw new java.util.ConcurrentModificationException();
                }

                // Traverse to leftmost node
                while (current != NIL) {
                    stack.push(current);
                    current = current.getLeft();
                }

                if (stack.isEmpty()) {
                    throw new java.util.NoSuchElementException();
                }

                // Get the next node
                Node node = stack.pop();
                current = node.getRight();

                return node.getValue();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove not supported");
            }
        };
    }

    /**
     * Main method to test the Red-Black Tree implementation.
     */
    public static void main(String[] args) {
        int[] values = {5, 8, 1, -4, 6, -2, 0, 7};
        RedBlackTree<Integer> rbTree = new RedBlackTree<>();

        System.out.println("Inserting values into the Red-Black Tree:");
        for (int v : values) {
            boolean inserted = rbTree.insert(v);
            System.out.printf("Inserted %d: %s\n", v, inserted);
        }
        for (Integer value : rbTree) {
            System.out.println(value);
        }
        System.out.println(rbTree.toString());

        System.out.println("\nTree height: " + rbTree.height());
        System.out.println("Tree size: " + rbTree.size());

        System.out.println("\nSearching for values:");
        System.out.printf("Tree contains %d: %s\n", 6, rbTree.contains(6));
        System.out.printf("Tree contains %d: %s\n", -5, rbTree.contains(-5));
        System.out.printf("Tree contains %d: %s\n", 1, rbTree.contains(1));
        System.out.printf("Tree contains %d: %s\n", 99, rbTree.contains(99));

        System.out.println("\nDeleting values:");
        System.out.printf("Deleted %d: %s\n", 6, rbTree.delete(6));
        System.out.printf("Deleted %d: %s\n", -5, rbTree.delete(-5));

        System.out.println("\nAfter deletion:");
        System.out.printf("Tree contains %d: %s\n", 6, rbTree.contains(6));
        System.out.println("Tree size: " + rbTree.size());

        System.out.println("\nIterating through the tree:");
        for (Integer value : rbTree) {
            System.out.println(value);
        }
        RedBlackTree<String> redBlackTree = new RedBlackTree<>();
        redBlackTree.insert("Ahmed");
        redBlackTree.insert("Samaa");
        redBlackTree.insert("Maged");
        redBlackTree.insert("Fares");
        redBlackTree.insert("Sama");
        redBlackTree.insert("Nour");
        System.out.println(redBlackTree.toString());
        for(String s : redBlackTree){
            System.out.println(s);
        }
    }
}
