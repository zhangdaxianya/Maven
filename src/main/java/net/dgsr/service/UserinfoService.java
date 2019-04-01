package net.dgsr.service;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.model.Userinfo;

public interface UserinfoService {

    //添加用户
	ServiceResponse<?> addUser(Userinfo userinfo);

	//删除用户
    ServiceResponse<?> deleteUserById(int id);

    //根据部门查询用户
    ServiceResponse<?> selectUserByPartid(int id);

    //查询用户
    ServiceResponse<?> selectUserByKey(Userinfo userinfo);

    //更新用户
    ServiceResponse<?> updateUserByKey(Userinfo userinfo);

    //根据userid查询所有客户
    ServiceResponse<?> selectClientinfoByUserid(String userid);
}
