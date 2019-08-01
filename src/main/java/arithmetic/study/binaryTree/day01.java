package arithmetic.study.binaryTree;

import org.junit.Test;

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
}
