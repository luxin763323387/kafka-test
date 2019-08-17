package com.cn.lx.kafkatest.mysql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <h1>Json模板</h1>
 * @author StevenLu
 * @date 2019/7/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonTable {

    private  String tableName;
    private Integer level;

    private List<Column> insert;
    private List<Column> update;
    private List<Column> delete;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Column{

        private String column;
    }
}
