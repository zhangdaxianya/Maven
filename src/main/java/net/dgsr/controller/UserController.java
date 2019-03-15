package net.dgsr.controller;

import com.alibaba.fastjson.JSON;
import net.dgsr.model.User;
import net.dgsr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //根据传入的条件查询用户信息，默认查全部用户
    @RequestMapping(value = "/getUser")
    public String getUserByCondition( User user){
        List<User> list = userService.getUserByCondition(user);
        return JSON.toJSONString(list);
    }


    //根据条件查看用户数量，默认查看全部用户数量
    @RequestMapping("/getCount")
    public Integer getUserCount(User user){

        return userService.getAllUserCount(user);
    }


    //测试获取request请求内的数据
    @RequestMapping("/demo")
    public Map<String,Object> string(HttpServletRequest request){
        Enumeration<String> enumeration = request.getParameterNames();
        Map<String,Object> map =new LinkedHashMap<>();
        while (enumeration.hasMoreElements()){
            //获取到请求对象中的参数名
            String name = enumeration.nextElement();
            //通过参数名拿到参数值
            String value = request.getParameter(name);
            //放入map
            map.put(name,value);
        }
        //获取到请求对象中所有的key
        for (String key : map.keySet()){
            System.out.println(key+"："+map.get(key));
        }
        return map;
    }




}
