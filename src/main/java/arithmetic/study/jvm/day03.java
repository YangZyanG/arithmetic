package arithmetic.study.jvm;

/***
 * GC算法 垃圾收集器
 */
public class day03 {

    /***
     * JVM中，程序计数器、虚拟机栈、本地方法栈都是随线程而生随线程而灭的，栈帧随方法的进入和退出做入栈和出栈的操作，实现了自动的内存清理。
     * 因此，我们的内存垃圾回收主要集中于Java堆中和方法区中，在程序运行期间，这部分内存的分配和使用都是动态的。
     */

    /***
     * 对象存活判断
     * 判断对象存活一般有两种方式：
     * 1.引用计数
     * 每个对象都有一个引用计数属性，新增一个引用时计数加1，引用释放时计数减1，计数为0时可以回收。此方法简单，无法解决对象相互循环引用的问题，就像如下代码：
     * {
     *     Dog dog = new Dog();
     *     Tail tail = new Tail();
     *     dog.tail = tail;
     *     tail.dog = dog;
     * }
     * 在这里，dog拿着tail对象的引用，tail拿着dog对象的引用，双方都在等待着对方先被回收，当方法执行完后，垃圾管理器就始终无法回收这两个实际无用的对象。
     * 所以这种情况只能靠第二种方式判断对象是否存活。
     *
     * 2.可达性分析
     * 从GC Roots开始向下搜索，搜索所走过的路径称为引用链，当一个对象到GC Roots没有任何引用链相连时，则证明此对象是不可用的，不可达对象。
     * 在Java语言中，GC Roots包括：
     * 虚拟机栈中引用的对象
     * 方法区中类静态属性实体引用的对象
     * 方法区中常量引用的对象
     * 本地方法栈中JNI引用的对象
     *
     * 针对上面对象相互循环引用的问题，当方法执行完后，对应的栈随线程关闭，那么此时虚拟机栈中就没有引用指向这两个对象，那么这两个对象就可以被回收了。
     */

    /***
     * 垃圾收集算法
     * 1.标记-清除算法
     * "标记-清除"算法，如它的名字一样，算法分为"标记"和"清除"两个阶段。首先标记出所有需要回收的对象，在标记完成后统一回收掉所有被标记的对象。
     * 之所以说它是最基础的算法，是因为后续的收集算法都是基于这种思路并对其缺点进行改进而得到的。
     * 它的缺点主要有两个：
     * 一是效率问题，标记和清除过程的效率都不高。
     * 二是空间问题，标记清除后会产生大量不连续的内存碎片，当程序以后的运行过程中需要分配较大对象时无法找到足够的连续内存，从而不得不提前出发另一次垃圾回收。
     *
     * 2.复制算法
     * "复制"的收集算法，它将可用内存按容量划分为大小相等的两块，每次只使用其中一块。当这一块的内存用完后，就将还活着的对象复制到另一块上面，然后再把前一个内存空间一次清理掉。
     * 这样使得每次都是对其中的一块内存空间进行回收，内存分配时也不用考虑内存碎片等复杂情况，只要移动堆顶指针，按顺序分配内存即可，实现简单，运行高效。
     * 只是这种算法的代价是将内存缩小为原来的一半，持续复制长生存期的对象则导致效率降低。
     *
     * 3.标记-压缩
     * 复制收集算法在对象存活率较高时就要执行较多的复制操作，效率将会变低。
     * 更关键的是，如果不想浪费50%的空间，就需要有额外的空间进行分配担保，以应对被使用的内存中所有对象100%存活的极端情况，所以在老年代一般不能直接选用这种算法。
     * 根据老年代的特点，有人提出了一种"标记-压缩"算法，标记过程依然与"标记-清除"算法一样，但后续步骤不是对可回收对象进行清理，而是让所有存活的都向后一端移动，然后直接清理掉端边界以外的内存。
     */

    /***
     * 分代收集算法
     * GC分代的基本假设：绝大部分对象的生命周期都非常短暂，存活时间短。
     * "分代收集"算法，把Java堆分为新生代和老年代，这样就可以根据各个年代的特点采用最适当的收集算法。
     * 在新生代中，每次垃圾收集时都发现有大批对象死去，只有少量存活，那就选用复制算法，只需要付出少量存活对象的复制成本就可以完成收集。
     * 而老年代中对象存活率较高、没有额外空间对它进行分配担保，就必须使用"标记-清除"或"标记-压缩"算法进行回收。
     */

