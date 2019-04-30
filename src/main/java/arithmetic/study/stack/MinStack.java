package arithmetic.study.stack;

import java.util.Stack;

/***
 * LeetCode 155 最小栈
 */
public class MinStack {

    private Stack<Integer> stack;

    public MinStack(){
        stack = new Stack<Integer>();
    }

    public void push(int x){

        if(stack.empty()){
            stack.push(x);
            stack.push(x);
        }else{
            int temp = stack.peek();
            stack.push(x);
            if(x < temp)
                stack.push(x);
            else
                stack.push(temp);
        }
    }

    public void pop(){
        stack.pop();
        stack.pop();
    }

    public int top(){
        return stack.get(stack.size() - 2);
    }

    public int getMin(){
        return stack.peek();
    }
}
