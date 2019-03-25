package net.dgsr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.dao.UserinfoDao;
import net.dgsr.model.Userinfo;
import net.dgsr.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserinfoDao userinfoDao;
	
	
	/**
	 * 添加用户
	 */
	@Override
	public ServiceResponse<?> addUser(Userinfo userinfo) {
		
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

}
