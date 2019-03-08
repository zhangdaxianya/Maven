package net.dgsr.service.impl;

import net.dgsr.dao.UserClockDao;
import net.dgsr.service.UserClockService;
import net.dgsr.vo.UserClockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserClockServiceImpl implements UserClockService {

    @Autowired
    private UserClockDao userClockDao;

    //获取指定用户在某个时间段内的外勤详情
    @Override
    public List<UserClockVo> getUserClock(UserClockVo userClockVo) {

        return userClockDao.getUserClock(userClockVo);
    }
}
