package com.cn.lx.kafkatest.test;

import com.alibaba.fastjson.JSON;
import com.cn.lx.kafkatest.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author StevenLu
 * @date 2019-08-18 16:09
 */
@Slf4j
public class ConsumerTest {

    private static Properties props;

    static {
        props = new Properties();

        props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "KafkaStudy");
    }

    private static  KafkaConsumer<String,String> consumer ;


    private static  void consumeMessage(){
        consumer = new KafkaConsumer<String, String>(props);
        //subscribe表示只接受是包含元素的列表(放topic)
        consumer.subscribe(Collections.singletonList(Constants.TOPIC));
    }


    /**
     * 同步轮询
     */
    private static void generalConsumeMessageAutoCommit(){
        props.put("enable.auto.commit", true);
        consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Collections.singleton(Constants.TOPIC));

        Map<String,Integer> custCounTryMap = new HashMap<>(16);
        try{
            while (true){
                ConsumerRecords<String,String> records = consumer.poll(100);
                for(ConsumerRecord<String,String> record : records){
                    log.info("topic = %s, partition = %s, offset = %d, customer = %s," +
                            "country = %s\n",record.topic(),record.partition(),record.offset(),
                            record.key(),record.value());

                    int updatedCount = 1;
                    if(custCounTryMap.containsKey(record.value())){
                        updatedCount = custCounTryMap.get(record.value()) +1;
                    }
                    custCounTryMap.put(record.value(),updatedCount);
                    log.info("=========:{}",JSON.toJSON(custCounTryMap));
                }
            }
        }finally {
            consumer.close();
        }
    }

    /**
     * 异步+同步提交
     */
    public static void generalConsumeMessageSyncCommit(){

        props.put("enable.auto.commit",false);
        consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Collections.singleton(Constants.TOPIC));

        try {
            while (true){
                ConsumerRecords<String,String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    log.info("topic = %s, partition = %s, offset = %d, customer = %s, country = S\n",
                            record.topic(),record.partition(),record.offset(),record.key(),record.value());
                }
                consumer.commitAsync();
            }
        }catch (Exception e){
            log.error("Unexpected error",e);
        }finally {
            try{
                consumer.commitSync();
            }finally {
                consumer.close();
        }

        }


    }



    public static void main(String[] args) {

        //consumeMessage();
        //generalConsumeMessageAutoCommit();
        generalConsumeMessageSyncCommit();
    }
}
