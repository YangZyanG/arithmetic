package arithmetic.study.stack;

import org.junit.Test;

import java.util.Stack;

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

    /***
     * 栈使用的练习
     */

    /***
     * LeetCode 20
     * 有效的括号
     */
    @Test
    public void method3(){
        //自己的解决方法
        String string = "(){}[()]";
        System.out.println(method3(string));

        //官方的解决方法
        Solution solution = new Solution();
        System.out.println(solution.isValid(string));
    }

    public static boolean method3(String string){

        char[] chars = string.toCharArray();
        Stack<Character> stack = new Stack<Character>();

        boolean result = true;
        for (int i=0; i<chars.length; ++i){

            if(chars[i]=='{' || chars[i]=='[' || chars[i]=='('){
                stack.push(chars[i]);
            }else{
                if(stack.empty()){
                    result = false;
                    break;
                }

                if(chars[i] == '}'){
                    if(stack.pop().equals('{'))
                        continue;
                    else{
                        result = false;
                        break;
                    }
                }

                if(chars[i] == ']'){
                    if(stack.pop().equals('['))
                        continue;
                    else{
                        result = false;
                        break;
                    }
                }

                if(chars[i] == ')'){
                    if(stack.pop().equals('('))
                        continue;
                    else{
                        result = false;
                        break;
                    }
                }
            }
        }

        if(!stack.empty())
            result = false;

        return result;
    }

    /***
     * LeetCode 155
     * 获取最小栈
     */
    @Test
    public void method4(){
        MinStack minStack = new MinStack();
        minStack.push(-1);
        minStack.push(5);
        minStack.push(2);
        minStack.push(4);
        minStack.push(-4);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
    }

    /***
     * LeetCode 232
     * 用栈实现队列
     */
    @Test
    public void method5(){
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.empty());
    }

    /***
     * LeetCode 844
     * 比较含退格的字符串，#代表退格字符
     */
    @Test
    public void method6(){
        CompareString compareString = new CompareString();
        System.out.println(compareString.backspaceCompare("a##c", "#a#c"));
    }

    /***
     * LeetCode 224
     * 基本计算器
     */
    @Test
    public void method7(){
        Calculate calculate = new Calculate();
        System.out.println(calculate.calculate("2147483647"));
    }

    /***
     * LeetCode 682
     * 棒球比赛
     */
    @Test
    public void method8(){
        CalPoints calPoints = new CalPoints();
        String[] ops = {"5","2","C","D","+"};
        System.out.println(calPoints.calPoints(ops));
    }
}
