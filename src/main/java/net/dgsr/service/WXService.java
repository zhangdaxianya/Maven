package net.dgsr.service;

import net.dgsr.model.User;

import java.util.List;
import java.util.Map;

public interface WXService {

    //从企业微信服务器处获取数据
    String wxUser(String url);

    //获取微信指定应用的可见范围的人员
    List<Map<String,Object>> getWxVisualUser(String url);

}
