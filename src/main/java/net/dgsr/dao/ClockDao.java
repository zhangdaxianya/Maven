package net.dgsr.dao;

import net.dgsr.model.Clock;

import java.util.List;

public interface ClockDao {


    //删除标签
    int deleteByPrimaryKey(Integer id);

    //添加标签
    int insert(Clock record);

    //查询标签
    List<Clock> selectByPrimaryKey(Clock clock);

    //修改标签
    int updateByPrimaryKey(Clock record);

}