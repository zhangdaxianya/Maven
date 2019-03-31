package net.dgsr.service;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.model.ClientInfo;

public interface ClientInfoService {

    //添加
    ServiceResponse<?> addClientInfo(ClientInfo clientInfo);

    //删除
    ServiceResponse<?> deleteClientInfo(int id);

    //更新
    ServiceResponse<?> updateClientInfo(ClientInfo clientInfo);

    //查询
    ServiceResponse<?> selectClientInfo(ClientInfo clientInfo);

}
