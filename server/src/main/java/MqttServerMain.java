import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task.PublishThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: mqtt-study
 * @description:
 * @author: pansn
 * @create: 2018-05-17 09:53
 **/

public class MqttServerMain {
    public static void main(String ags[]){
        Logger logger = LoggerFactory.getLogger(MqttServerMain.class);

        try {
            logger.debug("=============start ==============");

            ExecutorService pools = Executors.newCachedThreadPool();

            pools.execute(new PublishThread());

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
