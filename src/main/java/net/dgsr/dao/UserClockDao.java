package net.dgsr.dao;

import net.dgsr.vo.UserClockVo;

import java.util.Date;
import java.util.List;

public interface UserClockDao {

    //获取指定用户在某个时间段内的外勤详情
    List<UserClockVo> getUserClock(UserClockVo userClockVo);

}
