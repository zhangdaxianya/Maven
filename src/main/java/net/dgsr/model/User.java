package net.dgsr.model;

import lombok.Data;

@Data
public class User {
    private Integer id;      //用户id
    private String userid;     //用户名id
    private String name;    //姓名
    private String mobile;      //电话
    private Integer gender;     //性别
    private String department;    //所属部门
    private String position;   //职务
    private String email;   //邮件
    private String avatar;      //头像地址
    private String telephone;   //座机
    private Integer enable;     //成员启用状态。1表示启用的成员，0表示被禁用
    private Integer status;     //激活状态  1=已激活，2=已禁用，4=未激活。
    private String alias;       //别名
    private String qr_code;     //二维码地址
    private String english_name;    //英文名
    private Integer isleader;       //是否标识为上级
}
