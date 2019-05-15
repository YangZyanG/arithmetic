package arithmetic.study.queue;

/***
 * 数组实现循环队列
 */
public class CycleQueue {

    private int head;
    private int rear;
    private int n;

    private String[] items;

    public CycleQueue(int capacity){
        items = new String[capacity];
        n = capacity;
    }

    public boolean enqueue(String item){

        if((rear+1)%n == head)
            return false;

        items[rear] = item;
        rear = (rear+1)%n;

        return true;
    }

    public String dequeue(){

        if(rear == head)
            return null;

        String item = items[head];
        head = (head+1)%n;

        return item;
    }
}
