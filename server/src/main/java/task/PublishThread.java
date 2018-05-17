package task;

import org.fusesource.mqtt.client.FutureConnection;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.MqttUtil;

/**
 * @program: mqtt-study
 * @description: future 模式 发布主题
 * @author: pansn
 * @create: 2018-05-17 14:15
 **/

public class PublishThread implements Runnable {
    Logger logger = LoggerFactory.getLogger(PublishThread.class);

    @Override
    public void run() {
        logger.debug("===============发送开始============");
        final  FutureConnection connection;

        Topic[] topics = {
                new Topic("mqtt/aaa", QoS.EXACTLY_ONCE),
                new Topic("mqtt/bbb", QoS.AT_LEAST_ONCE),
                new Topic("mqtt/ccc", QoS.AT_MOST_ONCE)};

        try {
            connection = MqttUtil.getMqtt().futureConnection();

            connection.connect();
            int count = 1;
            while(true){
                count++;
                //主题内容
                String message = "hello "+count + "MQTT";
                String topic = "mqtt/bbb";
                //发布消息
                connection.publish(topic,message.getBytes(),QoS.AT_LEAST_ONCE,false);

                Thread.sleep(5000);
                System.out.println("MQTTFutureServer.publish Message "+"Topic Title :"+topic+" context :"+message);

            }
            //使用回调式API
             /* final CallbackConnection callbackConnection=mqtt.callbackConnection();
            //连接监听
            callbackConnection.listener(new Listener() {
             //接收订阅话题发布的消息
            @Override
            public void onPublish(UTF8Buffer topic, Buffer body, Runnable ack) {
                System.out.println("=============receive msg================"+new String(body.toByteArray()));
                ack.run();
            }
             //连接失败
             @Override
             public void onFailure(Throwable value) {
              System.out.println("===========connect failure===========");
              callbackConnection.disconnect(null);
             }
                //连接断开
             @Override
             public void onDisconnected() {
              System.out.println("====mqtt disconnected=====");
             }
             //连接成功
             @Override
             public void onConnected() {
              System.out.println("====mqtt connected=====");
             }
            });
             //连接
             callbackConnection.connect(new Callback<Void>() {
               //连接失败
                 public void onFailure(Throwable value) {
                     System.out.println("============连接失败："+value.getLocalizedMessage()+"============");
                 }
                 // 连接成功
                 public void onSuccess(Void v) {
                     //订阅主题
                     Topic[] topics = {new Topic("mqtt/bbb", QoS.AT_LEAST_ONCE)};
                     callbackConnection.subscribe(topics, new Callback<byte[]>() {
                         //订阅主题成功
                      public void onSuccess(byte[] qoses) {
                             System.out.println("========订阅成功=======");
                         }
                      //订阅主题失败
                         public void onFailure(Throwable value) {
                          System.out.println("========订阅失败=======");
                          callbackConnection.disconnect(null);
                         }
                     });
                      //发布消息
                     callbackConnection.publish("mqtt/bbb", ("Hello ").getBytes(), QoS.AT_LEAST_ONCE, true, new Callback<Void>() {
                         public void onSuccess(Void v) {
                           System.out.println("===========消息发布成功============");
                         }
                         public void onFailure(Throwable value) {
                          System.out.println("========消息发布失败=======");
                          callbackConnection.disconnect(null);
                         }
                     });

                 }
             });
             */

        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }


    }
}
