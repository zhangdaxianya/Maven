package net.dgsr.dao;

import net.dgsr.model.Target;

public interface TargetDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Target record);

    int insertSelective(Target record);

    Target selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Target record);

    int updateByPrimaryKey(Target record);
}