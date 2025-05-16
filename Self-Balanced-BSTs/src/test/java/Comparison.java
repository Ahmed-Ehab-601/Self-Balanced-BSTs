import org.example.AVLTree;
import org.example.RedBlackTree;
import org.example.helper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Comparison {

       @Test
       void testInsertWithDifferentSizes() {
           System.out.println("==========================================test with different sizes===========================================");

           int[] sizes = {100, 200, 500,1000};
           System.out.println("=============For AVL============");
           for (int size : sizes) {
            ArrayList<Integer> list = helper.generateRandomArrayList(size, 1, 100);
            String  stringTime = helper.getTimeString("insertinavl", list);
            System.out.println("Size " + size + "   Insertion Time: " + stringTime);

           }

           System.out.println("=============For RedBlack============");
           for (int size : sizes) {
            ArrayList<Integer> list = helper.generateRandomArrayList(size, 1, 100);
            String  stringTime = helper.getTimeString("insertinredblack", list);
            System.out.println("Size " + size + "  Insertion Time: " + stringTime);
        }
    }
   @Test
    void testdeletionWithDifferentSizes() {
           System.out.println("==========================================test deletion with different sizes===========================================");

           int[] sizes = {100, 200, 500,1000};
           System.out.println("=============For AVL============");
           for (int size : sizes) {
            ArrayList<Integer> list = helper.generateRandomArrayList(size, 1, 100);
            String  stringTime = helper.getTimeString("deletionfromavl", list);
            System.out.println("Size " + size + "  Deletion Time: " + stringTime);

           }

           System.out.println("=============For RedBlack============");
           for (int size : sizes) {
            ArrayList<Integer> list = helper.generateRandomArrayList(size, 1, 100);
            String  stringTime = helper.getTimeString("deletionfromredblack", list);
            System.out.println("Size " + size + "  Deletion Time: " + stringTime);
        }
    }

   @Test
    void testTreeHeight() {
           System.out.println("==========================================test tree height with different sizes===========================================");

           int[] sizes = {100, 200, 500,1000};
          
           for (int size : sizes) {
             System.out.println("=============For Size " + size + "============");
            ArrayList<Integer> list = helper.generateRandomArrayList(size, 1, 100);
            AVLTree<Integer> tree=new AVLTree<>();
            tree.insert(list);
            int avlheight = tree.height();
            RedBlackTree<Integer> tree2=new RedBlackTree<>();
            tree2.insert(list);
            int RedBlackheight = tree2.height();
            System.out.println("Size " + size + "  AVL Height: " + avlheight);
            System.out.println("Size " + size + "  RedBlack Height: " + RedBlackheight);
            System.out.println("Size " + size + "  Height Difference: " + Math.abs(avlheight-RedBlackheight));
            if(avlheight>RedBlackheight){
                System.out.println("Size " + size + "  AVL is higher than RedBlack");
            }
            else if(avlheight<RedBlackheight){
                System.out.println("Size " + size + "  RedBlack is higher than AVL");
            }
            
        }

    }
}

     