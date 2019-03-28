package net.dgsr.dao;

import net.dgsr.model.ClientField;

public interface ClientFieldDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ClientField record);

    int insertSelective(ClientField record);

    ClientField selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClientField record);

    int updateByPrimaryKey(ClientField record);
}