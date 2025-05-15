package org.example;

public class Node<T extends Comparable<T>> implements PrintableNode {
    T key;
    public   Node<T> left, right;
    int height;
    int bf;
    public Node(T key) {
        this.key = key;
        this.height = 0;
        this.bf=0;
    }

    public T getKey() {
        return key;
    }

    @Override
    public PrintableNode getLeft() {
        return left;
    }

    @Override
    public PrintableNode getRight() {
        return right;
    }

    @Override
    public String getText() {
        return key.toString();
    }
}
