package net.dgsr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.dgsr.model.Department;
import net.dgsr.model.Tag;
import org.apache.commons.collections.CollectionUtils;
import org.apache.maven.model.IssueManagement;
import org.apache.maven.shared.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.dgsr.comment.ServiceResponse;
import net.dgsr.dao.UserinfoDao;
import net.dgsr.model.Userinfo;
import net.dgsr.service.WXService;
import net.dgsr.util.Utils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Api(value="/wx" , description="企业微信")
@RestController
@RequestMapping("/wx")
public class WXController {
	
	
	@Autowired
	private WXService wxService;
	
	@Autowired
	private UserinfoDao userinfoDao;

	@ApiOperation(value="用户登陆")
	@ApiImplicitParam(paramType="query", name="code", value="code", required=true, dataType="int")
	@RequestMapping(value="/login",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> login(@RequestParam int code) {
		return wxService.login(code);
	}



	@ApiOperation(value="获取企业应用列表")
    @RequestMapping(value="/getSoftware" ,method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> getSoftware() {
    	return wxService.getSoftware();
	}
    
    
	@ApiOperation(value="获取指定应用的详情")
	@ApiImplicitParam(paramType="query", name="agentid", value="企业应用id", required=true, dataType="int")
    @RequestMapping(value="/getagentByid",method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResponse<?> getagentByid(@RequestParam int agentid) {
    	return wxService.getagentByid(agentid);
    }
	
	
	@ApiOperation("从指定应用中获取可见用户")
	@ApiImplicitParam(paramType="query", name="agentid", value="企业应用id", required=true, dataType="int")
	@RequestMapping(value="/getUserInfo" ,method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> getUserInfoByid(@RequestParam int agentid) {
		
		//获取应用详情
		String data = JSON.toJSONString(wxService.getagentByid(agentid).getData());
		
		//将字符串转换为map
		Map<String,Object> map = Utils.jsonToObject(data);
		
		//存放所有用户的userid
		List<String> sumList = new ArrayList<String>();
		
		//取出可见范围人员
		Map<String,Object> map1 = (Map<String, Object>) map.get("allow_userinfos");
		List<Map<String, String>> list = (List<Map<String, String>>) map1.get("user");
		for( Map<String, String> objmap:list ) {
			sumList.add(objmap.get("userid"));
		}
		
		//取出部门列表
		Map<String,Object> map2 = (Map<String, Object>) map.get("allow_partys");
		List<Integer> tempList = (List<Integer>)map2.get("partyid");
		for( Integer prtid :tempList ) {
			List<String> useridList = (List<String>) wxService.getUseridByParid(prtid, 1).getData();
			for( String userid : useridList) {
				sumList.add(userid);
			}
		}
		
		//取出标签列表
		Map<String,Object> map3 = (Map<String, Object>) map.get("allow_tags");
		List<Integer> tagList = (List<Integer>)map3.get("tagid");
		for( Integer tagid :tagList ) {
			List<String> useridList = (List<String>) wxService.getUseridByTagid(tagid).getData();
			for( String userid : useridList) {
				sumList.add(userid);
			}
		}
		
		//集合去重
		List<String> useridList = Utils.removeDuplicate(sumList);
		
		return ServiceResponse.createBySuccess("查询成功", useridList);
	}
	
	
	
	@ApiOperation("根据部门编号获取userid ")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="query", name="departmentid", value="部门id", required=true, dataType="int"),
		@ApiImplicitParam(paramType="query", name="fetch_child", value="1/0：是否递归获取子部门下面的成员（默认0）", defaultValue="0", dataType="int")
	})
	@RequestMapping(value="/getUseridByParid",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> getUseridByParid(@RequestParam int departmentid  ,@RequestParam(value="fetch_child",defaultValue="0")int fetch_child) {
		return wxService.getUseridByParid(departmentid, fetch_child);
	}
	
	
	@ApiOperation("根据标签id获取userid ")
	@ApiImplicitParam(paramType="query", name="tagid", value="标签id", required=true, dataType="int")
	@RequestMapping(value="/getUseridByTagid",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> getUseridByTagid(int tagid) {
		return wxService.getUseridByTagid(tagid);
	}
	


	@ApiOperation("根据userid获取用户信息")
	@ApiImplicitParam(paramType="query", name="userid", value="用户userid",required=true, dataType="String")
	@RequestMapping(value="/getUserByUserid",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> getUserByUserid(@RequestParam String userid) {
		return wxService.getUserinfoByUserid(userid);
	}
	
	
	
	@ApiOperation("将应用所有可见用户信息添加到数据库中")
	@ApiImplicitParam(paramType="query", name="agentid", value="应用id",required=true,defaultValue="1000005", dataType="int")
	@RequestMapping(value="/insertUserByUserid",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> insertUserByUserid(@RequestParam(value="agentid" ,defaultValue="1000005") int agentid) {

		//存放所有用户信息
		//List<Map<String,Object>> userList = new ArrayList<Map<String,Object>>();
		
		//获取所有的userid
		List<String> list = (List<String>) getUserInfoByid(agentid).getData();
		System.out.println("list集合："+list.size());

		//遍历userid集合
		for( String userid : list ) {
			System.out.println("开始添加userid为："+userid);
			//判断userid在数据库中是否存在
			if( userinfoDao.selectByUserid(userid) > 0 ) {
				System.out.println("重复id："+userid);

			}else {
				//取出用户信息
				Map<String, Object> map = (Map<String, Object>) wxService.getUserinfoByUserid(userid).getData();

				//处理部门(微信设置部门不合适)
				String depart = "";
				List<Integer> list1 = (List<Integer>) map.get("department");
				for (Integer integer : list1) {
					if( integer == 1){
						break;
					}
					depart = integer.toString() + "/" + depart;
				}
				depart = "1/"+depart;
				String department = depart.substring(0,depart.length()-1);

				try {
					//将装载用户信息的map转换为Userinfo对象
					Userinfo userinfo = (Userinfo) Utils.mapToObject(map, Userinfo.class);

					//设置员工个人二维码
					userinfo.setQrCode(map.get("qr_code").toString());

					//设置部门
					userinfo.setDepartment(department);

					//调用添加用户的方法
					int rowCount = (Integer) wxService.insertUser(userinfo).getStatus();

					if( rowCount != 0) {
						return ServiceResponse.createByErrorMessage("添加失败！");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return ServiceResponse.createBySuccessMessage("添加成功！");
	}

	
	@ApiOperation("获取部门列表")
	@ApiImplicitParam(paramType="query", name="id", value="部门id，获取指定部门及其下的子部门。 如果不填，默认获取全量组织架构",required=false, dataType="Integer")
	@RequestMapping(value="/getdepartmentList",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> getdepartmentList(@RequestParam(value="id" , required=false ) Integer id){
		return wxService.getdepartmentList(id);
	}


	@ApiOperation("获取标签列表")
	@RequestMapping(value="/getTagList",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> getTagList(){
		return wxService.getTagList();
	}


	@ApiOperation("根据id获取标签成员详情")
	@ApiImplicitParam(paramType="query", name="tagid", value="标签id" ,dataType="Integer")
	@RequestMapping(value="/getTagParticulars",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> getTagParticulars(@RequestParam Integer id){
		return wxService.getTagParticulars(id);
	}


	@ApiOperation("添加用户")
	@RequestMapping(value="/addUser",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> addUser(@ModelAttribute Userinfo userinfo )
	{
		return wxService.addUser(userinfo);
	}


	@ApiOperation("更新用户")
	@RequestMapping(value="/updateUser",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> updateUser(@ModelAttribute Userinfo userinfo ){

		return wxService.updateUser(userinfo);
	}


	@ApiOperation("删除用户")
	@ApiImplicitParam(paramType="query", name="userid", value="userid" ,dataType="String")
	@RequestMapping(value="/deliteUser",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> deliteUser(@RequestParam String userid){

		return wxService.deliteUser(userid);
	}


	@ApiOperation("添加部门")
	@RequestMapping(value="/addDepartment",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> addDepartment(@ModelAttribute Department department ){
		return wxService.addDepartment(department);
	}


	@ApiOperation("更新部门")
	@RequestMapping(value="/updateDepartment",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> updateDepartment(@ModelAttribute Department department ){
		return wxService.updateDepartment(department);
	}


	@ApiOperation("删除部门")
	@ApiImplicitParam(paramType="query", name="id", value="部门id（注：不能删除根部门；不能删除含有子部门、成员的部门）" ,dataType="Integer")
	@RequestMapping(value="/deliteDepartment",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> deliteDepartment(@RequestParam Integer id){

		return wxService.deliteDepartment(id);
	}


	@ApiOperation("添加标签")
	@RequestMapping(value="/addTag",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> addTag(@ModelAttribute Tag tag ){

		return wxService.addTag(tag);
	}


	@ApiOperation("更新标签")
	@RequestMapping(value="/updateTag",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> updateTag(@ModelAttribute Tag tag  ){
		return wxService.updateTag(tag);
	}


	@ApiOperation("删除标签")
	@ApiImplicitParam(paramType="query", name="tagid", value="标签id" ,dataType="Integer")
	@RequestMapping(value="/deliteTag",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> deliteTag(@RequestParam Integer tagid){

		return wxService.deliteTag(tagid);
	}



	@ApiOperation("上传临时素材到微信")
	@RequestMapping(value="/uploadTempFile",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> uploadTempFile(@RequestParam(value = "file", required = false) MultipartFile file,
										 	  @RequestParam String type) {

		return wxService.uploadTempFile(file, type);
	}


	@ApiOperation("获取临时素材")
	@RequestMapping(value="/downloadTempFile",method = {RequestMethod.GET, RequestMethod.POST})
	public ServiceResponse<?> downloadTempFile(@RequestParam String mediaId,
											   @RequestParam String userid,
										       HttpServletRequest request) {
		return wxService.downloadTempFile(mediaId, userid, request);
	}







}
