package net.dgsr.util;

import java.io.IOException;
import java.util.Map;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils; 
import org.apache.commons.beanutils.BeanMap;


public class Utils {

	//地球半径
	private static double EARTH_RADIUS = 6371.393;

	//获取传入值的半径
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}


	
	/**
	 * 将json字符串转换为map
	 * @param str
	 * @return
	 */
	 public static Map<String , Object> jsonToObject(String str){
	        Map<String , Object> map = null;
	        try {
	            map = new ObjectMapper().readValue(str, Map.class);
	        }catch (IOException e){
	            e.printStackTrace();
	            return null;
	        }
	        return map;
	    }
	 
	 
	    /**
	     *  集合去重
	     * @param list
	     * @return
	     */
	    public static List removeDuplicate(List list) {
	        HashSet h = new HashSet(list);
	        list.clear();
	        list.addAll(h);
	        return list;
	    }
	    
	    
	    
	    /**
	     * 将map转换为对象
	     * @param map
	     * @param beanClass
	     * @return
	     * @throws Exception
	     */
	    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {    
	        
	    	if (map == null) {
	        	return null;  
	        }
	  
	        Object obj = beanClass.newInstance();  
	  
	        BeanUtils.populate(obj, map);  
	  
	        return obj;  
	    }    
	    
	    
	    /**
	     * 将对象转为map
	     * @param obj
	     * @return
	     */
	    public static Map<?, ?> objectToMap(Object obj) {  
	        if(obj == null)  
	            return null;   
	  
	        return new BeanMap(obj);  
	    }





	/**
	 * 根据两个经纬度计算距离
	 * @param lat1 纬度1
	 * @param lng1 经度1
	 * @param lat2 纬度2
	 * @param lng2 经度2
	 * @return
	 */
	public static double GetDistance(double lat1, double lng1, double lat2, double lng2) {

		//转换经纬度
		double radLng1 = rad(lng1);
		double radLat1 = rad(lat1);
		double radLng2 = rad(lng2);
		double radLat2 = rad(lat2);

		//计算两个经纬度的偏差距离
		double a = radLat1 - radLat2;
		double b = radLng1 - radLng2;

		double dis = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		dis = dis * EARTH_RADIUS;
		dis = Math.round(dis * 1000);
		return dis;
	}


}
