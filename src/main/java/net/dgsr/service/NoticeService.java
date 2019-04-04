package net.dgsr.service;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.model.Notice;

public interface NoticeService {

    //发送消息
    ServiceResponse<?> sendNotice(Notice notice);

    //删除消息
    ServiceResponse<?> deleteNotice(int id);

    //修该消息
    ServiceResponse<?> selectNotice(Notice notice);

}
