package net.dgsr.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.dgsr.comment.ServiceResponse;
import net.dgsr.dao.NoticeDao;
import net.dgsr.model.Notice;
import net.dgsr.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "/notice" , description = "消息管理")
@RestController
@RequestMapping(value = "/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;


    @ApiOperation("发送消息")
    @RequestMapping(value = "/sendNotice" ,method = {RequestMethod.GET ,RequestMethod.POST})
    public ServiceResponse<?> sendNotice(@ModelAttribute Notice notice){
        return noticeService.sendNotice(notice);
    }


    @ApiOperation("删除消息")
    @ApiImplicitParam(paramType="query", name="id", value="消息id", required=true, dataType="int")
    @RequestMapping(value = "/deleteNotice" ,method = {RequestMethod.GET ,RequestMethod.POST})
    public ServiceResponse<?> deleteNotice(@RequestParam(value = "id" , required = true) int id){
        return noticeService.deleteNotice(id);
    }




    @ApiOperation("查询消息")
    @RequestMapping(value = "/selectNotice" ,method = {RequestMethod.GET ,RequestMethod.POST})
    public ServiceResponse<?> selectNotice(@ModelAttribute Notice notice){
        return noticeService.selectNotice(notice);
    }

}
