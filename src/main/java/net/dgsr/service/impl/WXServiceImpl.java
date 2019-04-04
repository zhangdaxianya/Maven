package net.dgsr.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.dgsr.dao.TagDao;
import net.dgsr.model.Department;
import net.dgsr.model.Tag;
import net.dgsr.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.dao.UserinfoDao;
import net.dgsr.model.Userinfo;
import net.dgsr.service.WXService;
import net.dgsr.util.HttpUtil;
import net.dgsr.util.Utils;
import net.dgsr.util.WXUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Service
public class WXServiceImpl implements WXService {
	
	@Autowired
	private UserinfoDao userinfoDao;

	@Autowired
	private TagDao tagDao;

	//sr
	String token = WXUtil.getToken();

	//sy
	String tokenSY = WXUtil.getTokenSY();

	//sy_TXL
	String tokenSY_TXL = WXUtil.getTokenTXL();

	
	/**
	 * 获取企业应用列表
	 */
	@Override
	public ServiceResponse<?> getSoftware() {
		
		//拼接请求地址
		String url = "https://qyapi.weixin.qq.com/cgi-bin/agent/list?access_token="+token;
		
		//发送请求 并将结果转换为map
		Map<String, Object> map = WXUtil.sendAndTransform(url);

		//判断请求状态
		if (!StringUtils.equals(map.get("errcode").toString(), "0")) {
			return ServiceResponse.createByError(map);
		}
		
		//取出集合
		List<String> list = (List<String>) map.get("agentlist");
		
		return ServiceResponse.createBySuccess("查询成功", list);
	}

	
	
	/**
	 * 获取指定应用的详情
	 */
	@Override
	public ServiceResponse<?> getagentByid(int agentid) {
		
		//拼接请求地址
		String url = "https://qyapi.weixin.qq.com/cgi-bin/agent/get?access_token="+token+"&agentid="+agentid;
		
		//发送请求
		Map<String,Object> map = Utils.jsonToObject(HttpUtil.sendGet(url));

		//判断请求状态
		if (!StringUtils.equals(map.get("errcode").toString(), "0")) {
			return ServiceResponse.createByError(map);
		}
		
		return ServiceResponse.createBySuccess("查询成功", map);
	}


	/**
	 * 根据部门编号获取userid 
	 */
	@Override
	public ServiceResponse<?> getUseridByParid(int departmentid,int fetch_child) {

		//拼接请求地址
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token="+token+"&department_id="+departmentid+"&fetch_child="+fetch_child;
		
		//发送请求 并将结果转换为map
		Map<String, Object> map = WXUtil.sendAndTransform(url);

		//判断请求状态
		if (!StringUtils.equals(map.get("errcode").toString(), "0")) {
			return ServiceResponse.createByError(map);
		}
		
		//取出userid
		List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("userlist");
		
		//存放所有的userid
		List<String> useridList = new ArrayList<String>();
		
		for( Map<String,Object> obj : list) {
			useridList.add(obj.get("userid").toString());
		}
		
		return ServiceResponse.createBySuccess("查询成功", useridList);
	}



	/**
	 * 根据标签id获取userid 
	 */
	@Override
	public ServiceResponse<?> getUseridByTagid(int tagid) {
		
		//拼接请求地址
		String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/get?access_token="+token+"&tagid="+tagid;
		
		//发送请求 并将结果转换为map
		Map<String, Object> map = WXUtil.sendAndTransform(url);

		//判断请求状态
		if (!StringUtils.equals(map.get("errcode").toString(), "0")) {
			return ServiceResponse.createByError(map);
		}
		
		//取出userid
		List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("userlist");
		
		//存放所有的userid
		List<String> useridList = new ArrayList<String>();
		
		for( Map<String,Object> obj : list) {
			useridList.add(obj.get("userid").toString());
		}
		
		return ServiceResponse.createBySuccess("查询成功", useridList);
	}



	/**
	 * 根据userid获取用户信息
	 */
	@Override
	public ServiceResponse<?> getUserinfoByUserid(String userid) {
		
		//拼接请求地址
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token="+token+"&userid="+userid;
		
		//发送请求 并将结果转换为map
		Map<String, Object> map = WXUtil.sendAndTransform(url);

		//判断请求状态
		if (!StringUtils.equals(map.get("errcode").toString(), "0")) {
			return ServiceResponse.createByError(map);
		}
		
		return ServiceResponse.createBySuccess("查询成功",map);
	}



