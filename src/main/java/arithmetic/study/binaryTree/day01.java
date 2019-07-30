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
        ListTree tree = new ListTree();
        ListTree.Node root = tree.addRoot(0);

        ListTree.Node left = tree.addNode(root, ListTree.NodeType.LEFT, 1);
        ListTree.Node right = tree.addNode(root, ListTree.NodeType.RIGHT, 2);

        tree.addNode(left, ListTree.NodeType.LEFT, 3);
        tree.addNode(left, ListTree.NodeType.RIGHT, 4);
        tree.addNode(right, ListTree.NodeType.LEFT, 5);
        tree.addNode(right, ListTree.NodeType.RIGHT, 6);

//        tree.preOrder(root);
//        tree.inOrder(root);
        tree.postOrder(root);
    }
}
