package arithmetic.study.stack;

import java.util.Stack;

/***
 * LeetCode 844
 * 比较含退格的字符串，#代表退格字符
 */
public class CompareString {

    public boolean backspaceCompare(String S, String T) {
        return back(S).equals(back(T));
    }

    private String back(String string){

        Stack<Character> stack = new Stack<Character>();
        StringBuffer stringBuffer = new StringBuffer();

        for(int i=string.length()-1; i>=0; --i){

            if('#' == string.charAt(i))
                stack.push('#');
            else{
                if(stack.empty())
                    stringBuffer.append(string.charAt(i));
                else
                    stack.pop();
            }
        }

        return stringBuffer.reverse().toString();
    }
}
