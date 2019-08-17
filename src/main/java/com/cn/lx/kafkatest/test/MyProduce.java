package com.cn.lx.kafkatest.test;

import com.cn.lx.kafkatest.util.Constants;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

/**
 * @author StevenLu
 * @date 2019/7/11
 */
public class MyProduce {

    private static KafkaProducer<String,String> producer;

    static{

        Properties  props = new Properties();
        props.put("bootstrap.servers", "47.99.127.204:9092");
        //序列化器
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("partitioner.class", "com.cn.lx.kafkatest.test.CustomPartitioner");
        producer = new KafkaProducer<String, String>(props);
    }

    private static void sendMessageCallBack(){

        ProducerRecord record = new ProducerRecord(
                Constants.TOPIC,Constants.KEY,"kafka_test"
        );
        producer.send(record,new MyProducerCallback());

        record = new ProducerRecord(
                Constants.TOPIC,"kafka_key-x","kafka_test_x"
        );
        producer.send(record,new MyProducerCallback());

        record = new ProducerRecord(
                Constants.TOPIC,"kafka_key-y","kafka_test_y"
        );
        producer.send(record,new MyProducerCallback());

        record = new ProducerRecord(
                Constants.TOPIC,"kafka_key-z","kafka_test_z"
        );
        producer.send(record,new MyProducerCallback());
        producer.close();
    }


    private static class MyProducerCallback implements Callback {

        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {

            if (e != null) {
                e.printStackTrace();
                return;
            }

            System.out.println(recordMetadata.topic());
            System.out.println(recordMetadata.partition());
            System.out.println(recordMetadata.offset());
            System.out.println("Coming in MyProducerCallback");
        }
    }

    public static void main(String[] args) {

        sendMessageCallBack();
    }


}
