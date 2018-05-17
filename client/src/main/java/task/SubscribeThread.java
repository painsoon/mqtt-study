package task;

import org.fusesource.mqtt.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.MqttUtil;

/**
 * @program: mqtt-study
 * @description:采用Future模式 订阅主题 
 * @author: pansn
 * @create: 2018-05-16 14:43
 **/

public class SubscribeThread implements Runnable{
    private static Logger logger = LoggerFactory.getLogger(SubscribeThread.class);

    @Override
    public void run() {
        final FutureConnection connection;
        Topic[] topics =  {new Topic("psn",QoS.AT_MOST_ONCE)};


        try {
            // 获取mqtt的连接对象BlockingConnection
            connection = MqttUtil.getMqtt().futureConnection();
            connection.connect();
            connection.subscribe(topics);

            while (true){
                Future<Message> futureMessage = connection.receive();
                Message message = futureMessage.await();
                System.out.println("MQTTFutureClient.Receive Message " + "Topic Title :" + message.getTopic() + " context :"
                        + String.valueOf(message.getPayloadBuffer()));
            }


        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }
}
