package arithmetic.study.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 * 数组实现的阻塞队列
 */
public class BlockingArrayQueue<T> {

    private Object items[];
    private int size;
    private int head;
    private int rear;

    private final Lock lock;
    private final Condition notEmpty;
    private final Condition notFull;

    public BlockingArrayQueue(){
        this(10);
    }

    public BlockingArrayQueue(int capacity){
        items = new Object[capacity];
        size = capacity;
        head = 0;
        rear = 0;

        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    public void enqueue(T item){

        lock.lock();
        try{
            if(rear == size){
                if(head == 0){
                    System.out.println("队列已满");
                    notFull.await();
                }

                //整理数组
                for(int i=head; i<rear; ++i){
                    items[i - head] = items[i];
                }
                rear -= head;
                head = 0;
            }

            items[rear] = item;
            ++rear;
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T dequeue(){
        T t = null;

        lock.lock();
        try{
            while (head == rear){
                System.out.println("队列为空");
                notEmpty.await();
            }

            t = (T) items[head];
            ++head;
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return t;
    }
}
