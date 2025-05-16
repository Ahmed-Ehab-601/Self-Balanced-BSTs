package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class EnglishDictionary {
    private SelfBalancedBTS<String> tree;
    public  EnglishDictionary(String type){
        if(type.equalsIgnoreCase("AVL")){
            this.tree = new AVLTree<>();

        } else if (type.equalsIgnoreCase("RedBlack")) {
            this.tree = new RedBlackTree<>();
        }
    }
    public boolean insert(String key){
          return this.tree.insert(key);
    }
    public boolean search(String key){
        return this.tree.search(key);
    }
    public boolean delete(String key){
        return this.tree.delete(key);
    }
    public int height(){
        return this.tree.height();
    }
    public int size(){
        return this.tree.size();
    }
    public void print(){
        System.out.println(tree.toString());
    }



    private ArrayList<String> readFile(String filePath ){
        ArrayList<String> keyList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                keyList.add(line.trim());
            }
        } catch (Exception e) {
            System.out.println("Enter Valid File");
        }
        return  keyList;
    }
    public ArrayList<Integer> insertBatch(String filePath){

        return this.tree.insert(readFile(filePath));
    }
    public ArrayList<Integer> deleteBatch(String filePath){

        return this.tree.delete(readFile(filePath));
    }
}
