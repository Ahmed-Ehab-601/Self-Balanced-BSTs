package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class RedBlackTree implements SelfBalancedBTS {

    private  int n;

    public RedBlackTree(int n) {
        this.n = n;

    }

    public RedBlackTree() {

    }



    @Override
    public boolean insert(String key){
        return true;

    }
    @Override
    public boolean delete(String key){
        return true;
    }
    @Override
    public boolean search(String key){

        return true;


    }
    @Override
    public boolean insert(ArrayList<String> keyList){
        return true;
    }

    @Override
    public boolean delete(ArrayList<String> keyList){
        return true;
    }

    public static void test1(){
        SelfBalancedBTS hash = new RedBlackTree(3);
        hash.insert("cat");
        hash.insert("samaa");
        hash.insert("sama");
        hash.insert("nour");
        hash.insert("ahmed");
        hash.insert("maged");
        hash.insert("hello");
        hash.insert("ziad");
        hash.insert("dog");
        hash.insert("fares");
        hash.search("nour");
        hash.search("samaa");
        hash.search("sama");
        hash.search("cat");
        hash.delete("cat");
        hash.delete("cat");
        hash.search("cat");
    }
    public static void test2(){
        SelfBalancedBTS hash = new RedBlackTree(4);
        hash.insert("cat");
        hash.insert("hat");
        hash.insert("sam");
        hash.insert("nor");

    }
    public static void test4(){
        ArrayList<String> test = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G","H"));
        SelfBalancedBTS hash = new RedBlackTree(4);
        hash.insert("A");
        hash.insert("D");
        hash.insert(test);// Should say that A , D  Already found .



    }
    public static void test3(){
        ArrayList<String> list = new ArrayList<>();
        list.add("ahmed");
        list.add("sama");
        list.add("samaa");
        list.add("nour");
       list.add("maged");

        SelfBalancedBTS hash = new RedBlackTree(3);
        hash.insert(list);
        hash.search("ahmed");
        hash.search("sama");
        hash.search("samaa");
        hash.delete("nour");
        hash.search("nour");


    }


    public  static void test5(){
        SelfBalancedBTS hash = new RedBlackTree(5);
        hash.insert("sama");
        hash.insert("samaa");
        ArrayList<String> testlist =new ArrayList<>();
        testlist.add("sama");
        testlist.add("ahmed");
        testlist.add("samaa");
        testlist.add("lina");
        testlist.add("LAILA");
        hash.insert(testlist);

    }

    public  static void test6(){
        SelfBalancedBTS hash = new RedBlackTree(5);
        ArrayList<String> testlist =new ArrayList<>();
        testlist.add("sama");
        testlist.add("ahmed");
        testlist.add("samaa");
        testlist.add("lina");
        testlist.add("Lela");
        hash.insert(testlist);
        testlist.add("66");
        hash.delete(testlist);
        testlist.clear();
        hash.insert(testlist);
        hash.delete(testlist);
        hash.insert("sama");

    }

    public static void main(String[] args) {
        System.out.println("====================================================Test1======================================================= ");
        test1();
        System.out.println("====================================================Test2======================================================= ");
        test2();
        System.out.println("====================================================Test3======================================================= ");
        test3();
        System.out.println("====================================================Test4======================================================= ");
        test4();

        System.out.println("====================================================Test5======================================================= ");
        test5();

        System.out.println("====================================================Test6======================================================= ");
        test6();
    }

}
