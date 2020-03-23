package arithmetic.study;

import com.yohitrip.common.base.util.json.JSONConvert;
import lombok.Data;
import org.junit.Test;

import java.util.Objects;
import java.util.Random;

/**
 * @author yangziyang
 * @since 2020-03-20
 */
public class BaseTest {

    /***
     * 栈
     */
    class ArrayStack{

        private Object[] arrays;

        private int size;

        private int count;

        private ArrayStack(){

        }

        public ArrayStack(int n){
            this.arrays = new Object[n];
            this.size = n;
            this.count = 0;
        }

        public void push(Object value){
            if (count == size)
                return;

            arrays[count++] = value;
        }

        public Object pop(){
            if (count == 0)
                return null;

            Object value = arrays[count - 1];
            arrays[--count] = null;
            return value;
        }

        public Object[] getStack() {
            return arrays;
        }
    }

    @Test
    public void ArrayStackTest(){
        ArrayStack stack = new ArrayStack(10);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(JSONConvert.toString(stack.getStack()));
        Object value = stack.pop();
        System.out.println(JSONConvert.toString(stack.getStack()));
        System.out.println(value);
    }

    @Data
    class LinkedStack{

        private Node top;

        public void push(Object value){
            if (Objects.isNull(top)){
               top =  new Node(value);
               return;
            }

            Node newNode = new Node(value);
            newNode.next = top;
            top = newNode;
        }

        public Object pop(){
            if (Objects.isNull(top))
                return null;

            Object value = top.value;
            top = top.next;
            return value;
        }
    }

    @Data
    class Node{

        private Object value;

        private Node next;

        public Node(Object value){
            this.value = value;
        }
    }

    @Test
    public void LinkedStackTest(){
        LinkedStack stack = new LinkedStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(JSONConvert.toString(stack.getTop()));
        Object value = stack.pop();
        System.out.println(JSONConvert.toString(stack.getTop()));
        System.out.println(value);
    }

    //哨兵
    @Test
    public void sentry(){

        int[] arrays = new int[100000000];
        int n = 88888888;

        for (int i=0; i<arrays.length; i++){
            arrays[i] = i;
        }

        long start_1 = System.currentTimeMillis();
        for (int i=0; i<arrays.length; i++){
            if (arrays[i] == n)
                System.out.println(arrays[i]);
        }
        long end_1 = System.currentTimeMillis();
        System.out.println(end_1 - start_1);

        long start_2 = System.currentTimeMillis();
        int sentry = arrays.length - 1;
        int m = arrays[sentry];
        arrays[sentry] = n;
        for (int i=0; ; ++i){
            if (arrays[i] == n){
                if (i != sentry)
                    System.out.println(arrays[i]);
                break;
            }
        }
        if (m == n)
            System.out.println(m);
        long end_2 = System.currentTimeMillis();
        System.out.println(end_2 - start_2);
    }
}
