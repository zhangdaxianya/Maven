package net.dgsr.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.maven.shared.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.dao.UserinfoDao;
import net.dgsr.model.Userinfo;
import net.dgsr.service.WXService;
import net.dgsr.util.HttpUtil;
import net.dgsr.util.Utils;
import net.dgsr.util.WXUtil;

@Service
public class WXServiceImpl implements WXService {
	
	@Autowired
	private UserinfoDao userinfoDao;
	
	//token
	String token = WXUtil.getToken();

	
	/**
	 * 获取企业应用列表
	 */
	@Override
	public ServiceResponse<?> getSoftware() {
		
		//拼接请求地址
		String url = "https://qyapi.weixin.qq.com/cgi-bin/agent/list?access_token="+token;
		
		//发送请求 并将结果转换为map
		Map<String, Object> map = WXUtil.sendAndTransform(url);
		
		//取出集合
		List<String> list = (List<String>) map.get("agentlist");
		
		return ServiceResponse.createBySuccess("查询成功", list);
	}

	
	
	/**
	 * 获取指定应用的详情
	 */
	@Override
	public ServiceResponse<?> getagentByid(int agentid) {
		
		//拼接请求地址
		String url = "https://qyapi.weixin.qq.com/cgi-bin/agent/get?access_token="+token+"&agentid="+agentid;
		
		//发送请求
		Map<String,Object> map = Utils.jsonToObject(HttpUtil.sendGet(url));
		
		return ServiceResponse.createBySuccess("查询成功", map);
	}


	/**
	 * 根据部门编号获取userid 
	 */
	@Override
	public ServiceResponse<?> getUseridByParid(int departmentid,int fetch_child) {

		//拼接请求地址
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token="+token+"&department_id="+departmentid+"&fetch_child="+fetch_child;
		
		//发送请求 并将结果转换为map
		Map<String, Object> map = WXUtil.sendAndTransform(url);
		
		//取出userid
		List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("userlist");
		
		//存放所有的userid
		List<String> useridList = new ArrayList<String>();
		
		for( Map<String,Object> obj : list) {
			useridList.add(obj.get("userid").toString());
		}
		
		return ServiceResponse.createBySuccess("查询成功", useridList);
	}



	/**
	 * 根据标签id获取userid 
	 */
	@Override
	public ServiceResponse<?> getUseridByTagid(int tagid) {
		
		//拼接请求地址
		String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/get?access_token="+token+"&tagid="+tagid;
		
		//发送请求 并将结果转换为map
		Map<String, Object> map = WXUtil.sendAndTransform(url);
		
		//取出userid
		List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("userlist");
		
		//存放所有的userid
		List<String> useridList = new ArrayList<String>();
		
		for( Map<String,Object> obj : list) {
			useridList.add(obj.get("userid").toString());
		}
		
		return ServiceResponse.createBySuccess("查询成功", useridList);
	}



	/**
	 * 根据userid获取用户信息
	 */
	@Override
	public ServiceResponse<?> getUserinfoByUserid(String userid) {
		
		//拼接请求地址
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token="+token+"&userid="+userid;
		
		//发送请求 并将结果转换为map
		Map<String, Object> map = WXUtil.sendAndTransform(url);
		
		return ServiceResponse.createBySuccess("查询成功",map);
	}



	/**
	 * 添加用户到数据库
	 */
	@Override
	public ServiceResponse<?> insertUser(Userinfo userinfo) {
		
		//判断电话是否为null
		if( StringUtils.isEmpty(userinfo.getMobile())) {
			userinfo.setMobile(" ");
		}
		
		//判断职务是否为null
		if( StringUtils.isEmpty(userinfo.getPosition())) {
			userinfo.setPosition(" ");
		}
		
		//判断邮箱是否为null
		if( StringUtils.isEmpty(userinfo.getEmail())) {
			userinfo.setEmail(" ");
		}
		
		//判断头像url是否为null
		if( StringUtils.isEmpty(userinfo.getAvatar())) {
			userinfo.setAvatar(" ");
		}
		
		//判断座机是否为null
		if( StringUtils.isEmpty(userinfo.getTelephone())) {
			userinfo.setTelephone(" ");
		}
		
		//判断别名是否为null
		if( StringUtils.isEmpty(userinfo.getAlias())) {
			userinfo.setAlias(" ");
		}
		
		//判断个人二维码是否为null
		if( StringUtils.isEmpty(userinfo.getQrCode())) {
			userinfo.setQrCode(" ");
		}
		
		//判断地址是否为null
		if( StringUtils.isEmpty(userinfo.getAddress())) {
			userinfo.setAddress(" ");
		}
		
		//调用添加用户的方法 
		int rowCount = userinfoDao.insert(userinfo);
		
		if( rowCount > 0) {
			return ServiceResponse.createBySuccessMessage("添加成功！");
		}
		return ServiceResponse.createByErrorMessage("添加失败！");
	}



	/**
	 * 获取部门列表
	 */
	@Override
	public ServiceResponse<?> getdepartmentList(Integer id) {
		
		//拼接请求地址
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token="+token+"";
		
		//拼接部门
		if( id != null) {
			url = url + "&id=" + id;
		}
		
		//发送请求 并将结果转换为map
		Map<String,Object> map = WXUtil.sendAndTransform(url);
		
		//取出部门列表
		List<Map<String, Object>> departmentList = (List<Map<String, Object>>) map.get("department");
		
		return ServiceResponse.createBySuccess("查询成功!", departmentList);
	}


}
