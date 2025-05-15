//package org.example;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.util.ArrayList;
//
//public class EnglishDictionary {
//    private SelfBalancedBTS hashTable;
//    public  EnglishDictionary(String type){
//        if(type.equalsIgnoreCase("square")){
//            this.hashTable = new AVLTree();
//
//        } else if (type.equalsIgnoreCase("linear")) {
//            this.hashTable= new RedBlackTree();
//        }
//    }
//    public boolean insert(String key){
//          return this.hashTable.insert(key);
//    }
//    public boolean search(String key){
//        return this.hashTable.search(key);
//    }
//    public boolean delete(String key){
//        return this.hashTable.delete(key);
//    }
//    private ArrayList<String> readFile(String filePath ){
//        ArrayList<String> keyList = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                keyList.add(line.trim());
//            }
//        } catch (Exception e) {
//            System.out.println("Enter Valid File");
//        }
//        return  keyList;
//    }
//    public boolean insertBatch(String filePath){
//
//
//        return this.hashTable.insert(readFile(filePath));
//    }
//    public boolean deleteBatch(String filePath){
//
//        return this.hashTable.delete(readFile(filePath));
//    }
//}
