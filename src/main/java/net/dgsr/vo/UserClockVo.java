package net.dgsr.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
public class UserClockVo {

    private String full_name;  //公司名

    private String for_short;   //公司简称

    private String clientCode;   //客户代码

    private Integer sumCount; //次数

    private String userid;  //出勤人id

    private String salesmanName; //出勤人姓名

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date maxTime;   //最近一次出勤时间

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;  //开始时间

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;    //结束时间

}
