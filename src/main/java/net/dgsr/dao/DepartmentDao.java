package net.dgsr.dao;

import net.dgsr.model.Department;

import java.util.List;

public interface DepartmentDao {

    //获取组织架构
    List<Department> getDepartmentDao(Integer parentid);

}
