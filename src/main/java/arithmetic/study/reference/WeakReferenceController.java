package arithmetic.study.reference;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangziyang
 * @since 2020-06-10
 */

@Controller
@RequestMapping("weakReference")
public class WeakReferenceController {

    Map<Object, Object> map = new HashMap<>();

    @RequestMapping("start")
    public void start() {
        ReferenceQueue<WeakReferenceEntity> referenceQueue = WeakReferenceQueue.getInstance();
        WeakReferenceEntity entity;
        for (int i = 0; i < 1000; ++i) {
            entity = new WeakReferenceEntity();
            WeakReference<WeakReferenceEntity> weakReference = new WeakReference<>(entity, referenceQueue);
            map.put(weakReference, i);
        }
    }

    @RequestMapping("check")
    public void check() {
        System.out.println(map.size());
        System.gc();
    }

    @RequestMapping("print")
    public void print() {
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(((WeakReference) entry.getKey()).get() + "：" + entry.getValue());
        }
    }
}
