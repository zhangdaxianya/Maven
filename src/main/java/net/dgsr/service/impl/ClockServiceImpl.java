package net.dgsr.service.impl;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.dao.ClientInfoDao;
import net.dgsr.dao.ClockDao;
import net.dgsr.model.ClientInfo;
import net.dgsr.model.Clock;
import net.dgsr.service.ClockService;
import net.dgsr.util.LngAadLatUtil;
import net.dgsr.util.Utils;
import net.dgsr.vo.TotalAndDataVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ClockServiceImpl implements ClockService {

    @Autowired
    private ClockDao clockDao;

    @Autowired
    private ClientInfoDao clientInfoDao;


    /**
     * 添加打卡记录
     * @param clock
     * @return
     */
    @Override
    public ServiceResponse<?> addClock(Clock clock) {
        int rowCount = clockDao.insert(clock);
        if( rowCount > 0 ){
           return ServiceResponse.createBySuccessMessage("添加成功！");
        }
        return ServiceResponse.createByErrorMessage("添加失败！");
    }

    /**
     * 查询打卡记录
     * @param clock
     * @return
     */
    @Override
    public ServiceResponse<?> selectClock( Clock clock ) {
        List<Clock> list = clockDao.selectByPrimaryKey(clock);
        return ServiceResponse.createBySuccess("查询成功！",list);
    }


    /**
     * 删除打卡记录
     * @param id
     * @return
     */
    @Override
    public ServiceResponse<?> deleteClock(int id) {
        int rowCount = clockDao.deleteByPrimaryKey(id);
        if( rowCount > 0 ){
            return ServiceResponse.createBySuccessMessage("删除打成功！");
        }
        return ServiceResponse.createByErrorMessage("删除打失败！");
    }


    /**
     * 更新打卡记录
     * @param clock
     * @return
     */
    @Override
    public ServiceResponse<?> updateClock(Clock clock) {
        int rowCount = clockDao.updateByPrimaryKey(clock);
        if( rowCount > 0 ){
            return ServiceResponse.createBySuccessMessage("更新打成功！");
        }
        return ServiceResponse.createByErrorMessage("更新打失败！");
    }


    /**
     * 根据经纬度查询是否在打卡范围中，并返回附近的客户(2000m)
     * @param lat 纬度
     * @param lon 经度
     * @param code 客户代码
     * @return
     */
    @Override
    public ServiceResponse<?> getDistance(double lat, double lng, String code) {

        TotalAndDataVo totalAndDataVo = new TotalAndDataVo();

        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setCode(code);

        //获取客户信息
        ClientInfo clientInfo1 = clientInfoDao.selectByPrimaryKey(clientInfo).get(0);

        //距离
        double scope = 0.0;

        //根据经纬度计算距离
        scope = Utils.GetDistance(lng,lat,Double.parseDouble(clientInfo1.getLongitude()),Double.parseDouble(clientInfo1.getLatitude()));

        //Total = 1 表示在范围内
        if( scope <= 2000 ){
            totalAndDataVo.setTotal(1);
        }else {
            totalAndDataVo.setTotal(0);
        }

        List<ClientInfo> data = (List<ClientInfo>) getClientList(500,lat,lng).getData();

        totalAndDataVo.setObjectList(data);

        return ServiceResponse.createBySuccess("查询成功！",totalAndDataVo);
    }


    /**
     * 根据经纬度和范围返回满足条件的客户列表
     * @param dis 距离
     * @param lat 纬度
     * @param lon 经度
     * @return
     */
    @Override
    public ServiceResponse<?> getClientList(int dis, double lat, double lon) {

        ClientInfo clientInfo = null;

        //存放符合距离的客户
        List<ClientInfo> clientInfos = new ArrayList<>();

        //获取所有客户的经纬度
        List<ClientInfo> list = clientInfoDao.selectByPrimaryKey(clientInfo);

        //距离
        double scope = 0.0;

        //遍历客户集合
        for (ClientInfo info : list) {
            if ( info.getLongitude() == null || info.getLatitude() == null ){
                continue;
            }
            //根据经纬度计算距离
            scope = Utils.GetDistance(lon,lat,Double.parseDouble(info.getLongitude()),Double.parseDouble(info.getLatitude()));
            if( scope <= 500 ){
                clientInfos.add(info);  //添加符合条件的客户
            }
        }
        return ServiceResponse.createBySuccess("查询成功！",clientInfos);
    }



    /**
     * 根据地址获取经纬度
     * @param address
     * @return
     */
    @Override
    public ServiceResponse<?> getLngAadLatUtil(String address) throws UnsupportedEncodingException {

        Map<String,Object> map = LngAadLatUtil.getLngAadLatUtil(URLEncoder.encode(address,"utf-8"));

        //判断请求状态
        if( !StringUtils.equals( map.get("status").toString() , "0") ){
            return ServiceResponse.createByError("查询失败！",map );
        }

        //获取地址解析结果
        Map<String,Object> result = (Map<String, Object>) map.get("result");

        //获取经纬度
        Map<String,Object> lngAadLat = (Map<String, Object>) result.get("location");

        return ServiceResponse.createByError("查询成功！",lngAadLat );
    }


    /**
     * 更新数据库客户的经纬度
     * @return
     * @throws UnsupportedEncodingException
     */
    @Override
    public ServiceResponse<?> getLngAadLatUtil2() throws UnsupportedEncodingException {

        //获取所有客户信息
        ClientInfo clientInfo = null;
        List<ClientInfo> list = clientInfoDao.selectByPrimaryKey(clientInfo);

        Integer integer = 0 ;

        //循环获取所有客户地址
        for (ClientInfo info : list) {

            System.out.println("经度："+info.getLongitude());

            if ( info.getLongitude() == null ){
                Map<String,Object> maps = null;
                try{
                    Thread.sleep(1000);
                    maps = LngAadLatUtil.getLngAadLatUtil(URLEncoder.encode(info.getFull_name(),"utf-8"));
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                //判断请求状态
                if( !StringUtils.equals( maps.get("status").toString() , "0") ) {
                    System.out.println("不能解析......" + info.getFull_name());
                    System.out.println(maps);
                    continue;
                }

                //获取地址解析结果
                Map<String,Object> result = (Map<String, Object>) maps.get("result");

                //获取经纬度
                Map<String,Object> lngAadLat = (Map<String, Object>) result.get("location");

                //设值
                info.setLongitude(lngAadLat.get("lng").toString());
                info.setLatitude(lngAadLat.get("lat").toString());

                //更新
                integer = clientInfoDao.updateByPrimaryKey(info);
            }
        }

        if( integer > 0 ){
            return ServiceResponse.createBySuccessMessage("更新成功");
        }
        return ServiceResponse.createByErrorMessage("更新失败");
    }


}
