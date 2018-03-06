package com.sssz.buscheck;

import java.util.ArrayList;

/**
 * Created by sssz on 2018/2/26.
 */

public class ConstVar {
    public static final int AddPassangerRequestCode = 1;
    public static final int AddPassangerResultCode = 2;
    public static final int StartCheckRequestCode = 3;
    public static final int StartCheckResultCode = 4;



    public static final String start = "start";
    public static final String end = "end";
    public static final String price = "price";
    public static final String num = "num";
    public static final String should = "should";
    public static final String handIn = "handIn";
    public static final String handOut = "handOut";
    public static final String realGet = "handGet";
    public static final String currentCheck = "currentCheck";


    public static final String choose = "选择";
    public static final String startName = "出发地";
    public static final String endName = "终点";
    public static final String numName = "人数";
    public static final String handInName = "收入";
    public static final String handOutName = "找补";
    public static final String currentCheckMoneyName = "本次跟车共收入：";
    public static final String todayMoneyName = "今日跟车共收入：";


    public static final ArrayList<String> locations = new ArrayList<String>();
    static {
        locations.add("安福");
        locations.add("李天伦垭口");
        locations.add("哑子口");
        locations.add("金子沟");
        locations.add("曹家垭口");
        locations.add("杨家坝");
        locations.add("蒋家坝");
        locations.add("红土地");
        locations.add("五通村");
        locations.add("一碗水");
        locations.add("林家坝");
        locations.add("六方碑");
        locations.add("养猪场");
        locations.add("蓬南");
    }
    public static final int MIN_PRICE = 2;
    public static final ArrayList<Integer> priceSet = new ArrayList<>();
    static {
        priceSet.add(0);
        priceSet.add(1);
        priceSet.add(2);//安福--李天伦
        priceSet.add(0);
        priceSet.add(2);
        priceSet.add(2);//安福--哑子口
        priceSet.add(0);
        priceSet.add(3);
        priceSet.add(2);//安福--金子沟
        priceSet.add(0);
        priceSet.add(4);
        priceSet.add(2);//安福--曹家垭口
        priceSet.add(0);
        priceSet.add(5);
        priceSet.add(2);//安福--杨家坝
        priceSet.add(0);
        priceSet.add(6);
        priceSet.add(3);//安福--蒋家坝
        priceSet.add(0);
        priceSet.add(7);
        priceSet.add(3);//安福--红土地
        priceSet.add(0);
        priceSet.add(8);
        priceSet.add(3);//安福--五通村
        priceSet.add(0);
        priceSet.add(9);
        priceSet.add(3);//安福--一碗水
        priceSet.add(0);
        priceSet.add(10);
        priceSet.add(3);//安福--林家坝
        priceSet.add(0);
        priceSet.add(11);
        priceSet.add(4);//安福--六方碑
        priceSet.add(0);
        priceSet.add(12);
        priceSet.add(5);//安福--养猪场
        priceSet.add(0);
        priceSet.add(13);
        priceSet.add(6);//安福--蓬南
        //----------------------
        priceSet.add(13);
        priceSet.add(1);
        priceSet.add(5);//蓬南--李天伦
        priceSet.add(13);
        priceSet.add(2);
        priceSet.add(5);//蓬南--哑子口
        priceSet.add(13);
        priceSet.add(3);
        priceSet.add(4);//蓬南--金子沟
        priceSet.add(13);
        priceSet.add(4);
        priceSet.add(4);//蓬南--曹家垭口
        priceSet.add(13);
        priceSet.add(5);
        priceSet.add(4);//蓬南--杨家坝
        priceSet.add(13);
        priceSet.add(6);
        priceSet.add(3);//蓬南--蒋家坝
        priceSet.add(13);
        priceSet.add(7);
        priceSet.add(3);//蓬南--红土地
        priceSet.add(13);
        priceSet.add(8);
        priceSet.add(3);//蓬南--五通村
        priceSet.add(13);
        priceSet.add(9);
        priceSet.add(3);//蓬南--一碗水
        priceSet.add(13);
        priceSet.add(10);
        priceSet.add(3);//蓬南--林家坝
        priceSet.add(13);
        priceSet.add(11);
        priceSet.add(2);//蓬南--六方碑
        priceSet.add(13);
        priceSet.add(12);
        priceSet.add(2);//蓬南--养猪场
    }

}
