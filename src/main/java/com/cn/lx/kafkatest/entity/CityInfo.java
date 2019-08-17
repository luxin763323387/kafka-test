package com.cn.lx.kafkatest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author StevenLu
 * @date 2019/8/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityInfo {

    private String cityName;

    private double precision;

    private double latitude;

}
