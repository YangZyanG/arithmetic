package arithmetic.study.stack;

/***
 * 链表实现栈
 */
public class LinkedStack {

    private Node top = null;

    /***
     * 入栈
     * @param data
     * @return
     */
    public boolean push(int data){

        Node newNode = new Node(data, null);

        if(null == top){
            top = newNode;
        }else{
            newNode.next = top;
            top = newNode;
        }

        return true;
    }

    /***
     * 出栈
     * @return
     */
    public int pop(){

        if(null == top)
            return -1;

        int result = top.getData();
        top = top.next;

        return result;
    }

    private static class Node{
        private int data;
        private Node next;

        public Node(int data, Node next){
            this.data = data;
            this.next = next;
        }

        public int getData(){
            return data;
        }
    }
}
