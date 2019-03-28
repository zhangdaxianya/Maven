package net.dgsr.dao;

import net.dgsr.model.Userinfo;

import java.util.List;

public interface UserinfoDao {
	
	//添加用户
	int insert(Userinfo userinfo);
	
	//查询用户信息
    List<Userinfo> selectByPrimaryKey(Userinfo userinfo);

    //查询全部用户
    List<Userinfo> selectUser();

    //删除用户
    int deleteByPrimaryKey(int id);

    //更新用户
    int updateByPrimaryKey(Userinfo userinfo);

    //根据userid查询用户,返回数字
    int selectByUserid(String userid);


}
