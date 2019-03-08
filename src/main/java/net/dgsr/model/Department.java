package net.dgsr.model;

import java.util.List;

public class Department {

    private String name;
    private Integer parentid;
    private Integer order;
    private Integer id;
    private List<Department> subdivision;

    public List<Department> getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(List<Department> subdivision) {
        this.subdivision = subdivision;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
