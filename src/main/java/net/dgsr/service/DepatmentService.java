package net.dgsr.service;

import net.dgsr.model.Department;

import java.util.List;

public interface DepatmentService {

    //获取组织架构
    List<Department> getDepartment(Integer parentid);

}
