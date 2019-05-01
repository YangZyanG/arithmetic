package arithmetic.study.stack;

import java.util.Stack;

/***
 * LeetCode 682
 * 棒球比赛
 */
public class CalPoints {

    private Stack<Integer> stack;

    public CalPoints(){
        stack = new Stack<Integer>();
    }

    public int calPoints(String[] ops){

        for (String string : ops){

            if("+".equals(string)){

                int m = 0;
                int n = 0;
                if(!stack.isEmpty()){
                    m = stack.pop();
                    if(!stack.isEmpty()){
                        n = stack.peek();
                        stack.push(m);
                    }
                }
                stack.push(m + n);

            }else if("D".equals(string)){

                int m = 0;
                if(!stack.isEmpty()){
                    m = stack.peek();
                    stack.push(m*2);
                }

            }else if("C".equals(string)){

                stack.pop();

            }else{
                stack.push(new Integer(string));
            }

        }

        Integer result = new Integer(0);
        while (!stack.isEmpty()){
            result += stack.pop();
        }

        return result;
    }
}
