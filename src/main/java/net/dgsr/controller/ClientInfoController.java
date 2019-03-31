package net.dgsr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.dgsr.comment.ServiceResponse;
import net.dgsr.model.ClientInfo;
import net.dgsr.service.ClientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "/clientInfo",description = "客户管理")
@RequestMapping("/clientInfo")
@RestController
public class ClientInfoController {

    @Autowired
    private ClientInfoService clientInfoService;

    @ApiOperation("添加客户")
    @RequestMapping(value = "/addClientInfo",method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResponse<?> addClientInfo(@ModelAttribute ClientInfo clientInfo){
        return clientInfoService.addClientInfo(clientInfo);
    }


    @ApiOperation("删除客户")
    @ApiImplicitParam(paramType="query", name="id", value="客户id", required=true, dataType="int")
    @RequestMapping(value = "/deleteClientInfo",method = {RequestMethod.GET,RequestMethod.POST})
    public ServiceResponse<?> deleteClientInfo( @RequestParam(value = "id" , required = true ) int id){
        return clientInfoService.deleteClientInfo(id);
    }


    @ApiOperation("更新客户")
    @RequestMapping(value = "/updateClientInfo",method = {RequestMethod.GET,RequestMethod.POST})
    public ServiceResponse<?> updateClientInfo( @ModelAttribute ClientInfo clientInfo){
        return clientInfoService.updateClientInfo(clientInfo);
    }


    @ApiOperation("查询客户")
    @RequestMapping(value = "/selectClientInfo",method = {RequestMethod.GET,RequestMethod.POST})
    public ServiceResponse<?> selectClientInfo( @ModelAttribute ClientInfo clientInfo){
        return clientInfoService.selectClientInfo(clientInfo);
    }





}
