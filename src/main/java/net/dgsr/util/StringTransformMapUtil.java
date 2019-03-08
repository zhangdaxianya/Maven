package net.dgsr.util;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StringTransformMapUtil {

    /**
     * String类型的JSON字符串转换为Map集合(静态方法，可直接调用)
     */
    public static Map<String,Object> stringTransformMap(String str){

        Map<String, Object> map = null;
        try {
            map = new ObjectMapper().readValue(str, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return map;
    };





}
