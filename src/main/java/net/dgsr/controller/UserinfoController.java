package net.dgsr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.dgsr.comment.ServiceResponse;
import net.dgsr.model.Userinfo;
import net.dgsr.service.UserService;

@Api(value = "/user", description = "用户管理")
@RestController
@RequestMapping("/user")
public class UserinfoController {
	
	@Autowired
	private UserService userService ;
	
	
	@ApiOperation("添加用户")
	@RequestMapping(value="/insert" ,method={RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> insert(@RequestBody @ApiParam(name="用户对象" ,value="传入json格式") Userinfo userinfo) {
		return userService.addUser(userinfo);
	}
	
	
	
	
	

}
