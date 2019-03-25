package net.dgsr.service;


import net.dgsr.comment.ServiceResponse;

public interface DepartmentService {
	
	//获取部门信息
	ServiceResponse<?> getAllDepartment(Integer id);

	//获取组织架构
	ServiceResponse<?> getOrganizationalStructure(Integer id);

	//添加部门
	ServiceResponse<?> addDepartment(int id, String name);
	
	//更新部门
	ServiceResponse<?> uploadDepartment(int id, int departmentid, String name);
	
	//删除部门
	ServiceResponse<?> deleteDepartment(int id);



}
