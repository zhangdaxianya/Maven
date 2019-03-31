package net.dgsr.dao;

import net.dgsr.model.ClientInfo;

import java.util.List;

public interface ClientInfoDao {

    //删除
    int deleteByPrimaryKey(Integer id);

    //添加
    int insert(ClientInfo record);

    //查询
    List<ClientInfo> selectByPrimaryKey(ClientInfo clientInfo);

    //修改
    int updateByPrimaryKey(ClientInfo record);

}