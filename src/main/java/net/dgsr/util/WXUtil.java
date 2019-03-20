package net.dgsr.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class WXUtil {

    /*将字符串转换成json对象*/
    public static Map<String , Object> jsonToObject(String str){
        Map<String , Object> map = null;
        try {
            map = new ObjectMapper().readValue(str, Map.class);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return map;
    };

    //获取token
    public static String getToken(){

        //發送獲取token的請求
        String tokenUrl = Constants.GET_TOKEN_URL+"?corpid="+Constants.APP_ID+"&corpsecret="+Constants.SECRET;

        //將接收的token以map接收
        Map<String , Object> map = jsonToObject(HttpUtil.sendGet(tokenUrl));

        //取出token字符串
        String token = map.get("access_token").toString();

        return token;
    };

}
