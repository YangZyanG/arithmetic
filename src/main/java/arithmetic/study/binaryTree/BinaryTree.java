package arithmetic.study.binaryTree;

/***
 * 链表实现的二叉树
 */
public class BinaryTree {

    private Node root;

    public Node addRoot(int data){
        if (root != null){
            root.setData(data);
            return root;
        }

        return new Node(data);
    }

    public Node addNode(Node parentNode, NodeType type, int data){
        if (parentNode == null)
            return null;

        Node node = new Node(data);
        if (type == NodeType.LEFT)
            parentNode.setLeft(node);
        else
            parentNode.setRight(node);

        return node;
    }

    /***
     * 前序遍历 自己 -> 左子树 -> 右子树
     * @param node
     */
    public void preOrder(Node node){
        if (node == null)
            return;

        System.out.println(node.getData());
        preOrder(node.getLeft());
        preOrder(node.getLeft());
    }

    /***
     * 中序遍历 左子树 -> 自己 -> 右子树
     * @param node
     */
    public void inOrder(Node node){
        if (node == null)
            return;

        inOrder(node.getLeft());
        System.out.println(node.getData());
        inOrder(node.getRight());
    }

    /***
     * 后序遍历 左子树 -> 右子树 -> 自己
     * @param node
     */
    public void postOrder(Node node){
        if (node == null)
            return;

        postOrder(node.getLeft());
        postOrder(node.getRight());
        System.out.println(node.getData());
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
