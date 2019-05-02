package arithmetic.study.queue;

import org.junit.Test;

/***
 * 队列在线程池等有限资源池中的应用
 * 我们向固定大小的线程池请求一个线程时，如果线程池没有空闲资源了，这个时候线程池如何处理这个请求？
 * 是拒绝请求还是排队请求？各种处理策略又是怎么实现的呢？
 */
public class day01 {

    /***
     * 如何理解队列？
     * 先进先出者，就是典型的队列。
     * 我们知道栈只支持两个操作：入栈和出栈。队列跟栈非常相似，支持的操作也很有限，最基本的操作也是两个：入队和出队。
     * 入队：放一个数据到队列尾部
     * 出队：从队列头部取一个元素
     */

    /***
     * 跟栈一样，，队列可以用数组实现，也可以用链表实现。
     * 同样，用数组实现的队列叫顺序队列，用链表实现的队列叫链式队列。
     */

    @Test
    public void method1(){
        //数组实现
        ArrayQueue arrayQueue = new ArrayQueue(20);
        arrayQueue.enqueue("1");
        arrayQueue.enqueue("2");
        arrayQueue.enqueue("3");
        System.out.println(arrayQueue.dequeue());
        System.out.println(arrayQueue.dequeue());

        //链表实现
        LinkedQueue<String> linkedQueue = new LinkedQueue<String>();
        linkedQueue.enqueue("1");
        linkedQueue.enqueue("2");
        linkedQueue.enqueue("3");
        linkedQueue.dequeue();
        linkedQueue.dequeue();
        linkedQueue.print();
    }
}
