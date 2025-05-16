package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;



public class helper {
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
    public static String getTimeString(String command,ArrayList<Integer> list){
        long startTime = System.nanoTime();
        execute(command,list);    
        long endTime = System.nanoTime();
        long  nanoTime = (endTime - startTime);
        double microTime = ((double) (endTime - startTime)) / 10E3;
        double milliTime = ((double) (endTime - startTime)) / 10E6;
        String stringTime = "{" + nanoTime + " ns, " + microTime + " micro, " + milliTime + " ms}";
        return stringTime;
              
    }
    private static void execute(String command,ArrayList<Integer> list){ {
       switch (command.toLowerCase()) {
        case "insertinavl":
            AVLTree<Integer> tree=new AVLTree<>();
            tree.insert(list);
            break;
        case "insertinrb":
            RedBlackTree<Integer> tree1=new RedBlackTree<>(); 
            tree1.insert(list);
            break;
        case "deletefromavl":
            AVLTree<Integer> tree2=new AVLTree<>(); 
            tree2.delete(list);
            break;
        case "deletefromrb":
            RedBlackTree<Integer> tree3=new RedBlackTree<>(); 
            tree3.delete(list);
            break;
        default:
            break;
       }
    }
    }
}



