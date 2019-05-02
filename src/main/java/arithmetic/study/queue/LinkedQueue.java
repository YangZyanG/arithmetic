package arithmetic.study.queue;

/***
 * 链表实现队列
 */
public class LinkedQueue<E> {

    private transient Node<E> head;
    private transient Node<E> rear;

    public LinkedQueue(){
        rear = head = new Node<E>(null);
        head.next = rear;
    }

    public void enqueue(E data){

        if (null == head.data){
            head.data = data;
            return;
        }

        rear = rear.next = new Node<E>(data);
    }

    public E dequeue(){

        if(null == head)
            return null;

        E data = head.data;
        head = head.next;

        return data;
    }

    public void print(){
        while (head != null){
            System.out.println(head.data);
            head = head.next;
        }
    }

    static class Node<E>{
        E data;
        Node<E> next;

        Node(E data){
            this.data = data;
        }
    }
}
