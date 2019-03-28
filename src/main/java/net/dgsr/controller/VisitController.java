package net.dgsr.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.dgsr.comment.ServiceResponse;
import net.dgsr.model.Visit;
import net.dgsr.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Api( value="/visit" ,description="拜访管理")
@RestController
@RequestMapping("/visit")
public class VisitController {

    @Autowired
    private VisitService visitService;

    @ApiOperation("添加拜访计划")
    @RequestMapping(value = "/addVisit", method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResponse<?> addVisit(@ModelAttribute Visit visit ) {
        System.out.println("进入请求");
        return visitService.addVisit(visit);
    }


    @ApiOperation("删除拜访计划")
    @ApiImplicitParam( paramType="query", name="id", value="拜访计划id", required=true, dataType="int" )
    @RequestMapping(value = "/deleteVisit", method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResponse<?> deleteVisit(int id ) {
        return visitService.deleteVisit(id);
    }


    @ApiOperation("更新拜访计划")
    @RequestMapping(value = "/updateVisit", method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResponse<?> updateVisit(@ModelAttribute Visit visit ) {
        return visitService.updateVisit(visit);
    }


    @ApiOperation("查询拜访计划")
    @RequestMapping(value = "/selectVisit", method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResponse<?> selectVisit(@ModelAttribute Visit visit ) {
        return visitService.selectVisit(visit);
    }


}
