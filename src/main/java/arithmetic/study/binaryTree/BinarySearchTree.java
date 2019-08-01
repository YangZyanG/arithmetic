package arithmetic.study.binaryTree;

/***
 * 二叉查找树
 */
public class BinarySearchTree {

    private Node root;

    public Node find(Node node){

        Node temp = root;
        while (temp != null){

            if (temp.getData() > node.getData())
                temp = temp.getLeft();
            else if (temp.getData() < node.getData())
                temp = temp.getRight();
            else
                return temp;

        }

        return null;
    }

    /***
     * 这里暂时不考虑重复数据
     * @param data
     */
    public void insert(int data){

        if (root == null){
            root = new Node(data);
            return;
        }

        Node temp = root;
        Node node;
        while (temp != null){

            if (temp.getData() > data){
                if (temp.getLeft() == null){
                    node = new Node(data);
                    temp.setLeft(node);
                    return;
                }
                temp = temp.getLeft();
            }else{
                if (temp.getRight() == null){
                    node = new Node(data);
                    temp.setRight(node);
                    return;
                }
                temp = temp.getRight();
            }

        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
