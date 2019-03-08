package net.dgsr.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//拦截器
public class SysInterceptor extends HandlerInterceptorAdapter {

    //登陆拦截 重写preHandle
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("进入拦截器");

        //获取session对象
        HttpSession session = request.getSession();

        //取出session里面的对象

        //判断session对象是否为空

        //如果为空就重定向到登陆界面，否则return true

        return true;
    }
}
