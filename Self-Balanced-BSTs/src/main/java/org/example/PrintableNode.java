package org.example;

/** Node that can be printed */
public interface PrintableNode {

    // Get left child
    public PrintableNode getLeft();

    // Get right child
    public PrintableNode getRight();

    // Get text to be printed
    public String getText();
}
