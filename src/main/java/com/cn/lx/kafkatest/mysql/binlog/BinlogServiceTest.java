package com.cn.lx.kafkatest.mysql.binlog;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;

import java.io.IOException;

/**
 * @author StevenLu
 * @date 2019/7/14
 */
public class BinlogServiceTest {

    public static void main(String[] args) throws IOException {

        //binlog 客户端
        BinaryLogClient client = new BinaryLogClient(
                "47.99.127.204",
                3306,
                "root",
                "Luxin19951029!@#"
        );

        /**注册监听器，从最新的位置监听*/
        client.registerEventListener(event -> {

            EventData data = event.getData();

            //根据不同的操作进行打印
            if(data instanceof UpdateRowsEventData){
                System.out.println("========Update==========");
                System.out.println(data.toString());
            }else if(data instanceof WriteRowsEventData){
                System.out.println("========Write==========");
                System.out.println(data.toString());
            }else if(data instanceof DeleteRowsEventData){
                System.out.println("========Delete==========");
                System.out.println(data.toString());
            }
        });

        client.connect();
    }
}
