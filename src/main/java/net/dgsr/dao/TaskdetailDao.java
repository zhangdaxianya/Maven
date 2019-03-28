package net.dgsr.dao;

import net.dgsr.model.Taskdetail;

public interface TaskdetailDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Taskdetail record);

    int insertSelective(Taskdetail record);

    Taskdetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Taskdetail record);

    int updateByPrimaryKey(Taskdetail record);
}