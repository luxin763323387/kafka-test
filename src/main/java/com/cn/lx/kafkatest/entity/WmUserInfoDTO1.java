package com.cn.lx.kafkatest.entity;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * @author StevenLu
 * @date 2020-06-30 13:12
 */
/**
 * 微盟用户消息数据
 * @author StevenLu
 * @date 2020-05-19 18:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WmUserInfoDTO1 implements Serializable {

    /**
     * 主wid（wid合并机制里面的概念，wid合并机制会在一个人的多个wid中选举出一个主wid）
     */
    private Long superWid;

    /**
     * public_account_id（pid）：商户店铺id（新云）
     */
    private Long pid;

    /**
     * 渠道信息 list
     */
    private List<SourceObjectList> sourceObjectList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class  SourceObjectList{

        /**
         * 渠道openid参数 比如手机号 或者微信openid
         */
        private String sourceOpenid;

        /**
         * 渠道类型 0 微信公众号 ，1 微信小程序 ，3 微盟openid 当商户的公众号没有网页授权能力时由微盟公众号代授权， 4 手机号 phone ，10 APP免登
         */
        private Integer source;

        /**
         * 渠道的appid
         */
        private String sourceAppid;

        /**
         * 微信的渠道信息
         */
        public SourceData sourceData;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SourceData{

        /**
         * 数据类型 “wx” 代表微信数据
         */
        private String sourceType;

        /**
         * 昵称
         */
        private String nickname;

        /**
         * 头像
         */
        private String headurl;

        /**
         * 用户性别 0未知 1男 2女
         */
        private Integer sex;

        /**
         * 所在国家
         */
        private String country;

        /**
         * 所在省
         */
        private String provice;

        /**
         * 所在市
         */
        private String city;

        /**
         * 是否关注 0 未关注 1 已关注
         */
        private Integer subscribe;

        /**
         * 关注时间
         */
        private Date subscribeTime;

        /**
         * openid对应的微信开放平台的unionid
         */
        private String unionid;

    }


    public static void main(String[] args) {
        SourceData sourceData1 = new SourceData();
        sourceData1.setSourceType("wx");
        SourceObjectList sourceObjectList1 = new SourceObjectList();
        sourceObjectList1.setSourceData(sourceData1);

        SourceData sourceData2 = new SourceData();
        sourceData2.setSourceType("wx");
        sourceData2.setNickname("lx");
        SourceObjectList sourceObjectList2 = new SourceObjectList();
        sourceObjectList2.setSourceData(sourceData2);

        SourceData sourceData3 = new SourceData();
        sourceData3.setSourceType("wx");
        sourceData3.setNickname("lxx");
        SourceObjectList sourceObjectList3 = new SourceObjectList();
        sourceObjectList3.setSourceData(sourceData3);


        List<SourceObjectList> sourceObjectLists = new ArrayList<>();
        sourceObjectLists.add(sourceObjectList1);
        sourceObjectLists.add(sourceObjectList2);
        sourceObjectLists.add(sourceObjectList3);

        WmUserInfoDTO1 wmUserInfoDTO1 = new WmUserInfoDTO1();
        wmUserInfoDTO1.setSourceObjectList(sourceObjectLists);


        System.out.println(JSON.toJSONString(wmUserInfoDTO1));

        Optional<String> first = wmUserInfoDTO1.getSourceObjectList().stream()
                .filter(e -> e.getSourceData() != null && "wx".equals(e.getSourceData().getSourceType()) && StringUtils.isNoneBlank(e.getSourceData().getNickname()))
                .map(SourceObjectList::getSourceData)
                .map(SourceData::getNickname)
                .findFirst();
        if (first.isPresent()){
            System.out.println(first.get());
        }else {
            System.out.println("null");
        }
    }


}
