package net.dgsr.util;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;

public class WXUtil {
	
	/**
	 * 每隔一个小时获取一次token（顺荣）
	*/
    @Scheduled(cron = "0 0 1/1 * * ?") // 一个小时
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
     * 每隔一个小时获取一次token（胜源）
     */
    @Scheduled(cron = "0 0 1/1 * * ?") // 一个小时
    public static String getTokenSY(){

        //拼接获取token的请求地址
        String tokenUrl = Constants.GET_TOKEN_URL+"?corpid="+Constants.APP_ID_SY+"&corpsecret="+Constants.SECRET_SY;

        //发送获取token请求
        String data = HttpUtil.sendGet(tokenUrl);

        //將token字符串转换为map接收
        Map<String , Object> map = Utils.jsonToObject(data);

        //取出token字符串
        String token = map.get("access_token").toString();

        return token;
    }


    /**
     * 每隔一个小时获取通讯录的token（胜源）
     */
    @Scheduled(cron = "0 0 1/1 * * ?") // 一个小时
    public static String getTokenTXL() {

        //拼接获取token的请求地址
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + Constants.APP_ID + "&corpsecret=" + Constants.SECRET_TXL_SY + "";

        Map<String, Object> map = Utils.jsonToObject(HttpUtil.sendGet(url));

        String secretAccesstoken = (String) map.get("access_token");

        return secretAccesstoken;
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
