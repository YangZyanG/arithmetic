package arithmetic.study.test;

import lombok.Data;
import org.junit.Test;


/**
 * @author yangziyang
 * @since 2020-06-18
 */
public class BinaryTreeTest {

    /***
     * 二叉树谨记四个定义
     * 1.高度
     * 当前节点到叶子节点的最长路径所经历的边的条数
     * 2.深度
     * 根节点到当前节点所经历的边的条数
     * 3.层数
     * 深度+1
     * 4.树的高度
     * 根节点的高度
     * 5.树的深度
     * 根节点到叶子节点最长路径的层数
     */

    @Data
    class LinkedBinarySearchTree<T extends Comparable<? super T>> {

        private Node<T> root;

        public Node<T> search(T element) {
            if (root == null) {
                return null;
            }

            Node<T> currentNode = root;
            while (currentNode != null) {
                if (element.compareTo(currentNode.element) < 0) {
                    currentNode = currentNode.l;
                } else if (element.compareTo(currentNode.element) > 0) {
                    currentNode = currentNode.r;
                } else {
                    return currentNode;
                }
            }
            return null;
        }

        public void add(T element) {
            if (root == null) {
                root = new Node<>(element);
                return;
            }

            Node<T> currentNode = root;
            while (currentNode != null) {
                if (element.compareTo(currentNode.element) <= 0) {
                    if (currentNode.l == null) {
                        Node<T> newNode = new Node(element);
                        currentNode.l = newNode;
                        break;
                    }
                    currentNode = currentNode.l;
                } else {
                    if (currentNode.r == null) {
                        Node<T> newNode = new Node<>(element);
                        currentNode.r = newNode;
                        break;
                    }
                    currentNode = currentNode.r;
                }
            }
        }

        public void delete(T element) {
            //
        }

        public Node<T> searchMax() {
            if (root == null) {
                return null;
            }

            Node<T> currentNode = root;
            while (currentNode.r != null) {
                currentNode = currentNode.r;
            }
            return currentNode;
        }

        public Node<T> searchMin() {
            if (root == null) {
                return null;
            }

            Node<T> currentNode = root;
            while (currentNode.l != null) {
                currentNode = currentNode.l;
            }
            return currentNode;
        }

        public Node<T> searchPre(T element) {
            if (root == null) {
                return null;
            }

            Node<T> currentNode = root;
            Node<T> preNode = null;
            while (currentNode != null) {
                if (element.compareTo(currentNode.element) > 0) {
                    preNode = currentNode;
                    currentNode = currentNode.r;
                } else if (element.compareTo(currentNode.element) < 0) {
                    preNode = currentNode;
                    currentNode = currentNode.l;
                } else {
                    if (currentNode == root) {
                        return null;
                    }
                    return preNode;
                }
            }
            return null;
        }

        public int maxDepth(){
            return maxDepth(root);
        }

        private int maxDepth(Node<T> currentNode) {
            if (currentNode == null){
                return 0;
            }
            return Math.max(maxDepth(currentNode.l), maxDepth(currentNode.r)) + 1;
        }

        /***
         *
         * 树的遍历
         * 1.前序
         * 自己 -> 左子树 -> 右子树
         * 2.中序
         * 左子树 -> 自己 -> 右子树
         * 3.后序
         * 左子树 -> 右子树 -> 自己
         */
        public <T> void preOrder(Node<T> node) {
            System.out.println(node.element);
            if (node.l != null)
                preOrder(node.l);
            if (node.r != null)
                preOrder(node.r);
        }

        public <T> void inOrder(Node<T> node) {
            if (node.l != null)
                inOrder(node.l);
            System.out.println(node.element);
            if (node.r != null)
                inOrder(node.r);
        }

        public <T> void postOrder(Node<T> node) {
            if (node.l != null)
                postOrder(node.l);
            if (node.r != null)
                postOrder(node.r);
            System.out.println(node.element);
        }

        @Data
        class Node<T> {

            private T element;
            private Node l;
            private Node r;

            public Node(T element) {
                this(element, null, null);
            }

            public Node(T element, Node<T> l, Node<T> r) {
                this.element = element;
                this.l = l;
                this.r = r;
            }
        }
    }

    @Test
    public void test_1() {
        LinkedBinarySearchTree tree = new LinkedBinarySearchTree();
        tree.add(9);
        tree.add(14);
        tree.add(5);
        tree.add(7);
        tree.add(1);
        tree.add(20);

        System.out.println("preOrder:");
        tree.preOrder(tree.root);
        System.out.println("inOrder:");
        tree.inOrder(tree.root);
        System.out.println("postOrder:");
        tree.postOrder(tree.root);

        System.out.println("max:" + tree.searchMax().element);
        System.out.println("min:" + tree.searchMin().element);
        System.out.println(tree.searchPre(14).element);

        System.out.println("depth:" + tree.maxDepth());
    }
}
