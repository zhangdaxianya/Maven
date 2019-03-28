package net.dgsr.dao;

import net.dgsr.model.Clock;

public interface ClockDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Clock record);

    int insertSelective(Clock record);

    Clock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Clock record);

    int updateByPrimaryKeyWithBLOBs(Clock record);

    int updateByPrimaryKey(Clock record);
}