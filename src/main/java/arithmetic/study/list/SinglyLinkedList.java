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
            temp = head.next;
        }
        temp.next = node;
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
}
