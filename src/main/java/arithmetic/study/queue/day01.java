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

    /***
     * 循环队列
     * 我们刚才用数组来实现队列的时候，在rear=n时，会有数据搬移操作，这样入队操作性能就会收到影响。
     * 那有没有办法能够避免数据搬移呢？我们来看看循环队列的解决思路。
     * 顾名思义，循环队列就是在数组最后一位被数据充填时，如果数组前面还有空位置，按理说是该执行数据搬移了，但循环队列会直接从数组第一位开始储存数据。
     * 通过这样的方法，我们成功的避免了数据搬移，但循环队列的难点是怎样去判断队空和队满。
     *
     * 在非循环队列中，我们判断队列是否为空的条件是head==rear，循环队列判断条件也是这个。
     * 在非循环队列中，队满的判断条件是rear==n&&head==0，但在循环队列中不一样，这有个公式：(rear+1)%n==head
     * 就是rear下标+1后除以n的余数是不是等于head的下标，注意，循环队列队尾保留，不用于储存数据。
     */
    @Test
    public void method2(){
        CycleQueue cycleQueue = new CycleQueue(8);
        cycleQueue.enqueue("1");
    }

    /***
     * 前面讲的内容理论比较多，看起来很难跟实际开发扯上关系，确实，队列这种数据结构很基础，平时的业务也不大可能从零实现一个队列，甚至都不会直接用到。
     * 而一些具有特殊特性的队列应用却比较广泛，比如阻塞队列和并发队列。
     */

    /***
     * 阻塞队列
     * 阻塞队列其实就是队列基础上增加了阻塞操作。
     * 简单来说，从队头取数据会被阻塞，因为没有可取的数据，知道队列中有数据了才能返回。
     * 如果队列已满，那么插入数据会被阻塞，直到队列中有空闲的位置时再插入。
     * 你应该已经发现了，上述定义是一个典型的"生产者-消费者"模式。
     * 具体的实现效果在controller包的BlockingArrayQueueController里面
     */
}
