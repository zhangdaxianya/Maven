package net.dgsr.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Clock {

    private Integer id;

    @ApiModelProperty( value = "客户代码")
    private String clientCode;

    @ApiModelProperty( value = "业务员名称")
    private String salesmanName;

    @ApiModelProperty( value = "经度")
    private Float longitude;

    @ApiModelProperty( value = "纬度")
    private Float latitude;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty( value = "打卡时间")
    private Date time;

    @ApiModelProperty( value = "备注")
    private String remark;

    @ApiModelProperty( value = "用户id")
    private String userId;

    @ApiModelProperty( value = "部门")
    private String department;

    @ApiModelProperty( value = "职位")
    private String position;

    @ApiModelProperty( value = "图片地址")
    private String imgpath;
}