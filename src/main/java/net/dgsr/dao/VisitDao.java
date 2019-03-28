package net.dgsr.dao;

import net.dgsr.model.Visit;

import java.util.List;

public interface VisitDao {

    //删除拜访记录
    int deleteByPrimaryKey(Integer id);

    //添加拜访记录
    int insert(Visit record);

    //查询拜访记录
    List<Visit> selectByPrimaryKey(Visit visit );

    //更新拜访记录
    int updateByPrimaryKey(Visit record);

    //获取总数量
    int total();

}