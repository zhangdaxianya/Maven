package net.dgsr.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="用户对象")
@Data
public class Userinfo {

    //主键
    private Integer id;

    //userid
    private String userid;

    //用户名
    private String name;
    
    //电话
    private String mobile;
    
    //所属部门
    private String department;
    
    //职务
    private String position;
    
    //性别
    private Integer gender;
    
    //邮箱
    private String email;
    
    //头像url
    private String avatar;
    
    //座机
    private String telephone;
    
    //状态（1启用，0禁用）
    private Integer enable;
    
    //别名
    private String alias;
    
    //个人二维码
    private String qrCode;
    
    //地址
    private String address;


}