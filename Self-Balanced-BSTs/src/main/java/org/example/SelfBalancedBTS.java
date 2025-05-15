package org.example;

import java.util.ArrayList;

public interface SelfBalancedBTS<T> {
    boolean insert(T key);
    boolean delete(T key);
    boolean search(T key);
    int height();
    int size();
    ArrayList<Integer> insert(ArrayList<T> keys);
    ArrayList<Integer> delete(ArrayList<T> keys);
}
