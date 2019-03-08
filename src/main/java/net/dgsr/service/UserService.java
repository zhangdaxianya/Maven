package net.dgsr.service;

import net.dgsr.model.User;

import java.util.List;

public interface UserService {

    //  根据条件查询用户信息
    List<User> getUserByCondition(User user);

    //  根据条件查看用户数量
    Integer getAllUserCount(User user);

}
