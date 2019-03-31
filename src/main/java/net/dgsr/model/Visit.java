package net.dgsr.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel(value="拜访计划对象")
public class Visit {

    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty( value = "拜访时间")
    private Date visit_date;

    @ApiModelProperty( value = "拜访人")
    private String visit_people;

    @ApiModelProperty( value = "标题")
    private String visit_title;

    @ApiModelProperty( value = "内容")
    private String visit_content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty( value = "创建时间")
    private Date create_time;

    @ApiModelProperty( value = "状态")
    private Integer status;

    @ApiModelProperty(hidden = true , value = "页码")
    private  Integer pageNum = 1;

    @ApiModelProperty(hidden = true , value = "每页数量")
    private  Integer pageSize = 10;

}