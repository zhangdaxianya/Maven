<%@page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<html>
    <body>

    <pre>

            接口：department/getDepartment
            接口描述：获取公司组织架构
            URL:http://localhost:8080/demo/department/getDepartment
            请求方式：get/post
            参数：
		    返回示例：


            接口：user/getUser
            接口描述：根据传入的参数根据条件查询用户信息
            URL:http://localhost:8080/demo/user/getUser
            请求方式：get/post
            参数：（可以传多个参数，默认无参查全部用户）
                Integer id;      //用户id
                String userid;     //用户名id
                String name;    //姓名
                String mobile;      //电话
                Integer gender;     //性别
                String department;    //所属部门
                String position;   //职务
                String email;   //邮件
                String avatar;      //头像地址
                String telephone;   //座机
                Integer enable;     //成员启用状态。1表示启用的成员，0表示被禁用
                Integer status;     //激活状态  1=已激活，2=已禁用，4=未激活。
                String alias;       //别名
                String qr_code;     //二维码地址
                String english_name;    //英文名
                Integer isleader;       //是否标识为上级
		    返回示例：
                 {
                    "alias": "",
                    "avatar": "http://p.qlogo.cn/bizmail/urmzQCg75pCqs4OhR8YBbLuF5XdLO0MibphatEXvoL83vcUYW4GgpOg/0",
                    "department": "1",
                    "email": "",
                    "enable": 1,
                    "english_name": "",
                    "gender": 2,
                    "id": 6586,
                    "isleader": 0,
                    "mobile": "18566561188",
                    "name": "杨梅",
                    "position": "",
                    "qr_code": "https://open.work.weixin.qq.com/wwopen/userQRCode?vcode=vca168caebba50a620",
                    "status": 1,
                    "telephone": "",
                    "userid": "Mei"
                  },




    </pre>







    </body>
</html>
