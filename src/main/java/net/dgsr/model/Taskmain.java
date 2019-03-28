package net.dgsr.model;

import lombok.Data;

import java.util.Date;

@Data
public class Taskmain {
    private Integer id;

    private String date;

    private Integer visitNumber;

    private String salesmanName;

    private String salesmanCode;

    private Integer timetype;

    private Integer isOption;

    private String deptName;

    private String deptCode;

    private String createName;

    private Date createDate;

}