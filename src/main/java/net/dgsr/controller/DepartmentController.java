package net.dgsr.controller;

import com.alibaba.fastjson.JSON;
import net.dgsr.model.Department;
import net.dgsr.service.DepatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepatmentService depatmentService;

    @RequestMapping("/getDepartment")
    @ResponseBody
    public String getDepartment(@RequestParam(value = "parentid" , defaultValue = "0")Integer parentid ){

        //公司
        List<Department> list = depatmentService.getDepartment(parentid);

        //部门
        List<Department> deptlist = depatmentService.getDepartment(list.get(0).getId());

        for (Department department : deptlist){
            //将当前部门的子部门集合设置进去
            department.setSubdivision(depatmentService.getDepartment(department.getId()));
        }

        list.get(0).setSubdivision(deptlist);

        return JSON.toJSONString(list);

    };


}
