package net.dgsr.util;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

	
	/**
	 * get请求
	 * @param url
	 * @return
	 */
    public static String sendGet(String url) {
        URL obj = null;
        HttpURLConnection con = null;
        BufferedReader in = null;
        StringBuffer response = null;
        try {
            obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
            // 默认值
            con.setRequestMethod("GET");
            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return response.toString();
    }

    
    
    /**
     * post请求
     * @param url
     * @param param
     * @return
     */
    public static String sendPost(String url, String param) {

        URL obj = null;
        HttpsURLConnection con = null;
        BufferedReader in = null;
        StringBuffer response = null;

        try {
            obj = new URL(url);
            con = (HttpsURLConnection) obj.openConnection();

            // 添加请求头
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            // 发送Post请求
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(new String(param.getBytes("UTF-8"), "iso-8859-1"));
            wr.flush();
            wr.close();

            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            response = new StringBuffer();

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return response.toString();
    }

}
