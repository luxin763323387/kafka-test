package com.cn.lx.kafkatest.test;

import com.cn.lx.kafkatest.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.CommitFailedException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * @author StevenLu
 * @date 2019/7/13
 */
@Slf4j
public class MyConsumer {

    private static KafkaConsumer<String,String> consumer;
    private static Properties props;

    static {
        props = new Properties();

        props.put("bootstrap.servers", "47.99.127.204:9092");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "KafkaStudy");
    }

    private static  void generalConsumeMessageAutoCommit(){

        props.put("enable.auto.commit", true);
        consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Collections.singleton(Constants.TOPIC));

        try{
            while (true){

                boolean flag = true;
                ConsumerRecords<String,String> records = consumer.poll(100);

                for(ConsumerRecord<String,String> record : records){
                    System.out.println(String.format(
                            "topic = %s, partition = %s, key = %s, value = %s",
                            record.topic(), record.partition(),
                            record.key(), record.value()
                    ));
                    log.info("record======纪录1:{}",record.value());
                    if (record.value().equals(" ")) {
                        log.info("record======纪录2:{}",record.value());
                        flag = false;
                    }
                }

                log.info("flag======:{}",flag);
                if (!flag){
                    break;
                }
            }
        }finally {
            consumer.close();
        }
    }


    private static void generalConsumeMessageSyncCommit() {

        props.put("auto.commit.offset", false);
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(Constants.TOPIC));

        while (true) {
            boolean flag = true;

            ConsumerRecords<String, String> records =
                    consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(String.format(
                        "topic = %s, partition = %s, key = %s, value = %s",
                        record.topic(), record.partition(),
                        record.key(), record.value()
                ));
                if (record.value().equals("done")) {
                    flag = false;
                }
            }

            try {
                consumer.commitSync();
            } catch (CommitFailedException ex) {
                System.out.println("commit failed error: "
                        + ex.getMessage());
            }

            if (!flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {

        generalConsumeMessageAutoCommit();
        //generalConsumeMessageSyncCommit();
    }
}
