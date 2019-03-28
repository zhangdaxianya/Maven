package net.dgsr.model;

import lombok.Data;

import java.util.Date;

@Data
public class Clock {
    private Integer id;

    private String clientCode;

    private String salesmanName;

    private Float longitude;

    private Float latitude;

    private Date time;

    private String remark;

    private String userId;

    private String department;

    private String position;

    private String imgpath;
}