package com.cn.lx.kafkatest.test;

import com.cn.lx.kafkatest.util.Constants;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

/**
 * @author StevenLu
 * @date 2019/8/14
 */
public class ProduceTest {

    private static KafkaProducer<String,String> producer;

    static{

        Properties  props = new Properties();
        props.put("bootstrap.servers", "47.99.127.204:9092");
        //序列化器
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("partitioner.class", "com.cn.lx.kafkatest.test.CustomPartitioner");
        props.put("retries",10);
        producer = new KafkaProducer<String, String>(props);
    }

    /**
     * 最简单
     */
    private static void  sendMessage(){
        ProducerRecord<String,String> record = new ProducerRecord<>( Constants.TOPIC,Constants.KEY,"lx");
        try {
            producer.send(record);
            producer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //同步
    private static void sendMessageSynchronize(){

        ProducerRecord<String,String> record = new ProducerRecord<>( Constants.TOPIC,Constants.KEY,"lx");
        try {
            producer.send(record).get();
            producer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //实现回调接口
    private static class ProducerCallback implements Callback{

        @Override
        public void onCompletion(RecordMetadata metadata, Exception exception) {
            if(exception != null){
                exception.printStackTrace();
            }else {
                System.out.println("success=========");
            }
        }
    }

    //异步
    private static void sendMessageAsynchronize(){
        ProducerRecord<String,String> producerRecord = new ProducerRecord<>(Constants.TOPIC,Constants.KEY,"lx5");
        producer.send(producerRecord,new ProducerCallback());
        producer.close();
    }


    public static void main(String[] args) {

        //sendMessage();
        //sendMessageSynchronize();
        sendMessageAsynchronize();
    }

}
