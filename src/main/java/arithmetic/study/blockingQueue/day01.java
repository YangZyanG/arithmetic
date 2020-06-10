package arithmetic.study.blockingQueue;

import org.junit.Test;
import sun.misc.Unsafe;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yangziyang
 * @since 2020-06-01
 */
public class day01 {

    private volatile int value = 5;

    Object m = new Object();

    public int getValue(){
        return this.value;
    }

    @Test
    public void ArrayBlockingQueueTest(){
        synchronized (m){
            AtomicInteger atomicInteger = new AtomicInteger();
            atomicInteger.set(value);
            int i = atomicInteger.incrementAndGet();
            System.out.println(i);
        }
    }

    public static synchronized void test(){
        System.out.println("");
    }

}
