package net.dgsr.dao;

import net.dgsr.model.ClientInfo;

public interface ClientInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ClientInfo record);

    int insertSelective(ClientInfo record);

    ClientInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClientInfo record);

    int updateByPrimaryKey(ClientInfo record);
}