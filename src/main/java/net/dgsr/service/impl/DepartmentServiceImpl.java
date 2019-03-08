package net.dgsr.service.impl;

import net.dgsr.dao.DepartmentDao;
import net.dgsr.model.Department;
import net.dgsr.service.DepatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepatmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public List<Department> getDepartment(Integer parentid) {

        List<Department> list = departmentDao.getDepartmentDao(parentid);

        return list;
    }
}
