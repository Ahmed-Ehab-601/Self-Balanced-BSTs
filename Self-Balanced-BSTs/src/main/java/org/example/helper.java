package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;



public class Helper {
       public static ArrayList<Integer> generateRandomArrayList(int size, int min, int max) {
           Set<Integer> set = new HashSet<>();
        Random rand = new Random();

        while (set.size() < size) {
            set.add(rand.nextInt(size * 10));
        }

        ArrayList<Integer> list = new ArrayList<>(set);
        Collections.shuffle(list);
        return list;
    }
    public static String getTimeString(String command, AVLTree<Integer> tree,ArrayList<Integer> list){
       long nanoTime = 0;
        
        double microTime = 0;
        double milliTime = 0; 
       for(int i=0;i<120;i++){
        long startTime = System.nanoTime();
        execute(command, tree,list);    
        long endTime = System.nanoTime();
        if(i>19){
        nanoTime += (endTime - startTime);
        microTime += ((double) (endTime - startTime)) / 10E3;
        milliTime += ((double) (endTime - startTime)) / 10E6;
        }
        tree=new AVLTree<>();
        if(command=="deletefromavl"||command=="searchinavl")
        tree.insert(list);
    }nanoTime = nanoTime/100;
    microTime = microTime/100;
    milliTime = milliTime/100;
    String stringTime = "{"+ nanoTime + " ns, " + String.format("%.4f",microTime) + " micro, " + String.format("%.4f",milliTime) + " ms}";

    return stringTime;
              
    }
    public static String getTimeString(String command, RedBlackTree<Integer> tree,ArrayList<Integer> list){
       long nanoTime = 0;
        double microTime = 0;
        double milliTime = 0; 
       for(int i=0;i<120;i++){
        long startTime = System.nanoTime();
        execute(command, tree,list);    
        long endTime = System.nanoTime();
        if(i>19){
        nanoTime += (endTime - startTime);
        microTime += ((double) (endTime - startTime)) / 10E3;
        milliTime += ((double) (endTime - startTime)) / 10E6;
        }
        tree=new RedBlackTree<>();
        if(command=="deletefromredblack"||command=="searchinredblack")
        tree.insert(list);
    }
    nanoTime = nanoTime/100;
    microTime = microTime/100;
    milliTime = milliTime/100;
    String stringTime = "{" + nanoTime + " ns, " + String.format("%.4f",microTime) + " micro, " +  String.format("%.4f",milliTime) + " ms}";
    return stringTime;
              
    }
    private static void execute(String command, AVLTree<Integer> tree,ArrayList<Integer> list){ 
       switch (command.toLowerCase()) {
        case "insertinavl":
            tree.insert(list);
            break;
        case "deletefromavl":
            tree.delete(list);
            break;
        case "searchinavl":
          for(int i=0;i<list.size();i++){
            tree.search(list.get(i));
          }
            break;
        default:
            break;
       }
    
    }
    private static void execute(String command, RedBlackTree<Integer> tree,ArrayList<Integer> list){ 
       switch (command.toLowerCase()) {
        case "insertinredblack":
            tree.insert(list);
            break;
        case "deletefromredblack":
            tree.delete(list);
            break;
        case "searchinredblack":
          for(int i=0;i<list.size();i++){
            tree.search(list.get(i));
          }
            break;
        default:
            break;
       }
    
    }
}



