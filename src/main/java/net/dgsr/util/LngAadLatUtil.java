package net.dgsr.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LngAadLatUtil {

    //key
    private static final String KEY = "P3KBZ-4ZQ6X-KFK4A-TBDEP-YX6Y2-D7BI5";


    /**
     * 根据传入的地址获取经纬度
     * @param address
     * @return
     */
    public static Map<String,Object> getLngAadLatUtil(String address){

        //拼接请求地址
        String addressUrl = "https://apis.map.qq.com/ws/geocoder/v1/?address="+address+"&key="+KEY;

        //发送请求，转换为map
        Map<String,Object> map = (Map<String, Object>) WXUtil.sendAndTransform(addressUrl);

        return map;

    }


    /**
     * 根据传入的经纬度获取地址
     * @param address
     * @return
     */
    public static Map<String,Object> getArrdess(int dis, double lat, double lng){

        //拼接请求地址
        String url = "https://apis.map.qq.com/ws/geocoder/v1/?location="+lat+","+lng+"&get_poi=1&poi_options=radius="+dis+"&key="+KEY+"&output=json";

        //发送请求，转换为map
        Map<String,Object> map = (Map<String, Object>) WXUtil.sendAndTransform(url);

        return map;

    }

}
