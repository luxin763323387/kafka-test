package com.cn.lx.kafkatest.service.impl;

import com.alibaba.fastjson.JSON;
import com.cn.lx.kafkatest.dao.UserMapper;
import com.cn.lx.kafkatest.entity.User;
import com.cn.lx.kafkatest.service.IUserService;
import com.cn.lx.kafkatest.util.Constants;
import com.cn.lx.kafkatest.util.ErrorCode;
import com.cn.lx.kafkatest.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private KafkaTemplate kafkaTemplate;

    @Resource
    private UserMapper userMapper;

    @Override
    public int add(User user) {
        return userMapper.insert(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User queryById(Integer id) {
        User user = new User();
        user.setPassword("123456");
        user.setPhone("123456");
        user.setUserid(1003);
        user.setUsername("23");
        userMapper.updateByPrimaryKey(user);
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Response dropUser(User user) {

        Response response = new Response();
        ErrorCode errorCode = user.validate(userMapper);

        if (errorCode != ErrorCode.SUCCESS) {
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        } else {
            String result = JSON.toJSONString(user);
            //第一个是topic 第二个是key 第三个是value
            kafkaTemplate.send(
                    Constants.TOPIC,
                    Constants.KEY,
                    result
            );
            log.info("发送kafka:{}", result);
        }

        return response;
    }

    @Override
    public Response dropUser2(User user) {

        Response response = new Response();
        ErrorCode errorCode = user.validate(userMapper);

        if (errorCode != ErrorCode.SUCCESS) {
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        } else {
            Properties props = new Properties();
            props.put("bootstrap.servers", "47.99.127.204:9092");
            //开启idempotence幂等 extract-once
            props.put("enable.idempotence",true);
            //序列化器
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            Producer<String, String> producer = new KafkaProducer<String, String>(props);
            Future<RecordMetadata> send = producer.send(new ProducerRecord<String, String>(Constants.TOPIC, "kafka_1", JSON.toJSONString(user)));
            log.info("send:{}", send);
//            for (int i = 0; i < 100; i++) {
//                producer.send(new ProducerRecord<String, String>(Constants.TOPIC, Constants.TOPIC, "dd:" + i));
//            }
            //Thread.sleep(1000000);
            producer.close();
        }
        return response;
    }

    @Override
    public int addUsers(List<User> users) {
        return userMapper.insertUsers(users);
    }

}
