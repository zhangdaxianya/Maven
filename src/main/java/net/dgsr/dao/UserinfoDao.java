package net.dgsr.dao;

import net.dgsr.model.Userinfo;

public interface UserinfoDao {
	
	//添加用户
	int insert(Userinfo userinfo);
	
	//根据条件查询用户信息
    Userinfo selectByPrimaryKey(Userinfo userinfo);
    
    //根据userid查询
    int selectByUserid(String userid);

}
