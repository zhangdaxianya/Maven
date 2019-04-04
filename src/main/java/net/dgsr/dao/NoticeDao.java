package net.dgsr.dao;

import net.dgsr.model.Notice;

import java.util.List;

public interface NoticeDao {

    //删除
    int deleteByPrimaryKey(Integer id);

    //添加
    int insert(Notice record);

    //查询
    List<Notice> selectByPrimaryKey(Notice notice);

    //修改
    int updateByPrimaryKey(Notice record);
}