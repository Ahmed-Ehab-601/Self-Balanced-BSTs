
import org.example.AVLTree;
import org.example.Helper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;



public class AVLTest {


   @Test
   void testheightCorectness(){
       System.out.println("==========================================Test for correctness for Max Height===========================================");
      int[] sizes = {10, 100, 500, 1000, 5000};
        for (int size : sizes) {
        AVLTree< Integer > tree  = new AVLTree<>();
        ArrayList<Integer> list = Helper.generateRandomArrayList(size, 1, 100);
        tree.insert(list);
        Assertions.assertTrue(tree.height() <= 1.44 * Math.log(tree.size())/Math.log(2));

        }

    }
   @Test
   void testinsertionCorrectness(){
       System.out.println("==========================================Test for correctness for Insertion===========================================");
      
        AVLTree< Integer > tree  = new AVLTree<>();
        ArrayList<Integer> list = Helper.generateRandomArrayList(500, 1, 100);
        tree.insert(list);
        list.sort(null);
        ArrayList<Integer> treetoarray = new ArrayList<>();
        tree.inorderTraversalList(tree.root, treetoarray);
        Assertions.assertEquals(list, treetoarray);
}
   @Test
   void testdeletionCorrectness(){
       System.out.println("==========================================Test for correctness for Deletion===========================================");
      
        
        AVLTree< Integer > tree  = new AVLTree<>();
        ArrayList<Integer> list = Helper.generateRandomArrayList(500, 1, 100);
        tree.insert(list);
        tree.delete(list);

        ArrayList<Integer> treetoarray = new ArrayList<>();
        tree.inorderTraversalList(tree.root, treetoarray);
        for (int i = 0; i < list.size(); i++) {
           Assertions.assertFalse(treetoarray.contains(list.get(i)));
          
    }
}
  @Test
  public void testSearch() {
      System.out.println("==========================================Test for correctness for Search===========================================");
      AVLTree< Integer > tree  = new AVLTree<>();
      ArrayList<Integer> list = Helper.generateRandomArrayList(500, 1, 100);
      tree.insert(list);
      for(int i=0;i<list.size();i++){
        Assertions.assertTrue(tree.search(list.get(i)));
      }
  }
  @Test 
  public void testemptyTree() {
  AVLTree< Integer > tree  = new AVLTree<>();
  Assertions.assertEquals(tree.height(), 0);
  ArrayList<Integer> list = Helper.generateRandomArrayList(100, 1, 100);
  Assertions.assertEquals(tree.delete(list).get(0),0);
  }

}