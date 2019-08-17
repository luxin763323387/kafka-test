package com.cn.lx.kafkatest.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author StevenLu
 * @date 2019/4/13
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

    SUCCESS(0, ""),
    DUPLICATE_NAME(1, "名字重复"),
    USER_NOT_EXIST(3,"名字不存在")
    ;
    /** 错误码 */
    private Integer code;

    /** 错误描述 */
    private String desc;
}
