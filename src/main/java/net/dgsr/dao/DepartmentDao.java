package net.dgsr.dao;

import java.util.List;

import net.dgsr.model.Department;

public interface DepartmentDao {
	
	//获取部门信息
	List<Department> getAllDepartment(Department department);
	
	//添加部门
	int addDepartment(Department department);
	
	//修改部门
	int uploadDepartment(Department department);
	
	//删除部门
	int deleteDepartment(int id);
	
	

}
