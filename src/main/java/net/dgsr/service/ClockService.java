package net.dgsr.service;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.model.Clock;

import java.io.UnsupportedEncodingException;

public interface ClockService {


    //添加打卡
    ServiceResponse<?> addClock(Clock clock);

    //查询打卡记录
    ServiceResponse<?> selectClock(Clock clock);

    //删除打卡记录
    ServiceResponse<?> deleteClock(int id);

    //更新打卡记录
    ServiceResponse<?> updateClock(Clock clock);

    //根据经纬度查询是否在打卡范围中，并返回附近的客户
    ServiceResponse<?> getDistance(double lat, double lon, String code);

    //根据经纬度和范围返回满足条件的客户列表
    ServiceResponse<?> getClientList(int dis, double lat, double lon);

    //根据地址获取经纬度
    ServiceResponse<?> getLngAadLatUtil(String address) throws UnsupportedEncodingException;

    //更新数据库客户的经纬度
    ServiceResponse<?> getLngAadLatUtil2() throws UnsupportedEncodingException;

}
