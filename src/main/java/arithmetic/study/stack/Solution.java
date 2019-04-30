package arithmetic.study.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/***
 * LeetCode 20
 */
public class Solution {

    private Map<Character, Character> map;

    public Solution(){
        map = new HashMap<Character, Character>(4);
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
    }

    public boolean isValid(String string){

        char c;
        boolean result = true;
        Stack<Character> stack = new Stack<Character>();

        for (int i=0, length=string.length(); i<length; ++i){
            c = string.charAt(i);

            if(map.containsKey(c)){

                if(stack.empty()){
                    result = false;
                    break;
                }

                if(!(map.get(c) == stack.pop())){
                    result = false;
                    break;
                }

            }else{
                stack.push(c);
            }
        }

        return result;
    }
}
