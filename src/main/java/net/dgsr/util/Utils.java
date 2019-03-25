package net.dgsr.util;

import java.io.IOException;
import java.util.Map;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils; 
import org.apache.commons.beanutils.BeanMap;


public class Utils {
	
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

}
