package net.dgsr.model;

import lombok.Data;

import java.util.Date;

@Data
public class Notice {
    private Integer id;

    private String title;

    private String msgContent;

    private Integer msgObjType;

    private String msgObj;

    private String sender;

    private Integer status;

    private Date timingSendTime;

    private Date sendTime;

    private String name;

    private String identifying;

    private String description;

}