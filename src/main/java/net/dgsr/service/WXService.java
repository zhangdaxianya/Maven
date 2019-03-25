package net.dgsr.service;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.model.Userinfo;

public interface WXService {
	
	//获取企业应用列表
	ServiceResponse<?> getSoftware();
	
	//获取指定应用的详情
	ServiceResponse<?> getagentByid(int agentid);
	
	//根据部门编号获取userid 
	ServiceResponse<?> getUseridByParid(int departmentid, int fetch_child);
	
	//根据标签id获取userid 
	ServiceResponse<?> getUseridByTagid(int tagid);
	
	//根据userid获取用户信息
	ServiceResponse<?> getUserinfoByUserid(String userid);
	
	//添加用户到数据库
	ServiceResponse<?> insertUser(Userinfo userinfo);

	//获取部门列表
	ServiceResponse<?> getdepartmentList(Integer id);

	
	


}
