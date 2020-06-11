package arithmetic.study.reference;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author yangziyang
 * @since 2020-06-10
 */

@Component
public class WeakHashMapRunnable implements InitializingBean, Runnable {

    @Override
    public void afterPropertiesSet() throws Exception {
        new Thread(new WeakHashMapRunnable()).start();
    }

    @Override
    public void run() {
        ReferenceQueue<WeakReferenceEntity> referenceQueue = WeakReferenceQueue.getInstance();
        try {
            int cnt = 0;
            WeakHashMapReference weakHashMapReference;
            while ((weakHashMapReference = (WeakHashMapReference) referenceQueue.remove()) != null){
                System.out.println(++cnt + "回收了" + weakHashMapReference);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
