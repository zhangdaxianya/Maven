package net.dgsr.service.impl;

import net.dgsr.dao.UserDao;
import net.dgsr.model.User;
import net.dgsr.service.UserService;
import net.dgsr.util.WXUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    //根据条件查询用户信息
    @Override
    public List<User> getUserByCondition(User user) {
        return userDao.getUserByCondition(user);
    }

    //根据条件查看用户数量
    @Override
    public Integer getAllUserCount(User user) {
        return userDao.getAllUserCount(user);
    }
}
