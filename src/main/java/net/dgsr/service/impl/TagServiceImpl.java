package net.dgsr.service.impl;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.dao.TagDao;
import net.dgsr.model.Tag;
import net.dgsr.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<Tag> list  = tagDao.selectByPrimaryKey(tag);
        return ServiceResponse.createBySuccess("查询成功！",list);
    }


    /**
     * 更新标签
     * @param tag
     * @return
     */
    @Override
    public ServiceResponse<?> updateTag(Tag tag) {
        int rowCount = tagDao.updateByPrimaryKey(tag);
        if( rowCount > 0 ){
            return ServiceResponse.createBySuccessMessage("更新成功！");
        }
        return ServiceResponse.createByErrorMessage("更新失败！");
    }


    /**
     * 删除标签
     * @param tagid
     * @return
     */
    @Override
    public ServiceResponse<?> deleteTag(int tagid) {
        int rowCount = tagDao.deleteByPrimaryKey(tagid);
        if( rowCount > 0 ){
            return ServiceResponse.createBySuccessMessage("删除成功！");
        }
        return ServiceResponse.createByErrorMessage("删除失败！");
    }


}
