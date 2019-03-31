package net.dgsr.service.impl;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.dao.ClientInfoDao;
import net.dgsr.model.ClientInfo;
import net.dgsr.service.ClientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientInfoServiceImpl implements ClientInfoService {

    @Autowired
    private ClientInfoDao clientInfoDao;


    /**
     * 添加客户
     * @param clientInfo
     * @return
     */
    @Override
    public ServiceResponse<?> addClientInfo(ClientInfo clientInfo) {

        //设置客户状态为0
        if( clientInfo.getStatus() == null ){
            clientInfo.setStatus(0);
        }

        //设置范围为500
        if( clientInfo.getScope() == null ){
            clientInfo.setScope(500);
        }

        int rowCount = clientInfoDao.insert(clientInfo);
        if( rowCount > 0 ){
            return ServiceResponse.createBySuccessMessage("添加成功！");
        }
        return ServiceResponse.createByErrorMessage("添加失败！");
    }


    /**
     * 删除客户
     * @param id
     * @return
     */
    @Override
    public ServiceResponse<?> deleteClientInfo(int id) {
        int rowCount = clientInfoDao.deleteByPrimaryKey(id);
        if( rowCount > 0 ){
            return ServiceResponse.createBySuccessMessage("删除成功！");
        }
        return ServiceResponse.createByErrorMessage("删除失败！");
    }


    /**
     * 更新客户
     * @param clientInfo
     * @return
     */
    @Override
    public ServiceResponse<?> updateClientInfo(ClientInfo clientInfo) {
        int rowCount = clientInfoDao.updateByPrimaryKey(clientInfo);
        if( rowCount > 0 ){
            return ServiceResponse.createBySuccessMessage("更新成功！");
        }
        return ServiceResponse.createByErrorMessage("更新失败！");
    }


    /**
     * 查询客户
     * @param clientInfo
     * @return
     */
    @Override
    public ServiceResponse<?> selectClientInfo(ClientInfo clientInfo) {
        List<ClientInfo> list = clientInfoDao.selectByPrimaryKey(clientInfo);
        return ServiceResponse.createBySuccess("查询成功！",list);
    }


}
