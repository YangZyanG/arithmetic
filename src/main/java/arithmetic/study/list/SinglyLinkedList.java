package arithmetic.study.list;

/***
 * 单链表实现
 */
public class SinglyLinkedList {

    private Node head;

    /***
     * 添加
     * @param data
     */
    public void addNode(int data){

        Node node = new Node(data);

        if(null == head){
            head = node;
            return;
        }

        Node temp = head;
        while (null != temp.next){
            temp = temp.next;
        }
        temp.next = node;
    }

    /***
     * 添加到指定位置
     * @param data
     * @param index
     */
    public void addNode(int data, int index){

        if(index<0 || index>length()){
            throw new RuntimeException();
        }

        Node node = new Node(data);

        int i = 0;
        Node temp = head;

        while (true){
            if(i++ == (index-1)){
                node.next = temp.next;
                temp.next = node;
                break;
            }else {
                temp = temp.next;
            }
        }

    }

    /***
     * 删除
     * @param index
     * @return
     */
    public boolean deleteNode(int index){
        if (index<0 || index>length())
            return false;

        if (0 == index){
            head = head.next;
            return true;
        }

        int i = 1;
        Node node = head;
        while (null != node.next){

            if (i++ == index){
                node.next = node.next.next;
                return true;
            }

            node = node.next;
        }

        return false;
    }

    /***
     * 长度
     * @return
     */
    public int length(){
        int length = 0;
        Node node = head;

        while (null != node){
            ++length;
            node = node.next;
        }

        return length;
    }

    /***
     * 打印
     */
    public void print(){

        Node node = head;

        while (null != node){
            System.out.println(node.data);
            node = node.next;
        }
    }

    public void reverse(){
        head = reverse(head);
    }

    public Node reverse(Node head){

        if(null==head || null==head.next){
            return head;
        }

        Node temp = head.next;
        Node newNode = reverse(temp);
        temp.next = head;
        head.next = null;
        return newNode;
    }

    public void reverse1(){
        head = reverse1(head);
    }

    public Node reverse1(Node head){
        Node prev = null;
        Node next = null;

        while (null != head){
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }
}
