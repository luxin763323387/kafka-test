package com.cn.lx.kafkatest.util;

import com.github.shyiko.mysql.binlog.event.EventType;

/**
 * @author StevenLu
 * @date 2019/7/14
 */
public enum OpType {

    ADD,
    UPDATE,
    DELETE,
    OTHER;

    public static OpType to(EventType eventType) {

        switch (eventType) {
            case EXT_WRITE_ROWS:
                return ADD;
            case EXT_UPDATE_ROWS:
                return UPDATE;
            case EXT_DELETE_ROWS:
                return DELETE;

            default:
                return OTHER;
        }
    }
}
