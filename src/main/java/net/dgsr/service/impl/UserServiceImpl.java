package net.dgsr.service.impl;

import org.apache.maven.shared.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.dao.UserinfoDao;
import net.dgsr.model.Userinfo;
import net.dgsr.service.UserinfoService;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserinfoService {

	@Autowired
	private UserinfoDao userinfoDao;
	
	
	/**
	 * 添加用户
	 */
	@Override
	public ServiceResponse<?> addUser(Userinfo userinfo) {

		//非空验证
		if( StringUtils.isEmpty(userinfo.getUserid()) || StringUtils.isBlank(userinfo.getUserid())){
			return ServiceResponse.createByErrorMessage("userid不能为空");
		}
		if( StringUtils.isEmpty(userinfo.getName()) || StringUtils.isBlank(userinfo.getName())){
			return ServiceResponse.createByErrorMessage("name不能为空");
		}
		if( StringUtils.isEmpty(userinfo.getDepartment()) || StringUtils.isBlank(userinfo.getDepartment())){
			return ServiceResponse.createByErrorMessage("department不能为空");
		}
		if( userinfo.getGender() == null ){
			return ServiceResponse.createByErrorMessage("gender不能为空");
		}
		if( userinfo.getEnable() == null ){
			return ServiceResponse.createByErrorMessage("enable不能为空");
		}
		
		//判断userid是否存在
		if( userinfoDao.selectByUserid(userinfo.getUserid()) > 0) {
			return ServiceResponse.createByError("用户userid已存在", userinfo.getUserid());
		}
		
		int rowCount = userinfoDao.insert(userinfo);
		if( rowCount > 0) {
			return ServiceResponse.createBySuccessMessage("添加成功！");
		}
		return ServiceResponse.createByErrorMessage("添加失败！");
	}

	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@Override
	public ServiceResponse<?> deleteUserById(int id) {
		int rowCount = userinfoDao.deleteByPrimaryKey(id);
		if( rowCount > 0 ) {
			return ServiceResponse.createBySuccessMessage("删除成功！");
		}
		return ServiceResponse.createByErrorMessage("删除失败！");
	}


	/**
	 * 根据部门id查询用户
	 * @param id
	 * @return
	 */
	@Override
	public ServiceResponse<?> selectUserByPartid(int id) {

		//存放符合条件的用户
		List<Userinfo> userList = new ArrayList<>();

		//先获取到所有用户
		List<Userinfo> userinfoList = userinfoDao.selectUser();

		//遍历所有用户
		for (Userinfo userinfo : userinfoList) {
			//获取部门
			String[] strings = userinfo.getDepartment().toString().split("/");
			//遍历部门
			for (String string : strings) {
				if ( StringUtils.equals(string,id+"")){
					userList.add(userinfo);
				}
			}
		}
		return ServiceResponse.createBySuccess("查询成功！",userList);
	}


	/**
	 * 根据条件查询用户（不包括部门）
	 * @param userinfo
	 * @return
	 */
	@Override
	public ServiceResponse<?> selectUserByKey(Userinfo userinfo) {
			List<Userinfo> userinfoList = userinfoDao.selectByPrimaryKey(userinfo);
			return ServiceResponse.createBySuccess("查询成功！",userinfoList);
	}


	/**
	 * 更新用户
	 * @param userinfo
	 * @return
	 */
	@Override
	public ServiceResponse<?> updateUserByKey(Userinfo userinfo) {
		int rowCount = userinfoDao.updateByPrimaryKey(userinfo);
		if( rowCount > 0 ) {
			return ServiceResponse.createBySuccessMessage("更新成功！");
		}
		return ServiceResponse.createByErrorMessage("更新失败！");
	}

}