	/**
	 * 添加用户到数据库
	 */
	@Override
	public ServiceResponse<?> insertUser(Userinfo userinfo) {

		//调用添加用户的方法
		int rowCount = userinfoDao.insert(userinfo);

		if( rowCount > 0) {
			return ServiceResponse.createBySuccessMessage("添加成功！");
		}
		return ServiceResponse.createByErrorMessage("添加失败！");
	}



	/**
	 * 获取部门列表
	 */
	@Override
	public ServiceResponse<?> getdepartmentList(Integer id) {
		
		//拼接请求地址
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token="+token+"";
//		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token="+tokenSY+"";
		//拼接部门
		if( id != null) {
			url = url + "&id=" + id;
		}
		
		//发送请求 并将结果转换为map
		Map<String,Object> map = WXUtil.sendAndTransform(url);
		
		//取出部门列表
		List<Map<String, Object>> departmentList = (List<Map<String, Object>>) map.get("department");
		
		return ServiceResponse.createBySuccess("查询成功!", departmentList);
	}


	/**
	 * 用户登陆
	 * @param code
	 * @return
	 */
	@Override
	public ServiceResponse<?> login( int code) {

		//拼接地址，获取访问用户身份
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token="+token+"&code="+code;

		//发送请求，接收map
		Map<String,Object> map = WXUtil.sendAndTransform(url);

		//判断请求状态
		if (!StringUtils.equals(map.get("errcode").toString(), "0")) {
			return ServiceResponse.createByError(map);
		}

		//取出userid
		String userid = map.get("UserId").toString();

		//获取admin标签id
		Tag tag = new Tag();
		tag.setTagname("admin");
		Integer tagid = tagDao.selectByPrimaryKey(tag).get(0).getTagid();

		//根据标签id获取标签成员详情
		Map<String,Object> map2 = (Map<String,Object>) getTagParticulars(tagid).getData();

		//存放标签内所有用户的userid
		List<String> list = new ArrayList<>();

		//取出用户userid集合
		List<Map<String,Object>> maps = (List<Map<String,Object>>) map2.get("userlist");

		//遍历集合 循环放入
		for (Map<String, Object> strmap : maps) {
			list.add(strmap.get("userid").toString());
		}

		//取出部门
		List<Integer> partId = (List<Integer>) map2.get("partylist");

		//循环取出部门所有用户
		for (Integer integer : partId) {
			Userinfo userinfo = (Userinfo) getUseridByParid(integer,0).getData();
			list.add(userinfo.getUserid());
		}


		//集合去重
		List<String> useridList = Utils.removeDuplicate(list);

		//判断此userid是否存在admin标签内
		for (String str : useridList) {
			System.out.println("str=="+str);
			if ( StringUtils.equals( userid , str)) {
				return ServiceResponse.createBySuccessMessage("登陆成功！");
			}
		}
		return ServiceResponse.createByErrorMessage("登陆失败，非管理员！");
	}


	/**
	 * 获取标签列表
	 * @return
	 */
	@Override
	public ServiceResponse<?> getTagList() {

		//拼接地址，获取访问用户身份
		String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/list?access_token="+token;

		//发送请求，接收map
		Map<String,Object> map = WXUtil.sendAndTransform(url);

		//判断请求状态
		if (!StringUtils.equals(map.get("errcode").toString(), "0")) {
			return ServiceResponse.createByError(map);
		}

		//取出标签列表
		List<Map<String,Object>> maps  = (List<Map<String, Object>>) map.get("taglist");

		return ServiceResponse.createBySuccess("查询成功！",maps);
	}


	/**
	 * 根据id获取标签成员详情
	 * @param id
	 * @return
	 */
	@Override
	public ServiceResponse<?> getTagParticulars(Integer id) {

		//拼接地址，获取访问用户身份
		String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/get?access_token="+token+"&tagid="+id;

		//发送请求，接收map
		Map<String,Object> map = WXUtil.sendAndTransform(url);

		//判断请求状态
		if (!StringUtils.equals(map.get("errcode").toString(), "0")) {
			return ServiceResponse.createByError(map);
		}

		return ServiceResponse.createBySuccess("查询成功！",map );
	}


