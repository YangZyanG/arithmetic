package arithmetic.study.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * @author yangziyang
 * @since 2020-06-10
 */

public class WeakReferenceQueue {

    private static volatile ReferenceQueue referenceQueue;

    private WeakReferenceQueue(){

    }

    public static ReferenceQueue getInstance(){
        if (referenceQueue == null){
            synchronized (WeakReferenceQueue.class){
                if (referenceQueue == null){
                    referenceQueue = new ReferenceQueue();
                }
            }
        }
        return referenceQueue;
    }
}