    /***
     * 垃圾收集器
     * 如果说收集算法是内存回收的方法论，垃圾收集器就是内存回收的具体实现。
     *
     * 1.Serial收集器
     * 串行收集器是最古老、最稳定以及效率最高的收集器，可能会产生较长时间的停顿，只使用一个线程去回收。
     * 新生代、老年代使用串行回收，新生代复制算法，老年代标记-压缩算法，垃圾收集的过程中会Stop The World（暂停服务）
     * 参数控制：
     * -XX:+UseSerialGC 串行收集器
     *
     * 2.ParNew收集器
     * ParNew收集器其实就是Serial收集器的多线程版本，新生代并行，老年代串行，新生代复制算法，老年代标记-压缩算法。
     * 参数控制：
     * -XX:+UseParNewGC ParNew收集器
     * -XX:ParallelGCThreads 限制线程数量
     *
     * 3.Parallel收集器
     * Parallel Scavenge收集器类似ParNew收集器，Parallel收集器更关注系统的吞吐量。
     * 可以通过参数来打开自适应调节策略，虚拟机会根据当前系统的运行情况收集性能监控信息，动态调节这些参数以提供最合适的停顿时间或最大的吞吐量。
     * 也可以通过参数控制GC的时间不大于多少毫秒或者比例，新生代复制算法，老年代标记-压缩。
     * 参数控制：
     * -XX:+UseParallelGC 使用Parallel收集器 + 老年代串行
     *
     * 4.Parallel Old收集器
     * Parallel Old是Parallel Scavenge收集器的老年代版本，使用多线程和"标记-压缩"算法，这个收集器是在JDK1.6才开始提供。
     * 参数控制：
     * -XX:+UseParallelOldGC 使用Parallel收集器 + 老年代并行
     *
     * 5.CMS收集器
     * CMS（Concurrent Mark Sweep）收集器是一种以获取最短回收停顿时间为目标的收集器。
     * 目前很大一部分的Java应用都集中在互联网网站或B/S系统的服务端上，这类应用尤其重视服务的响应速度，希望系统停顿时间最短，以给用户带来较好的体验。
     * CMS收集器是基于"标记-清除"算法实现的，它的运作过程相对于前面几种收集器来说要更复杂一些，整个过程分为4个步骤，包括：
     * 初始标记
     * 并发标记
     * 重新标记
     * 并发清除
     * 其中初始标记和重新标记这两个步骤仍然需要"Stop The World"。
     * 初始标记仅仅只是标记一下GC ROOTS能直接关联到的对象，速度很快，并发标记阶段就是GC ROOTS Tracing的过程，判断对象的可达性。
     * 重新标记则是为了修正并发标记阶段，因用户程序继续运作而导致产生标记变动的那一部分对象的标记记录，这个阶段的停顿时间一般比初始标记阶段长一些，但远比并发标记的时间短。
     * 由于整个过程中耗时最长的并发标记和并发清除过程中，收集器线程都可以与用户线程一起做工，所以整体上来说，CMS收集器的内存回收过程是与用户线程一起并发执行的。
     * 优点：并发收集、低停顿
     * 缺点：产生大量空间碎片、并发阶段会降低吞吐量
     * 参数控制：
     * -XX:+UseConcMarkSweepGC 老年代使用CMS收集器，新生代使用ParNew收集器
     * -XX:+UseCMSCompactAtFullCollection Full GC后，进行一次碎片整理，整理过程是独占的，会引起较长时间的停顿。
     * -XX:+CMSFullGCsBeforeCompaction 设置进行几次Full GC后，进行一次碎片整理。
     * -XX:ParallelCMSThreads 设定CMS的线程数量（一般情况约等于可用CPU的数量）。
     *
     * 6.G1收集器
     * G1是目前技术发展的最前沿成果之一，与CMS收集器相比G1收集器有以下特点：
     * 空间整合
     * G1收集器采用标记-压缩算法，不会产生内存碎片，分配大对象时不会因为找不到足够的连续空间而触发下一次GC。
     * 可预测停顿
     * 这是G1的另一大优势，降低停顿时间是G1和CMS共同关注点，但G1除了追求停顿之外，还能建立可预测的停顿时间模型，能让使用者明确指定在一个长度为N毫秒的时间片段内，消耗在
     * 垃圾收集上的时间不得超过N毫秒。
     * 上面提到的垃圾收集器，收集的范围都是整个新生代或者老年代，而G1不再是这样。
     * 使用G1收集器时，Java堆的内存布局与其他收集器有很大的差别，它将整个Java堆划分为多个大小相等的区域，虽然还保留有新生代和老年代的概念，但新生代和老年代不再是物理隔阂了，它们
     * 都是一部分区域（Region）的集合。
     * G1的新生代收集与ParNew类似，当新生代占用达到一定比例时，开始并发收集。和CMS类似，G1收集器收集老年代对象会有短暂停顿。
     *
     */
}