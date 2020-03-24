package arithmetic.study;

import com.yohitrip.common.base.util.json.JSONConvert;
import lombok.Data;
import org.junit.Test;

import java.util.Objects;

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

        public Node(){

        }

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

    /***
     * 哨兵
     */
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

    /***
     * 队列
     */
    @Data
    class ArrayQueue{

        private Object[] arrays;

        private int n;

        private int head;

        private int rear;

        private final int capacity = 16;

        public ArrayQueue(){
            arrays = new Object[capacity];
            this.n = capacity;
        }

        public ArrayQueue(int n){
            arrays = new Object[n];
            this.n = n;
        }

        public void push(Object value){
            if (rear == n){
                if (head == 0)
                    return;

                for (int i=head; i<rear; ++i){
                    arrays[i - head] = arrays[i];
                    arrays[i] = null;
                }
                rear -= head;
                head = 0;
            }

            arrays[rear++] = value;
        }

        public Object pop(){
            if (rear == head)
                return null;

            Object value = arrays[head];
            arrays[head++] = null;
            return value;
        }
    }

    @Test
    public void ArrayQueueTest(){
        ArrayQueue queue = new ArrayQueue(10);
        for (int i=0; i<10; ++i){
            queue.push(i);
        }
        System.out.println(JSONConvert.toString(queue.getArrays()));

        for (int i=0; i<6; ++i){
            Object value = queue.pop();
            System.out.println(value);
        }
        System.out.println(JSONConvert.toString(queue.getArrays()));

        queue.push(10);
        System.out.println(JSONConvert.toString(queue.getArrays()));
    }

    @Data
    class LinkedQueue{

        private Node head;

        private Node rear;

        public LinkedQueue(){

        }

        public void push(Object value){

            Node last = rear;
            Node newNode = new Node(value);
            rear = newNode;

            if (Objects.isNull(head)){
                head = newNode;
            }else {
                last.next = rear;
            }
        }

        public Object pop(){

            if (Objects.isNull(head))
                return null;

            Object value = head.value;
            head = head.next;
            return value;
        }
    }

    @Test
    public void linkedQueueTest(){
        LinkedQueue queue = new LinkedQueue();
        queue.push("1");
        queue.push("2");
        queue.push("3");
        System.out.println(JSONConvert.toString(queue.getHead()));
        Object value = queue.pop();
        System.out.println(value);
        value = queue.pop();
        System.out.println(value);
        System.out.println(JSONConvert.toString(queue.getHead()));
    }

    /***
     * 切记：循环队列会浪费数组的一个储存空间
     */
    @Data
    class CycleQueue{

        private Object[] arrays;
        private int capacity;
        private int head;
        private int rear;

        private final int CAP = 16;

        public CycleQueue(){
            arrays = new Object[CAP];
            this.capacity = CAP;
        }

        public CycleQueue(int n){
            arrays = new Object[n];
            this.capacity = n;
        }

        public void push(Object value){

            if ((rear + 1) % capacity == head)
                return;

            arrays[rear] = value;
            rear = (rear + 1) % capacity;
        }

        public Object pop(){

            if (head == rear)
                return null;

            Object value = arrays[head];
            arrays[head] = null;
            head = (head + 1) % capacity;
            return value;
        }
    }

    @Test
    public void cycleQueueTest(){
        CycleQueue queue = new CycleQueue();
        for (int i=0; i<16; ++i){
            queue.push(i);
        }
        System.out.println(JSONConvert.toString(queue.getArrays()));

        Object value = queue.pop();
        System.out.println(value);

        queue.push(15);
        System.out.println(JSONConvert.toString(queue.getArrays()));
    }

    
}
