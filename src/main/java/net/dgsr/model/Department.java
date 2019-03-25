package net.dgsr.model;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class Department {

	//部门id
    private Integer id;  
    
    //部门名称
    private String departmentname;
    
    //上级部门id
    private Integer superiordepartmentid;
    
    //部门状态（1启用，0禁用）
    private Integer status;
    
    //子部门集合
    private List<Department> subdivision;
}