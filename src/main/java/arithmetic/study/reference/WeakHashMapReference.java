package arithmetic.study.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author yangziyang
 * @since 2020-06-10
 */
public class WeakHashMapReference extends WeakReference<WeakReferenceEntity> {

    private Object key;

    public WeakHashMapReference(Object key, WeakReferenceEntity value, ReferenceQueue<? super WeakReferenceEntity> queue){
        super(value, queue);
        this.key = key;
    }
}
