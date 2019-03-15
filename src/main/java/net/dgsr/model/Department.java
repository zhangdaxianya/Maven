package net.dgsr.model;

import lombok.Data;

import java.util.List;

@Data
public class Department {
    private String name;
    private Integer parentid;
    private Integer order;
    private Integer id;
    private List<Department> subdivision;
}
