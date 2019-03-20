package net.dgsr.service.impl;

import com.alibaba.fastjson.JSON;
import net.dgsr.model.User;
import net.dgsr.service.WXService;
import net.dgsr.util.HttpUtil;
import net.dgsr.util.WXUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WXServiceImpl implements WXService {


    //从企业微信服务器处获取数据
    @Override
    public String wxUser(String url){
        //發送一個GET請求 參數跟在url后面
        String wxUser =  HttpUtil.sendGet(url);
        return wxUser;
    }

    //获取微信指定应用的可见范围的人员
    @Override
    public List<Map<String, Object>> getWxVisualUser(String url) {
        return null;
    }
}
