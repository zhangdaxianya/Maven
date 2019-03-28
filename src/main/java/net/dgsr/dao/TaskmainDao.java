package net.dgsr.dao;

import net.dgsr.model.Taskmain;

public interface TaskmainDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Taskmain record);

    int insertSelective(Taskmain record);

    Taskmain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Taskmain record);

    int updateByPrimaryKey(Taskmain record);
}