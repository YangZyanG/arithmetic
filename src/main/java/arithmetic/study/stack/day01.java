package arithmetic.study.stack;

import org.junit.Test;

/***
 * 栈：如何实现浏览器的前进和后退功能？
 */
public class day01 {

    /***
     * 如何理解栈？
     * 关于栈，我有一个很贴切的例子，就是一摞叠在一起的盘子。
     * 我们平时放盘子的时候都是从下往上一个一个放，取得时候都是从上往下一个一个取得，不能从中间任意抽取。
     * 后进者先出，先进者后出，这就是典型的"栈"结构。
     * 从栈的操作特性上来看，栈是一种"操作受限"的线性表，只允许在一端插入和删除数据。
     * 从功能上来说，数组或链表确实可以替代栈，但你要知道，特定的数据结构是对特定场景的抽象，而且数组或链表暴露了太多的操作接口，操作上的确灵活自由，但使用时就比较不可控了。
     * 当某个数据集合只涉及到在一端插入和删除数据，并且满足后进先出、先进后出的特性，我们就应该首选"栈"这种数据结构。
     */

    /***
     * 如何实现一个栈？
     * 从刚才栈的定义里我们可以看出，栈主要包含两个操作，即入栈和出栈，也就是在栈顶插入一个数据和从栈顶删除一个数据。
     * 栈既可以用数组来实现，也可以用链表来实现。用数组实现的栈，我们叫做顺序栈，用链表实现的栈，叫做链式栈。
     */

    /***
     * 数组实现栈
     */
    @Test
    public void method1(){
        ArrayStack arrayStack = new ArrayStack(3);
        arrayStack.push("1");
        arrayStack.push("2");
        arrayStack.push("3");
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
    }

    /***
     * 链表实现栈
     */
    @Test
    public void method2(){
        LinkedStack linkedStack = new LinkedStack();
        linkedStack.push(1);
        linkedStack.push(2);
        linkedStack.push(3);
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
    }
}
