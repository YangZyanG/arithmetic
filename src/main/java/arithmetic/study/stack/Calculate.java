package arithmetic.study.stack;

import java.util.Stack;

/***
 * LeetCode 244
 * 基本计算器
 */
public class Calculate {

    private Stack<Long> stackA;
    private Stack<Character> stackB;

    public Calculate(){
        stackA = new Stack<Long>();
        stackB = new Stack<Character>();
    }

    public int calculate(String string){

        Long result = new Long(0);
        Long temp;
        for (int i=string.length()-1; i>=0; --i){

            if(string.charAt(i)>=48 && string.charAt(i)<=57){

                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(string.charAt(i));
                while ((i-1)>=0 && string.charAt(i-1)>=48 && string.charAt(i-1)<=57){
                    stringBuffer.append(string.charAt(i-1));
                    --i;
                }
                stackA.push(Long.valueOf(stringBuffer.reverse().toString()));

            }else if(string.charAt(i) == 32)
                continue;
            else{

                if('(' == string.charAt(i)){

                    temp = stackA.pop();

                    while (stackB.peek() != ')'){
                        if(stackB.pop() == '+')
                            temp += stackA.pop();
                        else
                            temp -= stackA.pop();
                    }

                    stackB.pop();
                    //优先级高的计算后重新入栈，最后再计算
                    stackA.push(temp);

                }else
                    stackB.push(string.charAt(i));

            }
        }

        if(!stackA.isEmpty()){
            temp = stackA.pop();

            if(stackB.isEmpty()){
                result += temp;
            }else{
                while (!stackA.isEmpty()){
                    if(stackB.pop() == '+')
                        temp += stackA.pop();
                    else
                        temp -= stackA.pop();
                }

                result += temp;
            }
        }

        return result.intValue();
    }
}
