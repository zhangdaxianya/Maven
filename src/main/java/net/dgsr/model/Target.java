package net.dgsr.model;

import lombok.Data;

import java.util.Date;

@Data
public class Target {
    private Integer id;

    private String targetname;

    private Boolean isenabled;

    private String createname;

    private Date createdate;

}