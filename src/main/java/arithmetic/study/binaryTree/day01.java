package arithmetic.study.binaryTree;

import org.junit.Test;

import java.util.Arrays;

/***
 * 二叉树
 */
public class day01 {

    /***
     * 链表实现二叉树
     */
    @Test
    public void method1(){
        BinaryTree tree = new BinaryTree();
        Node root = tree.addRoot(0);

        Node left = tree.addNode(root, NodeType.LEFT, 1);
        Node right = tree.addNode(root, NodeType.RIGHT, 2);

        tree.addNode(left, NodeType.LEFT, 3);
        tree.addNode(left, NodeType.RIGHT, 4);
        tree.addNode(right, NodeType.LEFT, 5);
        tree.addNode(right, NodeType.RIGHT, 6);

//        tree.preOrder(root);
//        tree.inOrder(root);
        tree.postOrder(root);
    }

    /***
     * 堆
     */
    @Test
    public void method2(){
        HeapTree tree = new HeapTree(10);
        tree.insert(5);
        tree.insert(10);
        tree.insert(8);
        tree.insert(9);
        tree.insert(12);
        tree.insert(7);
        System.out.println(Arrays.toString(tree.getHeap()));

        tree.delete();
        System.out.println(Arrays.toString(tree.getHeap()));
    }

    @Test
    public void method3(){
        int[] arrays = new int[10];
        for (int i=1; i<=10; ++i){
            arrays[i - 1] = i;
        }
        System.out.println(Arrays.toString(arrays));

        HeapTree tree = new HeapTree(arrays);
        System.out.println(Arrays.toString(tree.getHeap()));

        tree.sort();
        System.out.println(Arrays.toString(tree.getHeap()));
    }
}
