package net.dgsr.service;

import java.util.List;
import java.util.Map;

import net.dgsr.model.Department;
import net.dgsr.model.Tag;
import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.model.Userinfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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

	//用户登陆
    ServiceResponse<?> login(int code);

    //获取标签列表
	ServiceResponse<?> getTagList();

	//根据id获取标签成员详情
    ServiceResponse<?> getTagParticulars(Integer id);

	//添加用户
	ServiceResponse<?> addUser(Userinfo userinfo);

	//更新用户
	ServiceResponse<?> updateUser(Userinfo userinfo);

	//删除用户
	ServiceResponse<?> deliteUser(String userid);

	//添加部门
	ServiceResponse<?> addDepartment(Department department);

	//更新部门
	ServiceResponse<?> updateDepartment(Department department);

	//删除部门
	ServiceResponse<?> deliteDepartment(Integer id);

	//添加标签
	ServiceResponse<?> addTag(Tag tag);

	//更新标签
	ServiceResponse<?> updateTag(Tag tag);

	//删除标签
	ServiceResponse<?> deliteTag(Integer tagid);

	//上传临时素材到微信
	ServiceResponse<?> uploadTempFile(MultipartFile file, String type);

	//获取临时素材
	ServiceResponse<?> downloadTempFile(String mediaId, String userid, HttpServletRequest request);
}
