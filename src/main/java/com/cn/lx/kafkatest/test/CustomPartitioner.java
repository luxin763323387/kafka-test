package com.cn.lx.kafkatest.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.record.InvalidRecordException;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

/**
 * @author StevenLu
 * @date 2019/7/12
 */
@Slf4j
    public class CustomPartitioner implements Partitioner {

    /**
     * 决定分区
     * @param topic topic
     * @param key key
     * @param keyBytes keyBytes
     * @param value value
     * @param valueBytes1 valueBytes1
     * @param cluster  集群配置
     * @return
     */
    @Override
    public int partition(
                                    String topic,
                                    Object key,
                                    byte[] keyBytes,
                                    Object value,
                                    byte[] valueBytes1,
                                    Cluster cluster) {

        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        int numPartitions = partitionInfos.size();

        if (null == keyBytes || !(key instanceof String)) {
            throw new InvalidRecordException("kafka message must have key");
        }

        if (numPartitions == 1) {
            return 0;
        }

        if (key.equals("name")) {
            return numPartitions - 1;
        }

        return Math.abs(Utils.murmur2(keyBytes)) % (numPartitions - 1);
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
