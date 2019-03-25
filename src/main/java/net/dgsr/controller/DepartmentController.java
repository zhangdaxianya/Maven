package net.dgsr.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.dgsr.comment.ServiceResponse;
import net.dgsr.service.DepartmentService;

@Api(value="/department" ,description="部门管理")
@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;


	@ApiOperation("获取部门信息")
	@ApiImplicitParam(paramType="query", name="id", value="部门id(默认获取所有部门)", required=false, dataType="Integer")
	@RequestMapping(value="/getAllDepartment",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> getAllDepartment(@RequestParam(value="id",required=false) Integer id){
		return departmentService.getAllDepartment(id);
	}
	
	
	@ApiOperation("获取组织架构")
	@ApiImplicitParam(paramType="query", name="id", value="部门id(默认为1)", defaultValue="1", required=true, dataType="int")
	@RequestMapping(value="/getOrganizationalStructure",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> getOrganizationalStructure(int id){
		return departmentService.getOrganizationalStructure(id);
	}
	
	
	@ApiOperation("添加部门")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="query", name="id", value="父级部门id", required=true, dataType="int"),
		@ApiImplicitParam(paramType="query", name="name", value="部门名称", required=true, dataType="String")
	})
	@RequestMapping(value="/addDepartment",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> addDepartment(@RequestParam(value="id",required=true) int id,
											   @RequestParam(value="name",required=true) String name){
		return departmentService.addDepartment(id,name);
	}
	
	
	@ApiOperation("更新部门")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="query", name="id", value="部门id", required=true, dataType="int"),
		@ApiImplicitParam(paramType="query", name="departmentid", value="父级部门id", required=true, dataType="int"),
		@ApiImplicitParam(paramType="query", name="name", value="部门名称", required=true, dataType="String")
	})
	@RequestMapping(value="/uploadDepartment",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> uploadDepartment(@RequestParam(value="id",required=true) int id,
											   @RequestParam(value="departmentid",required=true) int departmentid,
											   @RequestParam(value="name",required=true) String name){
		return departmentService.uploadDepartment(id,departmentid,name);
	}

	
	
	@ApiOperation("删除部门")
	@ApiImplicitParam(paramType="query", name="id", value="部门id", required=true, dataType="int")
	@RequestMapping(value="/deleteDepartment",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> deleteDepartment(@RequestParam(value="id",required=true) int id){
		return departmentService.deleteDepartment(id);
	}

}
