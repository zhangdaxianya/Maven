package net.dgsr.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Notice {
    private Integer id;

    @ApiModelProperty( value = "标题")
    private String title;

    @ApiModelProperty( value = "内容")
    private String msgContent;

    @ApiModelProperty( value = "消息对象类型 0所有 1组织 2学员")
    private Integer msgObjType;

    @ApiModelProperty( value = "消息对象")
    private String msgObj;

    @ApiModelProperty( value = "发送人")
    private String sender;

    @ApiModelProperty( value = "状态 0已发送 1定时发送 2草稿")
    private Integer status;

    @ApiModelProperty( value = "定时发送时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timingSendTime;

    @ApiModelProperty( value = "发送时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    @ApiModelProperty( value = "课程或课件名称")
    private String name;

    @ApiModelProperty( value = "课程或课件")
    private String identifying;

    @ApiModelProperty( value = "描述")
    private String description;

    @ApiModelProperty(hidden = true , value = "页码")
    private  Integer pageNum ;

    @ApiModelProperty(hidden = true , value = "每页数量")
    private  Integer pageSize ;

}