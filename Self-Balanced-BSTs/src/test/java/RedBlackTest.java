import org.example.RedBlackTree;
import org.example.Helper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import  java.lang.Math;             
import java.util.ArrayList;

public class RedBlackTest {


   @Test
   void testheightCorectness(){
       System.out.println("==========================================Test for correctness for Max Height===========================================");
      int[] sizes = {10, 100, 500, 1000, 5000};
        for (int size : sizes) {
        RedBlackTree< Integer > tree  = new RedBlackTree<>();
        ArrayList<Integer> list = Helper.generateRandomArrayList(size, 1, 100);
        tree.insert(list);
        Assertions.assertTrue(tree.height() <= 2 * Math.log(tree.size())/Math.log(2));
       
        }

    }
   @Test
   void testinsertionCorrectness(){
       System.out.println("==========================================Test for correctness for Insertion===========================================");
       int[] sizes = {10, 100, 500, 1000, 5000};
        for (int size : sizes) {
        RedBlackTree< Integer > tree  = new RedBlackTree<>();
        ArrayList<Integer> list = Helper.generateRandomArrayList(size, 1, 100);
        tree.insert(list);
        list.sort(null);
        ArrayList<Integer> treetoarray = new ArrayList<>();
        tree.inorderTraversal(tree.root, treetoarray);
        Assertions.assertEquals(list, treetoarray);
}
}
   @Test
   void testdeletionCorrectness(){
       System.out.println("==========================================Test for correctness for Deletion===========================================");
       int[] sizes = {10, 100, 500, 1000, 5000};
        for (int size : sizes) {
        RedBlackTree< Integer > tree  = new RedBlackTree<>();
         ArrayList<Integer> list = Helper.generateRandomArrayList(size, 1, 100);
        tree.insert(list);
        tree.delete(list);

        ArrayList<Integer> treetoarray = new ArrayList<>();
        tree.inorderTraversal(tree.root, treetoarray);
        for (int i = 0; i < list.size(); i++) {
            Assertions.assertFalse(treetoarray.contains(list.get(i)));
        }   
}
}
  @Test
   void testSearch() {
      System.out.println("==========================================Test for correctness for Search===========================================");
      RedBlackTree< Integer > tree  = new RedBlackTree<>();
      ArrayList<Integer> list = Helper.generateRandomArrayList(500, 1, 100);
      tree.insert(list);
      for(int i=0;i<list.size();i++){
        Assertions.assertTrue(tree.search(list.get(i)));
      }
  }
   @Test 
   void testemptyTree() {
  RedBlackTree< Integer > tree  = new RedBlackTree<>();
  Assertions.assertEquals(tree.height(), 0);
  ArrayList<Integer> list = Helper.generateRandomArrayList(100, 1, 100);
  Assertions.assertEquals(tree.delete(list).get(0),0);
  }



}


