package net.dgsr.dao;

import net.dgsr.model.Tag;

import java.util.List;

public interface TagDao {

    //删除
    int deleteByPrimaryKey(Integer tagid);

    //从微信添加标签
    int insertByWX(Tag record);

    //添加标签
    int insert(String name);

    //查询
    List<Tag> selectByPrimaryKey(Tag tag);

    //修改
    int updateByPrimaryKey(Tag record);
}