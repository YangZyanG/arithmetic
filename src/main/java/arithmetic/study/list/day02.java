package arithmetic.study.list;


import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/***
 * 如何轻松正确的写出链表代码？
 */
public class day02 {

    /***
     * 1.理解指针或引用的含义
     * 事实上，看懂链表的结构并不难，但是一旦把它和引用混在一起，就很容易让人摸不着头脑。所以，要想写对链表代码，首先就要理解引用。
     * 将某个变量赋给引用，实际上就是讲这个变量的地址赋给引用，或者反过来说，引用中储存了这个变量的内存地址。引用指向了这个变量，通过引用就能找到这个变量。
     * 在编写链表代码的时候，我们经常会有这样的代码：
     * p.next = q;
     * 这行代码是说p结点中的next存储了q结点的内存地址。
     * 还有一个更复杂的：
     * p.next = p.next.next;
     * 这行代码是说p结点的next存储了p的下下个结点的内存地址。
     */

    /***
     * 2.警惕引用丢失或内存泄漏（重点）
     * 不知道你有没有这样的感觉，写链表代码的时候，引用引来引去，一会儿就不知道引用到哪了去了。所以，我们写的时候，一定注意不要弄丢了引用。
     * 比如，我们希望在a、b结点之间插入一个x结点，如果代码是以下这样，就会指针丢失：
     * a.next = x;
     * x.next = a.next;
     * 初学者经常在这犯错，第一行代码之后，a结点的next已经没有指向b结点了，第二行代码就变成了x结点自己指向自己，整个链表也就断成了两半。
     * 所以，插入的时候，一定要注意操作的顺序。
     * 要先将结点x的next指向b结点，然后再把a结点的next指向结点x，这样才不会引用丢失导致内存泄漏。
     * x.next = a.next;
     * a.next = x;
     * 同理，删除链表结点时，也一定要记得手动释放内存空间，否则，也会出现内存泄漏的问题。
     */

    /***
     * 3.利用哨兵简化实现难度
     * 首先，我们先来回顾一下单链表的插入和删除。
     * 如果我们在a结点后面插入一个新的结点，只需要下面两行代码就能搞定：
     * x.next = a.next;
     * a.next = x;
     * 但是，当我们要向一个空链表中插入第一个结点，刚刚的逻辑就不能用了，我们需要进行下面的特殊处理。
     * 其中head表示链表的头结点：
     * if(null == head){
     *     head = x;
     * }
     * 从这段代码我们可以发现，对于单链表的插入，第一个结点和其他结点的插入逻辑是不一样的。
     * 我们再来看看删除结点的操作，如果要删除a结点后面的结点，我们只需一样代码就能搞定：
     * a.next = a.next.next;
     * 但是，如果链表中只剩一个结点，前面的代码就不适用了，我们也需要做特殊处理：
     * if(null == head.next){
     *     head = null;
     * }
     * 从前面一步一步的分析，我们可以看出，对链表的插入和删除操作，需要对插入的第一个结点和删除的最后一个结点做特殊处理。
     * 这样代码实现起来就会很繁琐，不简洁，而且也容易因考虑不全而出错，如何来解决这个问题呢？
     * 这时候我们只需要引入哨兵即可，具体怎么做呢？就是把哨兵当作边界点，看如下例子。
     */

    /***
     * 在数组a中查找值n并返回，n不存在则返回-1
     * @param a
     * @param n
     * @return
     */
    //常规操作
    public int find1(int[] a, int n){

        for (int i=0; i<a.length; ++i){
            if(n == a[i]){
                return a[i];
            }
        }

        return -1;
    }

    //哨兵操作
    public int find2(int[] a, int n){

        int m = a.length - 1;
        int last = a[m];
        a[m] = n;

        for(int i=0; ; ++i){
            if(n == a[i]){
                if(i != m)
                    return a[i];
                break;
            }
        }

        if(last == n){
            return last;
        }

        return -1;
    }
    /***
     * 看上面两个方法，功能一样，当数据量很大的时候，第二个方法的效率就明显高得多，因为第二个方法循环的时候少执行了 i<a.length。
     */

    /***
     * 4.重点留意边界条件处理
     * 经常用来检查链表代码是否正确的边界条件有这样几个：
     * 如果链表为空时，代码是否能正常工作？
     * 如果链表只包含一个结点，代码是否能正常工作？
     * 如果链表只包含两个结点，代码是否能正常工作？
     * 代码逻辑在处理头结点和尾结点的时候是否能正常工作？
     */

    /***
     * 5.多写多练，没有捷径
     * 5个常见的链表操作
     * 单链表反转
     * 链表中环的检测
     * 两个有序链表的合并
     * 删除链表倒数第n个结点
     * 求链表的中间结点
     */

    /***
     * 单链表反转
     */
    @Test
    public void method1(){
        SinglyLinkedList linkedList = new SinglyLinkedList();
        for(int i=1; i<=10; ++i){
            linkedList.addNode(i);
        }
        linkedList.print();
        //递归
        linkedList.reverse();
        System.out.println();
        linkedList.print();
        //遍历
        linkedList.reverse1();
        System.out.println();
        linkedList.print();
    }
}
