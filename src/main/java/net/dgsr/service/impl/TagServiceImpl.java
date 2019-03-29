package net.dgsr.service.impl;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.dao.TagDao;
import net.dgsr.model.Tag;
import net.dgsr.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;


    /**
     * 添加标签
     * @param id
     * @param name
     * @return
     */
    @Override
    public ServiceResponse<?> addTag( String name) {
        return null;
    }


    /**
     * 从企业微信获取标签添加数据库
     * @return
     */
    @Override
    public int addTagByWX(Tag tag) {
        return tagDao.insertByWX(tag);
    }


    /**
     * 查询标签
     * @param tag
     * @return
     */
    @Override
    public ServiceResponse<?> selectTag( Tag tag ) {
        Tag tag2 = null;
        tag2 = tagDao.selectByPrimaryKey(tag);

        if ( tag2 == null ){
            return ServiceResponse.createByErrorMessage("查询失败！");
        }
        return ServiceResponse.createBySuccess("查询成功！",tag2);
    }
}
