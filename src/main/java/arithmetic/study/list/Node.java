package arithmetic.study.list;

/***
 * 链表结点实现类
 * 编写一个Node类来充当结点模型
 */
public class Node {

    //结点存放的数据
    public int data;

    //结点的next，默认为null
    public Node next;

    public Node(int data){
        this.data = data;
    }
}
