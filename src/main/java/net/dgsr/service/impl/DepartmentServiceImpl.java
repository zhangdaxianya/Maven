package net.dgsr.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.dao.DepartmentDao;
import net.dgsr.model.Department;
import net.dgsr.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	
	@Autowired
	private DepartmentDao departmentDao;
	
	Department department = new Department();

	
	/**
	 * 获取部门信息
	 */
	@Override
	public ServiceResponse<?> getAllDepartment( Integer id) {
		department.setId(id);
		List<Department> list = departmentDao.getAllDepartment(department);
		return ServiceResponse.createBySuccess("查询成功", list);
	}


	/**
	 * 获取组织架构
	 */
	@Override
	public ServiceResponse<?> getOrganizationalStructure(Integer id) {
		
		//所有部门集合
		List<Department> sumList = departmentDao.getAllDepartment(department);
		
		//设置一级部门
		Department department = null; 
		for( Department depart : sumList) {
			if( depart.getId() == id ) {
				department = depart;
			}
		}
		
		//获取一级部门的子部门集合
		List<Department> delist = new ArrayList<Department>();
		for( Department depart : sumList) {
			if( depart.getSuperiordepartmentid() == department.getId()) {
				delist.add(depart);
			}
		}
		
		
		//循环子部门集合，判断下面是否有下级部门
		for( Department deli : delist ) {
			//存放二级部门的子部门集合
			List<Department> linshiList = new ArrayList<Department>();
			for( Department depart : sumList ) {
				if( depart.getSuperiordepartmentid() == deli.getId() ) {
					linshiList.add(depart);
				}
			}
			
			for( Department dpa : linshiList ) {
				//存放三级部门的子部门集合
				List<Department> linshiList2 = new ArrayList<Department>();
				for( Department depart : sumList ) {
					if( depart.getSuperiordepartmentid() == dpa.getId() ) {
						linshiList2.add(depart);
					}
				}
				
				for( Department ls3 : linshiList2 ) {
					//存放四级部门的子部门集合
					List<Department> linshiList3 = new ArrayList<Department>();
					for( Department depart : sumList ) {
						if( depart.getSuperiordepartmentid() == ls3.getId() ) {
							linshiList3.add(depart);
						}
					}
					//设置第四级部门的子部门集合
					ls3.setSubdivision(linshiList3);
				}
				//设置第三级部门的子部门集合
				dpa.setSubdivision(linshiList2);
			}
			//设置第二级部门的子部门集合
			deli.setSubdivision(linshiList);
		}
		//设置第一级部门的子部门集合
		department.setSubdivision(delist);
		
		return ServiceResponse.createBySuccess("查询成功", department);
	}


	/**
	 * 添加部门
	 */
	@Override
	public ServiceResponse<?> addDepartment(int id, String name) {
		department.setDepartmentname(name);
		department.setSuperiordepartmentid(id);
		department.setStatus(1);
		int rowCount = departmentDao.addDepartment(department);
		if( rowCount > 0) {
			return ServiceResponse.createBySuccessMessage("添加成功！");
		}
		return ServiceResponse.createByErrorMessage("添加失败！");
	}


	/**
	 * 更新部门
	 */
	@Override
	public ServiceResponse<?> uploadDepartment(int id,int departmentid, String name) {
		department.setId(id);
		department.setSuperiordepartmentid(departmentid);
		department.setDepartmentname(name);
		int rowCount = departmentDao.uploadDepartment(department);
		if( rowCount > 0 ) {
			return ServiceResponse.createBySuccessMessage("更新成功！");
		}
		return ServiceResponse.createByErrorMessage("更新失败！");
	}


	/**
	 * 删除部门
	 */
	@Override
	public ServiceResponse<?> deleteDepartment(int id) {
		int rowCount = departmentDao.deleteDepartment(id);
		if( rowCount > 0 ) {
			return ServiceResponse.createBySuccessMessage("删除成功！");
		}
		return ServiceResponse.createByErrorMessage("删除失败！");
	}

	


}
