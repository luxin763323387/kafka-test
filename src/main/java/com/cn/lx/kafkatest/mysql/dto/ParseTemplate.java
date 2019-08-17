package com.cn.lx.kafkatest.mysql.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author StevenLu
 * @date 2019/7/14
 */
@Data
public class ParseTemplate {

    private String database;

    private Map<String,Template> templateMap = new HashMap<>();

    public static ParseTemplate prase(Template _template){

        ParseTemplate template = new ParseTemplate();
        template.setDatabase(_template.getDatabase());
        for (JsonTable table : _template.getJsonTableList()) {

            String name = table.getTableName();
            Integer level = table.getLevel();

            TableTemplate tableTemplate = new TableTemplate();
            tableTemplate.setTableName(name);
            tableTemplate.setLevel(level.toString());

        }
        return template;
    }
}
