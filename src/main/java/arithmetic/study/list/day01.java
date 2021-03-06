package arithmetic.study.list;

import lombok.Data;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/***
 * 缓存是一种提高数据读取性的技术，在硬件设计、软件开发中都有着非常广泛的应用，比如常见的CPU缓存、数据库缓存、浏览器缓存等。
 * 缓存的大小是有限的，当缓存被用满时，哪些数据应该被清除出去？哪些数据应该保留？这就需要缓存淘汰策略来决定。
 * 常见的策略有三种：先进先出策略FIFO（first in, first out），最少使用策略LFU（least frequently used），最近最少使用策略LRU（least recently used）。
 * 今天的开篇问题就是：如何用链表来实现LRU缓存淘汰策略？
 */
public class day01 {

    /***
     * 五花八门的链表结构
     * 相比数组，链表是一种稍微复杂一点的数据结构。对于初学者来说，掌握起来也要比数组难一些。
     * 这两个非常基础、非常常用的数据结构，我们常常会放在一起来进行比较，所以我们先来看看这两者的区别。
     * 我们先从底层的存储结构上来看一看。
     * 数组需要一块连续的内存空间来储存，对内存的要求比较高，如果我们申请一个100MB大小的数组，当内存中没有连续的、足够大的存储空间时，即便内存空间大于100MB，仍然会申请失败。
     * 而链表恰恰相反，它并不需要一块连续的内存空间，它通过"指针"将一组零散的内存块串联起来使用，所以如果我们申请100MB的链表，根本不会有问题。
     */

    /***
     * 链表的结构五花八门，今天重点介绍三种最常见的链表结构，它们分别是单链表、双向链表和循环链表。
     *
     * 1.单链表
     * 我们刚刚讲到，链表通过指针将一组零散的内存块串联起来。其中，我们把内存块称为链表的"结点"。
     * 为了将所有结点串起来，每个链表的结点除了存储数据之外，还需要记录链表上下一个结点的地址，我们把这个记录下个结点地址的指针叫作"后继指针 next"。
     * 其中有两个结点比较特殊，它们分别是第一个结点和最后一个结点，我们习惯性的把第一个结点叫作头结点，把最后一个结点叫作尾结点。
     * 其中，头结点用来记录链表的基地址，有了它我们就可以遍历得到整条链表。而尾结点特殊的地方是，指针不是指向下一个结点，而是指向一个空地址null，表示这个链表上的最后一个结点。
     *
     * 与数组一样，链表也支持数据的查找、插入和删除操作。
     * 我们知道，数组插入、删除的时候，为了保证内存数据的连续性，需要做大量的数据搬移，所以时间复杂度是O(n)。而在链表中插入或删除数据，我们就不需要为了保持内存数据的连续性
     * 而进行大量的搬移工作，因为链表的存储空间本来就是不连续的，所以链表插入和删除是非常快的。例如：
     * 我们在链表a和b结点之间插入一个结点x，这个过程只需要改变a结点的next为x，然后x结点的next为b即可。
     * 同理，我们在a、c结点之间删除结点b，这个过程只需要将a结点的next改为c即可。
     *
     * 但是，有利就有弊。链表想要随机访问第k个元素，就没数组那么高效了。
     * 因为链表中的数据并非连续存储的，所以无法像数组那样，根据首地址和下标再通过寻址公式就能直接计算出对应的内存地址，而是需要根据指针一个结点一个结点的依次遍历，直到找到为止。
     * 你可以把链表想象成一个队伍，队伍中的每个人只知道自己后面的人是谁，所以当我们希望知道第k位的人是谁的时候，我们就需要从第一个人开始，一个一个往下数。
     * 所以链表随机访问的性能没有数组好，需要O(n)的时间复杂度。
     *
     */
    @Test
    public void method1() {
        SinglyLinkedList linkedList = new SinglyLinkedList();
        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(4);
        linkedList.print();
        linkedList.addNode(3, 2);
        linkedList.print();
        linkedList.deleteNode(0);
        linkedList.print();
    }

    /***
     * 循环链表
     * 循环链表是一种特殊的单链表，实际上，循环链表也很简单，它跟单链表唯一的区别就在结尾点。
     * 我们知道，单链表的尾结点指向空指针地址，表示就这就是最后的结点了。而循环链表的尾结点指针指向链表的头结点。
     * 循环链表的优点是从尾结点到头结点比较方便，当处理的数据具有环形结构特点时，就特别适合采用循环链表，比如约瑟夫问题。
     */
    @Test
    public void method2() {
        CycleLinkedList cycleLinkedList = new CycleLinkedList();
        cycleLinkedList.init(10000);
        cycleLinkedList.print();
        cycleLinkedList.Jusephus(0, 3);
        cycleLinkedList.print();
    }

