package net.dgsr.model;

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


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }

    public Integer getIsleader() {
        return isleader;
    }

    public void setIsleader(Integer isleader) {
        this.isleader = isleader;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
