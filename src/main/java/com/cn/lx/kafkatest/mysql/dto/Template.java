package com.cn.lx.kafkatest.mysql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author StevenLu
 * @date 2019/7/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Template {

    private String database;
    private List<JsonTable> jsonTableList;

}
