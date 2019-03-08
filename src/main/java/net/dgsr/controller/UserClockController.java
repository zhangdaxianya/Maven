package net.dgsr.controller;

import net.dgsr.service.UserClockService;
import net.dgsr.vo.UserClockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
@RequestMapping("/clock")
public class UserClockController {

    @Autowired
    private UserClockService userClockService;

    //获取指定用户在某个时间段内的外勤详情
    @RequestMapping("/userClock")
    @ResponseBody
    public List<UserClockVo> getUserClock(UserClockVo userClockVo){
        return userClockService.getUserClock(userClockVo);
    };
}
