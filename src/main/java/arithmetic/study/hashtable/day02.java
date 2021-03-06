package arithmetic.study.hashtable;

public class day02 {

    /***
     * 装载因子
     * 一般情况下，我们会尽可能保证散列表中有一定比例的空闲槽位，我们用装载因子来表示空位的多少。
     * 装载因子 = 插入散列表的元素个数/散列表的长度
     * 其中装载因子和散列表的长度是HashMap初始化时可以选择的两个参数。
     * HashMap初始化后，当"插入散列表的元素个数/散列表的长度 > 装载因子"的时候，HashMap会进行rehash操作，将Map的熔炼扩大为原来的2倍。
     * 如果装载因子越大，说明空闲槽位越少，冲突就越多，散列表的性能就会下降。
     */

    /***
     * HashMap的初始化容量为什么一定是2的幂次方？
     * 因为如果不是2的幂次方，会使元素在map中分布不均，大大增加hash碰撞的概率。
     */

    /***
     * 散列表的查询不能笼统地说成O(1)，它跟散列函数、装载因子、散列冲突等都有关系。
     * 如果散列函数设计的不好，或者装载因子过高，都可能导致散列冲突发生的概率升高，查询效率下降。
     *
     * 在极端情况下，有些恶意的攻击者，还有可能通过精心构造的数据，使得所有的数据经过散列函数之后，都散列到同一个槽里。
     * 如果我们使用的时基于链表的冲突解决方法，那这个时候，散列表就会退化为链表，查询的时间复杂度就会从O(1)变成O(n)。
     */

    /***
     * 如何选择冲突解决方法？
     * 两种主要的散列碰撞的解决方法，开放寻址法和链表法，这两种方法在实际的软件开发中都非常常用。
     * 比如，Java中LinkedHashMap就采用了链表法解决冲突，ThreadLocalMap是通过线性探测的开放寻址法来解决冲突的。
     *
     * 当数据量比较小、装载因子小的时候，适合采用开放寻址法。
     * 链表法适合储存大对象、大数据量，而且相对于开放寻址法，它更灵活，有许多的优化策略，比如用红黑是代替链表等。
     */
}
