package com.cn.lx.kafkatest.mysql.dto;

import com.cn.lx.kafkatest.util.OpType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author StevenLu
 * @date 2019/7/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableTemplate {

    private String tableName;
    private String level;

    private Map<OpType, List<String>> opTypeFieldSetMap = new HashMap<>();

    /**
     * 字段索引 -> 字段名
     * */
    private Map<Integer, String> posMap = new HashMap<>();
}

