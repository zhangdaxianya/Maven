package net.dgsr.service.impl;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.dao.NoticeDao;
import net.dgsr.model.Notice;
import net.dgsr.service.NoticeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;


    /**
     * 添加消息
     * @param notice
     * @return
     */
    @Override
    public ServiceResponse<?> sendNotice(Notice notice) {

        // 判断参数
        if (StringUtils.isBlank(notice.getMsgObj()) && StringUtils.isBlank(notice.getMsgObjType().toString())) {
            return ServiceResponse.createByErrorMessage("消息对象不能为空");
        }
        if (StringUtils.isBlank(notice.getTitle())) {
            return ServiceResponse.createByErrorMessage("标题不能为空");
        }
        if (StringUtils.isBlank(notice.getMsgContent())){
            return ServiceResponse.createByErrorMessage("内容不能为空");
        }
        if (StringUtils.isBlank(notice.getSender())) {
            return ServiceResponse.createByErrorMessage("发送人不能为空");
        }
        if (notice.getStatus() != 0 && notice.getStatus() != 1 && notice.getStatus() != 2) {
            return ServiceResponse.createByErrorMessage("状态错误");
        }

        //添加一条消息
        if ( notice.getId() == null ){
            if ( notice.getTimingSendTime() == null ){
                notice.setSendTime(new Date());
            }
            int rowCount = noticeDao.insert(notice);
            if ( rowCount > 0 ){
                if ( notice.getStatus() == 2 ){
                    return ServiceResponse.createBySuccessMessage("草稿添加成功！");
                }else {
                    //判断发送时间(立即发送)
                    if ( notice.getTimingSendTime() == null ){

                    }else {  //定时发送

                    }
                }

            }
        }else {
            //调用更新消息的方法
           int rowCount =  noticeDao.updateByPrimaryKey(notice);
           if ( rowCount > 0 ){
               if ( notice.getStatus() == 2 ){
                   return ServiceResponse.createBySuccessMessage("草稿添加成功！");
               }else {
                   //判断发送时间(立即发送)
                   if ( notice.getTimingSendTime() == null ){



                   }else {  //定时发送

                   }
               }
           }
        }

        return null;
    }


    /**
     * 删除消息
     * @param id
     * @return
     */
    @Override
    public ServiceResponse<?> deleteNotice(int id) {
        int rowCount = noticeDao.deleteByPrimaryKey(id);
        if ( rowCount > 0 ){
            return ServiceResponse.createBySuccessMessage("删除成功！");
        }
        return ServiceResponse.createByErrorMessage("删除失败！");
    }


    /**
     * 查询消息
     * @param notice
     * @return
     */
    @Override
    public ServiceResponse<?> selectNotice(Notice notice) {

        //分页数据
        if( notice.getPageNum() == null){
            notice.setPageNum(0);
        }else {
            notice.setPageNum( (notice.getPageNum() - 1) * 10 );
        }

        if ( notice.getPageSize() == null ){
            notice.setPageSize(10);
        }

        //获取消息数据
        List<Notice> list = noticeDao.selectByPrimaryKey(notice);

        return ServiceResponse.createBySuccess("查询成功！",list);
    }


}
