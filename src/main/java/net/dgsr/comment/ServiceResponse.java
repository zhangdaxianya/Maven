package net.dgsr.comment;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;


//@JsonInclude  实体类的参数查询到的为null的不显示
//Serializable  一个对象序列化的接口

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponse<T> implements Serializable {

    //状态
    private int status;

    //描述
    private String msg;

    //数据
    private T data;


    //状态
    private ServiceResponse(int status){
        this.status = status;
    }

    //状态 描述
    private ServiceResponse(int status , String msg){
        this.status = status;
        this.msg = msg;
    }

    //状态 数据
    private ServiceResponse(int status , T data){
        this.status = status;
        this.data = data;
    }

    //状态 描述 数据
    private ServiceResponse(int status , String msg ,T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }


    //成功 返回状态
    public static <T> ServiceResponse<T> createBySuccess(){
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    //成功 返回状态和描述及数据
    public static <T> ServiceResponse<T> createBySuccess(String msg,T data){
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(),msg ,data);
    }

    //成功 返回状态和数据
    public static <T> ServiceResponse<T> createBySuccess(T data){
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    //成功 返回状态和描述
    public static <T> ServiceResponse<T> createBySuccessMessage(String msg){
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }



    //错误 返回状态
    public static <T> ServiceResponse<T> createByError(){
        return new ServiceResponse<T>(ResponseCode.ERROR.getCode());
    }

    //错误 返回状态和描述及数据
    public static <T> ServiceResponse<T> createByError(String msg,T data){
        return new ServiceResponse<T>(ResponseCode.ERROR.getCode(),msg ,data);
    }

    //错误 返回状态和数据
    public static <T> ServiceResponse<T> createByError(T data){
        return new ServiceResponse<T>(ResponseCode.ERROR.getCode(),data);
    }

    //错误 返回状态和描述
    public static <T> ServiceResponse<T> createByErrorMessage(String msg){
        return new ServiceResponse<T>(ResponseCode.ERROR.getCode(),msg);
    }
    
    

    //获取状态
    public int getStatus(){
            return status;
    }

    //获取描述
    public String getMsg(){
        return msg;
    }

    //获取数据
    public T getData(){
        return data;
    }

    //判断状态
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }
}
