package net.dgsr.dao;

import net.dgsr.model.ClientLonandlat;

public interface ClientLonandlatDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ClientLonandlat record);

    int insertSelective(ClientLonandlat record);

    ClientLonandlat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClientLonandlat record);

    int updateByPrimaryKey(ClientLonandlat record);
}