package arithmetic.study.stack;

import java.util.Stack;

/***
 * LeetCode 232
 * 用栈实现队列
 */
public class MyQueue {

    private Stack<Integer> stackA;
    private Stack<Integer> stackB;

    public MyQueue(){
        stackA = new Stack<Integer>();
        stackB = new Stack<Integer>();
    }

    public void push(int x){
        stackA.push(x);
    }

    public int pop(){
        for(int i=stackA.size()-1; i>=0; --i){
            stackB.push(stackA.get(i));
        }

        int result = stackB.pop();

        stackA = new Stack<Integer>();
        for(int i=stackB.size()-1; i>=0; --i){
            stackA.push(stackB.get(i));
        }

        stackB.clear();
        return result;
    }

    public int peek(){

        for(int i=stackA.size()-1; i>=0; --i){
            stackB.push(stackA.get(i));
        }
        int result = stackB.peek();

        stackB.clear();
        return result;
    }

    public boolean empty(){
        return stackA.empty();
    }
}
