package arithmetic.study.test;

import lombok.Data;
import org.junit.Test;

import java.util.Stack;

/**
 * @author yangziyang
 * @since 2020-06-18
 */
public class BinaryTreeTest {

    @Data
    class LinkedBinarySearchTree<T extends Comparable<? super T>> {

        private Node<T> root;

        public void add(T element) {
            root = add(element, root);
        }

        private Node add(T element, Node<T> currentNode) {
            if (currentNode == null)
                return new Node<>(element);

            int compare = element.compareTo(currentNode.element);
            if (compare >= 0) {
                currentNode.r = add(element, currentNode.r);
            } else {
                currentNode.l = add(element, currentNode.l);
            }
            return currentNode;
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
        System.out.println(tree);
    }
}
