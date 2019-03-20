package net.dgsr.controller;

import com.alibaba.fastjson.JSON;
import net.dgsr.service.WXService;
import net.dgsr.util.RemoveDuplicateUtil;
import net.dgsr.util.StringTransformMapUtil;
import net.dgsr.util.WXUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/wx")
@ResponseBody
public class WXController {

    @Autowired
    private WXService wxService;

    private String token = WXUtil.getToken();

    private String bmidUrl="https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token="+token;


    /**
     *  从企业微信服务器处获取指定部门成员
     * @param department_id  部门id
     * @param fetch_child   是否递归子部门
     * @return
     */
    @RequestMapping("/getWXUser")
    @ResponseBody
    public String getWXUser(@RequestParam(value = "department_id" )Integer department_id,
                            @RequestParam(value = "fetch_child" , defaultValue = "0")Integer fetch_child){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token="+token+"&department_id="+department_id+"&fetch_child="+fetch_child;
        return wxService.wxUser(url);
    };


    /**
     *  从企业微信处获取标签列表
     * @return
     */
    @RequestMapping("/getLable")
    @ResponseBody
    public String getLable(){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/list?access_token="+token;
        return wxService.wxUser(url);
    };


    /**
     *  从企业微信服务器处获取指定标签成员
     * @param tagid
     * @return
     */
    @RequestMapping("/getWXLableUser")
    @ResponseBody
    public String getWXLableUser(@RequestParam(value = "tagid" )Integer tagid){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/get?access_token="+token+"&tagid="+tagid;
        return wxService.wxUser(url);
    };


    /**
     *  获取企业应用列表
     * @return
     */
    @RequestMapping("/getSoftware")
    public Object getSoftware(){
        System.out.println("----------------------");
        String url = "https://qyapi.weixin.qq.com/cgi-bin/agent/list?access_token="+token;
        String str =  wxService.wxUser(url);
        Object object = StringTransformMapUtil.stringTransformMap(str).get("agentlist");
        return object;
    }


    /**
     *  获取微信指定应用的可见范围的人员
     * @param agentid 应用id
     * @return
     */
    @RequestMapping("/getWxVisualUser")
    public Object getWxVisualUser( Integer agentid ){

        //拼接请求地址
        String url = "https://qyapi.weixin.qq.com/cgi-bin/agent/get?access_token="+token+"&agentid="+agentid;

        //获取请求数据
        String str = wxService.wxUser(url);

        //将请求回来的数据转换为map
        Map<String,Object> map = StringTransformMapUtil.stringTransformMap(str);

        //先取出应用的可见范围人员
        Map<String , Object> user = (Map<String , Object>) map.get("allow_userinfos");
        List<Map<String, String>> list =(List<Map<String, String>>) user.get("user");

        //存放可见范围的所有人员
        List<String> sumList = new ArrayList<String>();

        for (Map<String, String> item : list) {
            sumList.add(item.get("userid"));
        }

        //取出可见部门
        Map<String , Object> bmlist =(Map<String , Object>)map.get("allow_partys");
        List<Integer> tempList = (List<Integer>)bmlist.get("partyid");

        //根据部门id查成员
        for (Integer item : tempList) {
            Map<String,Object> map1 = StringTransformMapUtil.stringTransformMap(getWXUser(item,0));
            List<Map<String, String>> list1 = (List<Map<String, String>>)map1.get("userlist");
            for (Map<String, String> s:list1){
                Map<String,Object> map2 =StringTransformMapUtil.stringTransformMap(JSON.toJSONString(s));
                sumList.add(map2.get("userid").toString());
            }
        }

        //取出可见标签
        Map<String , Object> tagesMap =(Map<String , Object>)map.get("allow_tags");
        List<Integer> integers = (List<Integer>)tagesMap.get("tagid");

        //根据标签获取成员
        for (Integer integers1:integers){
            Map<String,Object> map1 = (Map<String, Object>)StringTransformMapUtil.stringTransformMap(getWXLableUser(integers1));
            List<Map<String,String>> maps =(List<Map<String, String>>) map1.get("userlist");
            for (Map<String, String> stringStringMap:maps){
                Map<String,Object> map2 =StringTransformMapUtil.stringTransformMap(JSON.toJSONString(stringStringMap));
                sumList.add(map2.get("userid").toString());
            }
        }

        //去重复
        List<String> userList = RemoveDuplicateUtil.removeDuplicate(sumList);

        //输出sumList所有的数据
        for(String s :userList){
           System.out.println("所有可见成员："+s);
        }
        return userList;
    }




}
