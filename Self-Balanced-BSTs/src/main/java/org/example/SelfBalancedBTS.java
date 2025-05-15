package org.example;

public interface SelfBalancedBTS<T> {
    boolean insert(T key);
    boolean delete(T key);
    boolean search(T key);
    int height();
    int size();
}
