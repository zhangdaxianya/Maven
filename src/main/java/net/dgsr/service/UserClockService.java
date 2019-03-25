package net.dgsr.service;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.vo.UserClockVo;

import java.util.Date;
import java.util.List;

public interface UserClockService {

    //获取指定用户在某个时间段内的外勤详情
	ServiceResponse<?> getUserClock(UserClockVo userClockVo);
}
