package arithmetic.study.reference;

import org.junit.Test;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;


/**
 * @author yangziyang
 * @since 2020-06-09
 * java 引用
 */
public class day01 {

    /***
     * 在java的引用体系中，存在着强引用，软引用，弱引用，虚引用，这4种引用类型。
     * 在正常的使用过程中，我们定义的类型都是强引用的，这种引用类型在回收中，只有当其它对象没有对这个对象的引用时，才会被GC回收掉。
     *
     * 在Java里，当一个对象object被创建时，它被放在Heap里，当GC运行的时候，如果发现没有任何引用指向object，object就会被回收以腾出内存空间。
     * 或者换句话说，一个对象被回收，必须满足两个条件:
     * (1)没有任何引用指向它
     * (2)GC被运行
     *
     * 在现实情况写代码的时候，我们往往通过把所有指向某个对象的引用置空来保证这个对象在下次GC运行的时候被回收。
     * Object object = new Object();
     * //  do something
     * object = null;
     * 但是，手动置空对象对于程序员来说，是一件繁琐且违背自动回收的理念的。
     * 对于简单的情况，手动置空是不需要程序员来做的，因为在java中，对于简单对象，当调用它的方法执行完毕后，指向它的引用会被从stack中popup，所以他就能在下一次GC执行时被回收了。
     *
     * 但是，也有特殊例外。
     * 当使用cache的时候，由于cache的对象正是程序运行需要的，那么只要程序正在运行，cache中的引用就不会被GC给。
     * 那么随着cache中的引用越来越多，GC无法回收的对象也越来越多，无法被自动回收。
     * 当这些对象需要被回收时，回收这些对象的任务只有交给程序编写者了。然而这却违背了GC的本质(自动回收可以回收的对象)。
     */

    /***
     * 所以，java中引入了weak reference。
     *
     * strong reference：
     * A a = new A();
     *
     * weak reference：
     * A a = new A();
     * WeakReference<A> weakReference = new WeakReference<>(a);
     *
     * 当要获得weak reference引用的对象时, 首先需要判断它是否已经被回收：
     * weakReference.get();
     * 如果此方法为空，那么说明weakCar指向的对象已经被回收了。
     */

    /***
     * 这个例子中，程序最终会打印出"对象已被回收"。
     * 值得注意的是，引用a是一个strong reference，但是进入while循环后却再也没有被使用过，所以jvm进行了优化，在gc触发时将其回收。
     * 如果在while循环中使用了引用a，即将下面代码的注释放开，那么引用a指向的对象永远不会被回收。
     */
    @Test
    public void test_1() {
        A a = new A();
        WeakReference<A> weakReference = new WeakReference<>(a);
        int i = 0;
        while (true) {
            ++i;
            if (weakReference.get() != null) {
//                System.out.println(a.getClass());
                System.out.println(i + "：对象还未被回收");
            } else {
                System.out.println("**********************对象已被回收*********************");
                break;
            }
        }
    }

    /***
     * A a = new A();
     * B b = new B(a);
     * a = null;
     * 上述代码，即使将a引用置为null，a引用指向的对象也不会被回收，因为B对象依赖了A对象，我们认为B对象时可以到达对象A的。
     * 那么这个时候就会造成内存泄漏，因为A对象永远不会被回收。除非这时候再加上
     * b = null;
     * 那么这个问题就会得到解决。
     * 但如果将A对象换成弱引用的话，如下述代码，这样A对象是可以被回收的。
     */
    @Test
    public void test_2() {
        A a = new A();
        WeakReference<A> weakReference = new WeakReference<>(a);
        B b = new B(a);
        int i = 0;
        while (true) {
            ++i;
            if (weakReference.get() != null) {
                System.out.println(i + "：对象还未被回收");
            } else {
                System.out.println("**********************对象已被回收*********************");
                break;
            }
        }
    }


    class A {
        //do
    }

    class B {

        private A a;

        public B(A a) {
            this.a = a;
        }

        //do
    }

    /***
     * ReferenceQueue的使用
     * 当一个弱引用或软引用被gc清理掉后，其相应的包装类（即weakReference对象），会被放入ReferenceQueue队列中。
     * 我们可以从ReferenceQueue中获取到相应的对象信息，同时进行额外的处理。比如反向操作，数据清理等。
     */

    /***
     * 使用ReferenceQueue进行数据监控
     * 参考WeakReference*
     */

    /***
     * 使用ReferenceQueue模仿WeakHashMap
     * WeakHashMap是对key做弱引用处理，我们这里对value做弱引用处理，参考WeakHashMap*
     */
}
