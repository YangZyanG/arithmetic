package arithmetic.study.controller;

import arithmetic.study.queue.BlockingArrayQueue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/queue")
public class BlockingArrayQueueController {

    BlockingArrayQueue<String> blockingArrayQueue = new BlockingArrayQueue<String>(5);

    @RequestMapping("/enqueue")
    @ResponseBody
    public String enqueue(String string){
        blockingArrayQueue.enqueue(string);
        return "成功";
    }

    @RequestMapping("/dequeue")
    @ResponseBody
    public String dequeue(){
        String string = blockingArrayQueue.dequeue();
        return string;
    }
}
