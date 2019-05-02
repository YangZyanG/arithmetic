package arithmetic.study.queue;

/***
 * 用数组实现队列
 */
public class ArrayQueue {

    private String[] items;
    private int n = 0;

    private int head;
    private int rear;

    public ArrayQueue(int capacity){
        items = new String[capacity];
        n = capacity;
    }

    /***
     * 入队
     * @param item
     * @return
     */
    public boolean enqueue(String item){

        if(rear == n){
            if(head == 0)
                return false;

            for(int i=head; i<rear; ++i){
                items[i - head] = items[i];
            }
            rear -= head;
            head = 0;
        }

        items[rear] = item;
        ++rear;
        return true;
    }

    /***
     * 出队
     */
    public String dequeue(){

        if(rear == head)
            return null;

        String temp = items[head];
        ++head;
        return temp;
    }
}
