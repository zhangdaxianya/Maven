package net.dgsr.dao;

import net.dgsr.model.Tag;

public interface TagDao {
    int deleteByPrimaryKey(Integer tagid);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer tagid);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);
}