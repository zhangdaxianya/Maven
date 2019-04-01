package net.dgsr.controller;

import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.dgsr.comment.ServiceResponse;
import net.dgsr.model.Userinfo;
import net.dgsr.service.UserinfoService;

@Api(value = "/user", description = "用户管理")
@RestController
@RequestMapping("/user")
public class UserinfoController {
	
	@Autowired
	private UserinfoService userService ;
	
	
	@ApiOperation("添加用户")
	@RequestMapping(value="/insert" ,method={RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> insert(@ModelAttribute Userinfo userinfo) {
		return userService.addUser(userinfo);
	}


	@ApiOperation("删除用户")
	@ApiImplicitParam( paramType="query", name="id", value="用户id", required=true, dataType="int" )
	@RequestMapping(value="/deleteUserById" ,method={RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> deleteUserById(int id) {
		return userService.deleteUserById(id);
	}


	@ApiOperation("更新用户")
	@RequestMapping(value="/updateUserinfo" ,method={RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> updateUserinfo(@ModelAttribute Userinfo userinfo) {
		return userService.updateUserByKey(userinfo);
	}


	@ApiOperation("查询用户")
	@RequestMapping(value="/selectUserByKey" ,method={RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> selectUserByKey(@ModelAttribute Userinfo userinfo) {
		return userService.selectUserByKey(userinfo);
	}


	@ApiOperation("根据部门id查询用户")
	@ApiImplicitParam( paramType="query", name="id", value="部门id", required=true, dataType="int" )
	@RequestMapping(value="/selectUserByPartid" ,method={RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> selectUserByPartid(int id) {
		return userService.selectUserByPartid(id);
	}


	@ApiOperation("根据userid查询所有客户")
	@ApiImplicitParam( paramType="query", name="userid", value="userid", required=true, dataType="String" )
	@RequestMapping(value="/selectClientinfoByUserid" ,method={RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> selectClientinfoByUserid(String userid) {
		return userService.selectClientinfoByUserid(userid);
	}


}
