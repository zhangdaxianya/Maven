package net.dgsr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.dgsr.comment.ServiceResponse;
import net.dgsr.model.Clock;
import net.dgsr.service.ClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Api(value = "/clock" ,description = "打卡管理")
@RestController
@RequestMapping("/clock")
public class ClockController {

    @Autowired
    private ClockService clockService;

    @ApiOperation("添加打卡记录")
    @RequestMapping(value = "/addClock",method = {RequestMethod.GET,RequestMethod.POST})
    public ServiceResponse<?> addClock( @ModelAttribute Clock clock){
        return clockService.addClock(clock);
    }


    @ApiOperation("删除打卡记录")
    @ApiImplicitParam(paramType="query", name="id", value="打卡记录id", required=true, dataType="int")
    @RequestMapping(value = "/deleteClock",method = {RequestMethod.GET,RequestMethod.POST})
    public ServiceResponse<?> deleteClock( @RequestParam(value = "id" , required = true ) int id){
        return clockService.deleteClock(id);
    }


    @ApiOperation("更新打卡记录")
    @RequestMapping(value = "/updateClock",method = {RequestMethod.GET,RequestMethod.POST})
    public ServiceResponse<?> updateClock( @ModelAttribute Clock clock){
        return clockService.updateClock(clock);
    }


    @ApiOperation("查询打卡记录")
    @RequestMapping(value = "/selectClock",method = {RequestMethod.GET,RequestMethod.POST})
    public ServiceResponse<?> selectClock( @ModelAttribute Clock clock){
        return clockService.selectClock(clock);
    }


    @ApiOperation(value = "根据经纬度查询是否在打卡范围中，并返回附近的客户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "lat", value = "纬度", required = true, dataType = "double"),
            @ApiImplicitParam(paramType = "query", name = "lon", value = "经度", required = true, dataType = "double"),
            @ApiImplicitParam(paramType = "query", name = "code", value = "客户代码", dataType = "String")
    })
    @RequestMapping(value = "/getDistance", method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResponse<?> getDistance(@RequestParam double lat, @RequestParam double lon, String code) {
        return clockService.getDistance(lat, lon, code);
    }

    @ApiOperation(value = "根据经纬度和范围返回满足条件的客户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "dis", value = "距离", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "lat", value = "纬度", dataType = "double"),
            @ApiImplicitParam(paramType = "query", name = "lon", value = "经度", dataType = "double")
    })
    @RequestMapping(value = "/getClientList", method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResponse<?> getClientList(@RequestParam int dis, double lat, double lon) {

        return clockService.getClientList(dis, lat, lon);
    }


    @ApiOperation("根据地址获取经纬度")
    @ApiImplicitParam(paramType="query", name="address", value="地址", required=true, dataType="String")
    @RequestMapping(value = "/getLngAadLatUtil",method = {RequestMethod.GET,RequestMethod.POST})
    public ServiceResponse<?> getLngAadLatUtil( @RequestParam(value = "address" , required = true ) String address) throws UnsupportedEncodingException {
        return clockService.getLngAadLatUtil(address);
    }


    @ApiOperation("更新数据库客户的经纬度")
    @RequestMapping(value = "/getLngAadLatUtil2",method = {RequestMethod.GET,RequestMethod.POST})
    public ServiceResponse<?> getLngAadLatUtil2( ) throws UnsupportedEncodingException {
        return clockService.getLngAadLatUtil2();
    }


}
