package arithmetic.study.binaryTree;

/***
 * 链表实现的二叉树
 */
public class ListTree {

    private Node root;

    public Node addRoot(int data){
        if (root != null){
            root.data = data;
            return root;
        }

        Node root = new Node(data);
        return root;
    }

    public Node addNode(Node parentNode, NodeType type, int data){
        if (parentNode == null)
            return null;

        Node node = new Node(parentNode, type, data);
        return node;
    }

    /***
     * 前序遍历 自己 -> 左子树 -> 右子树
     * @param node
     */
    public void preOrder(Node node){
        if (node == null)
            return;

        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    /***
     * 中序遍历 左子树 -> 自己 -> 右子树
     * @param node
     */
    public void inOrder(Node node){
        if (node == null)
            return;

        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }

    /***
     * 后序遍历 左子树 -> 右子树 -> 自己
     * @param node
     */
    public void postOrder(Node node){
        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data);
    }

    class Node{

        private int data;

        private Node left;

        private Node right;

        public Node(int data){
            this.data = data;
        }

        public Node(Node parentNode, NodeType type, int data){
            this.data = data;

            if (type == NodeType.LEFT)
                parentNode.left = this;
            else
                parentNode.right = this;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    enum  NodeType{
        LEFT,
        RIGHT
    }
}
