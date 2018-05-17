import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task.SubscribeThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: mqtt-study
 * @description:
 * @author: pansn
 * @create: 2018-05-16 11:08
 **/

public class MqttCustomMain {
    public static void main(String args[]){
        Logger logger = LoggerFactory.getLogger(MqttCustomMain.class);

        try {
            logger.debug("------------------start-----------------");

            ExecutorService pools = Executors.newCachedThreadPool();

            pools.execute(new SubscribeThread());

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }
}