    /***
     * 双向链表
     * 单链表只有一个方向，结点只有一个后继指针next指向后面的结点。
     * 而双向链表，顾名思义，它支持两个方向，每个结点不止有一个后继指针next指向后面的结点，还有一个前驱指针prev指向前面的结点。
     * 双向链表需要额外的两个空间来存储前驱指针prev和后继指针next，所以，如果同样多的数据，双向链表比单链表占用更多的空间。
     * 从结构上看，双向链表可以支持O(1)时间复杂度的情况下找到前驱结点，正是这样的特点，也是双向链表在某些情况下的插入、删除等操作都要比单链表简单、高效。
     *
     */
    @Test
    public void method3() {
        MyLinkedList<String> linkedList = new MyLinkedList<String>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.addFirst("0");
        linkedList.add("4");

        linkedList.remove(3);
        linkedList.print();
    }

    /***
     * 基于链表实现LRU缓存淘汰算法
     * 思路：
     * 我们维护一个有序的单链表，越靠近链表尾部的结点是越早访问的。当有一个新的数据被访问时，我们从链表头开始顺序遍历链表。
     * 如果数据之前已经存在于链表中，我们遍历得到这个数据的结点，并将原来的位置删除，然后再插入链表头部。
     * 如果数据不在链表中，又分为两种情况。
     * 如果缓存链表未满，直接插入头部。如果缓存链表满了，删除最后一个结点的数据，再将新数据插入头部。
     */

    @Data
    class FIFO {

        private Node head;
        private int count;
        private int size;

        public FIFO(int size) {
            this.count = 0;
            this.size = size;
        }

        public void push(int data) {
            if (head == null) {
                head = new Node(data);
                ++count;
                return;
            }

            if (count == size) {
                head = head.next;
            } else {
                ++count;
            }

            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            Node newNode = new Node(data);
            temp.next = newNode;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            Node temp = head;
            while (temp != null) {
                stringBuilder.append(temp.data + ", ");
                temp = temp.next;
            }
            return stringBuilder.toString();
        }

        @Data
        class Node {

            public Node(int data) {
                this.data = data;
            }

            private int data;
            private Node next;
        }
    }

    @Test
    public void FIFO_test() {
        FIFO fifo = new FIFO(10);
        for (int i = 0; i < 20; ++i) {
            fifo.push(i);
        }
        System.out.println(fifo.toString());
    }

    @Data
    class LFU {

        private Node head;
        private int count;
        private int size;

        public LFU(int size) {
            this.count = 0;
            this.size = size;
        }

        public void push(int data) {
            if (head == null) {
                head = new Node(data);
                ++count;
                return;
            }

            Node temp = head;
            while (temp != null){
                if (temp.data == data){
                    temp.count();
                    sort();
                    return;
                }
                temp = temp.next;
            }

            Node newNode = new Node(data);
            if (count == size) {
                newNode.next = head.next;
                head = newNode;
            } else {
                ++count;
                temp = head;
                while (temp.next != null){
                    temp = temp.next;
                }
                temp.next = newNode;
            }
        }

        public String toString(){
            StringBuilder stringBuilder = new StringBuilder();
            Node temp = head;
            while (temp != null) {
                stringBuilder.append(temp.data + ":" + temp.frq + ", ");
                temp = temp.next;
            }
            return stringBuilder.toString();
        }

        private void sort(){
           synchronized (head){
               Node temp = head;
               while (temp.next != null){
                   if (temp.frq > temp.next.frq){
                       int sm_date = temp.next.data;
                       int sm_frq = temp.next.frq;
                       temp.next.data = temp.data;
                       temp.next.frq = temp.frq;
                       temp.data = sm_date;
                       temp.frq = sm_frq;
                   }
                   temp = temp.next;
               }
           }
        }

        @Data
        class Node {

            public Node(int data) {
                this.data = data;
            }

            public void count() {
                this.frq++;
            }

            private int data;
            private int frq = 1;  //频率
            private Node next;
        }
    }

    @Test
    public void LFU_test(){
        LFU lfu = new LFU(10);
        for (int i=0; i<10; ++i){
            lfu.push(i);
            lfu.push(i);
        }
        System.out.println(lfu.toString());

        lfu.push(0);
        System.out.println(lfu.toString());

        lfu.push(10);
        System.out.println(lfu.toString());
    }

    @Data
    class LRU{

    }
}
