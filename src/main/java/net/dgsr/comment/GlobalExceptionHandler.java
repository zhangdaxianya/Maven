package net.dgsr.comment;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常捕获
 * ControllerAdvice注解将作用在所有注解了@RequestMapping的控制器的方法上
 * basePackages代表指定包下面的方法，可以使用不同的类处理不同的异常。
 * ExceptionHandler(RuntimeException.class)用于全局处理控制器里的异常，在这里只处理运行时异常
 * ResponseBody标识返回JSON格式。
 */
@ControllerAdvice(basePackages = "net.dgsr.controller")
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String,Object> exception( Exception e){
        Map<String,Object> map = new HashMap<>();
        System.out.println("全局异常捕获统计");
        e.printStackTrace();
        map.put("status",ResponseCode.ERROR.getCode());
        map.put("msg","接口异常，请查看服务端日志，错了心里没点B数吗？");
        map.put("data",e.toString());
        return map;
    }



}
