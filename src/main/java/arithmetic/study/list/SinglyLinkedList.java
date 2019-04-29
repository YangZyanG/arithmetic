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

    public boolean cycleCheck(){
        Node handFast = head;
        Node handSlow = head;

        while (null!=handFast.next.next && null!=handSlow.next){
            handFast = handFast.next.next;
            handSlow = handSlow.next;
            if(handFast.data == handSlow.data)
                return true;
        }

        return false;
    }

    public SinglyLinkedList merge(SinglyLinkedList linkedList1, SinglyLinkedList linkedList2){
        Node head1 = linkedList1.head;
        Node head2 = linkedList2.head;
        SinglyLinkedList result = new SinglyLinkedList();

        while (head1!=null && head2!=null){
            if (head1.data < head2.data){
                result.addNode(head1.data);
                head1 = head1.next;
            }else if(head1.data > head2.data){
                result.addNode(head2.data);
                head2 = head2.next;
            }else{
                result.addNode(head1.data);
                head1 = head1.next;
                head2 = head2.next;
            }
        }

        if(head1 != null){
            while (head1 != null){
                result.addNode(head1.data);
                head1 = head1.next;
            }
        }
        if(head2 != null){
            while (head2 != null){
                result.addNode(head2.data);
                head2 = head2.next;
            }
        }

        return result;
    }

    public SinglyLinkedList merge1(SinglyLinkedList linkedList1, SinglyLinkedList linkedList2){
        SinglyLinkedList result = new SinglyLinkedList();
        result.head = merge1(linkedList1.head, linkedList2.head);
        return result;
    }

    public Node merge1(Node head1, Node head2){

        if(head1==null && head2==null)
            return null;
        if(head1 == null)
            return head2;
        if(head2 == null)
            return head1;

        Node head = null;
        if(head1.data > head2.data){
            head = head2;
            head.next = merge1(head1, head2.next);
        }else if(head1.data < head2.data){
            head = head1;
            head.next = merge1(head1.next, head2);
        }else{
            head = head1;
            head.next = merge1(head1.next, head2.next);
        }

        return head;
    }

    public int backDelete(int index){
        int n = length() - index;
        Node prev = head;

        for (int i=0; i<n-1; ++i){
            prev = prev.next;
        }
        int result = prev.next.data;
        prev.next = prev.next.next;

        return result;
    }
}