	/**
	 * 添加用户
	 * @param userinfo
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ServiceResponse<?> addUser(Userinfo userinfo) {

		//同步添加到数据库中
		int rowCount = userinfoDao.insert(userinfo);
		if ( rowCount > 0 ){

			//将用户对象转换位JSON字符串
			String jsonStr = JSON.toJSONString(userinfo);

			//拼接请求地址，通讯录token（胜源）
//			String url = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token="+tokenSY_TXL;
			String url = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token="+token;

			//发送POST请求
			String data = HttpUtil.sendPost(url,jsonStr);

			//将data数据转换位map
			Map<String , Object> map = Utils.jsonToObject(data);

			//判断请求状态
			if ( !StringUtils.equals(map.get("errcode").toString(),"0") ){

				//手动回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

				return ServiceResponse.createByError("添加失败！",map);
			}
		}
		return  ServiceResponse.createBySuccessMessage("添加成功！");
	}

	/**
	 * 更新用户
	 * @param userinfo
	 * @return
	 */
	@Override
	public ServiceResponse<?> updateUser(Userinfo userinfo) {

		//同步更新到数据库中
		int rowCount = userinfoDao.updateByPrimaryKey(userinfo);
		if ( rowCount > 0 ){

			//将用户对象转换位JSON字符串
			String jsonStr = JSON.toJSONString(userinfo);

			//拼接请求地址，通讯录token（胜源）
			String url = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token="+token;

			//发送POST请求
			String data = HttpUtil.sendPost(url,jsonStr);

			//将data数据转换位map
			Map<String , Object> map = Utils.jsonToObject(data);

			//判断请求状态
			if ( !StringUtils.equals(map.get("errcode").toString(),"0") ){

				//手动回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

				return ServiceResponse.createByError("更新失败！",map);
			}
		}
		return  ServiceResponse.createBySuccessMessage("更新成功！");
	}


	/**
	 * 删除用户
	 * @param userid
	 * @return
	 */
	@Override
	public ServiceResponse<?> deliteUser(String userid) {

		//同步删除数据库中的用户
		int rowCount = userinfoDao.deleteByUserid(userid);
		if ( rowCount > 0 ){

			//拼接请求地址，通讯录token（胜源）
			String url = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token="+token+"&userid="+userid;

			// 发送请求，将data数据转换位map
			Map<String , Object> map = WXUtil.sendAndTransform(url);

			//判断请求状态
			if ( !StringUtils.equals(map.get("errcode").toString(),"0") ){

				//手动回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

				return ServiceResponse.createByError("删除失败！",map);
			}
		}
		return  ServiceResponse.createBySuccessMessage("删除成功！");
	}


	/**
	 * 添加部门
	 * @param department
	 * @return
	 */
	@Override
	public ServiceResponse<?> addDepartment(Department department) {
		return null;
	}


	/**
	 * 更新部门
	 * @param department
	 * @return
	 */
	@Override
	public ServiceResponse<?> updateDepartment(Department department) {
		return null;
	}


	/**
	 * 删除部门
	 * @param id
	 * @return
	 */
	@Override
	public ServiceResponse<?> deliteDepartment(Integer id) {
		return null;
	}


	/**
	 * 添加标签
	 * @param tag
	 * @return
	 */
	@Override
	public ServiceResponse<?> addTag(Tag tag) {
		return null;
	}


	/**
	 * 更新标签
	 * @param tag
	 * @return
	 */
	@Override
	public ServiceResponse<?> updateTag(Tag tag) {
		return null;
	}


	/**
	 * 删除标签
	 * @param tagid
	 * @return
	 */
	@Override
	public ServiceResponse<?> deliteTag(Integer tagid) {
		return null;
	}


	/**
	 * 上传临时素材到微信
	 * @param file
	 * @param type
	 * @return
	 */
	@Override
	public ServiceResponse<?> uploadTempFile(MultipartFile file, String type) {

		//拼接请求地址
		String url = "https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token="+token+"&type="+type;

		//发送上传临时素材的请求  返回一个map
		Map<String,Object> map = Utils.jsonToObject(FileUtil.uploadWxTempFile(url,file));

		//判断请求状态
		if( !StringUtils.equals( map.get("errcode").toString(),"0")){
			return ServiceResponse.createByError("上传失败！",map);
		}

		// media_id = 媒体文件上传后获取的唯一标识，3天内有效
		return ServiceResponse.createBySuccess("上传成功！",map.get("media_id"));
	}


	/**
	 * 获取临时素材
	 * @param mediaId
	 * @param userid
	 * @param request
	 * @return
	 */
	@Override
	public ServiceResponse<?> downloadTempFile(String mediaId, String userid, HttpServletRequest request) {

		//拼接请求地址
		String url = "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token="+token+"&media_id="+mediaId;

		//获取保存文件的路径 (目录的根目录的绝对路径。)
		String filePath = request.getServletContext().getRealPath("upload/clocking/" + userid + "/");

		//返回文件的存放位置
		return FileUtil.downloadTempFileByMediaId(url,filePath,mediaId);
	}


}
