package net.dgsr.util;

import java.util.Map;

public class WXUtil {
	
	/**
	 * 获取token
	*/
    public static String getToken(){

        //拼接获取token的请求地址
        String tokenUrl = Constants.GET_TOKEN_URL+"?corpid="+Constants.APP_ID+"&corpsecret="+Constants.SECRET;

        //发送获取token请求
        String data = HttpUtil.sendGet(tokenUrl);
        
        //將token字符串转换为map接收
        Map<String , Object> map = Utils.jsonToObject(data);

        //取出token字符串
        String token = map.get("access_token").toString();

        return token;
    }
	

    
    /**
     *  发送请求 转换数据
     * @param url
     * @return
     */
    public static Map<String,Object> sendAndTransform( String url){

		//发送请求
		String data = HttpUtil.sendGet(url);

		//将结果转换为map
		Map<String, Object> map = Utils.jsonToObject(data);
		
		return map;
    }

}
