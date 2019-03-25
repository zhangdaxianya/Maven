package net.dgsr.service.impl;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.dao.UserClockDao;
import net.dgsr.service.UserClockService;
import net.dgsr.vo.UserClockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserClockServiceImpl implements UserClockService {

    @Autowired
    private UserClockDao userClockDao;
    
    UserClockVo userClockVo = new UserClockVo();

    //获取指定用户在某个时间段内的外勤详情
    @Override
    public ServiceResponse<?> getUserClock(UserClockVo userClockVo) {
    	
    	//开始时间的毫秒数
    	Long aLong = userClockVo.getBeginTime().getTime();
    	
    	//结束时间的毫秒数
    	Long bLong = userClockVo.getEndTime().getTime();
    	
    	//时间验证
    	if( aLong > bLong ) {
    		return ServiceResponse.createByErrorMessage("开始时间不能大于结束时间");
    	}
    	
    	System.out.println(userClockVo.getBeginTime());
    	System.out.println(userClockVo.getEndTime());
    	System.out.println(userClockVo.getUserid());

    	List<UserClockVo> list = userClockDao.getUserClock(userClockVo);
    	
    	System.out.println("返回集合长度："+list.size());
         
        return ServiceResponse.createBySuccess("查询成功", list);
    }
}
