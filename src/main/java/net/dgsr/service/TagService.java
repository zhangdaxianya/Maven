package net.dgsr.service;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.model.Tag;

public interface TagService {

    //添加标签
    ServiceResponse<?> addTag(String name);

    //从企业微信获取标签添加数据库
    int addTagByWX(Tag tag);

    //查询标签
    ServiceResponse<?> selectTag(Tag tag);
}
