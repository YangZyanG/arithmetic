package arithmetic.study.list;

/***
 * 使用循环链表解决约瑟夫圆环问题
 */
public class CycleLinkedList {

    private Node head;

    private Node rear;

    private int length;

    public void init(int total){
        head = new Node(0);
        rear = new Node(total - 1);
        head.next = rear;
        rear.next = head;
        length = total;

        Node node;
        for (int i=1; i<total-1; ++i){
            node = new Node(i);
            if(head.next == rear){
                node.next = rear;
                head.next = node;
            }else{
                Node temp = head;
                while (temp.next != rear){
                    temp = temp.next;
                }
                node.next = rear;
                temp.next = node;
            }
        }
    }

    public void print(){
        Node temp = head;
        for (int i=0; i<length; ++i){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    /***
     * 从k位置开始数，每次数到m的人自杀
     * @param k
     * @param m
     */
    public void Jusephus(int k, int m){

        int i = 0;
        Node temp = head;
        while (i != k){
            temp = temp.next;
            ++i;
        }

        while (length >= m){
            for (i=1; i<m; ++i){
                temp = temp.next;
            }
            System.out.println("自杀编号为：" + temp.next.data);
            temp.next = temp.next.next;
            --length;
        }

        head = temp;
    }
}
