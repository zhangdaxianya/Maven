package net.dgsr.controller;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.service.UserClockService;
import net.dgsr.vo.UserClockVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.Date;

@Api(value="/clock" ,description="外勤管理")
@RestController
@RequestMapping("/clock")
public class UserClockController {

    @Autowired
    private UserClockService userClockService;
    

    @ApiOperation(value="获取指定用户在某个时间段内的外勤详情")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType="query", name="userid", value="userid", required=true, dataType="String"),
    	@ApiImplicitParam(paramType="query", name="beginTime", value="开始时间", required=true, dataType=""),
    	@ApiImplicitParam(paramType="query", name="endTime", value="结束时间", required=true, dataType="Date")
    })
    @RequestMapping(value="/userClock",method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResponse<?> getUserClock(UserClockVo userClockVo) throws Exception{
        return userClockService.getUserClock(userClockVo);
};
    
    
    
    
    
    
    
}
