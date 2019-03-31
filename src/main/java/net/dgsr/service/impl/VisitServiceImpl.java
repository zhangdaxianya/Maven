package net.dgsr.service.impl;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.dao.VisitDao;
import net.dgsr.model.Visit;
import net.dgsr.service.VisitService;
import net.dgsr.vo.TotalAndDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private VisitDao visitDao;


    /**
     * 添加拜访计划
     * @param visit
     * @return
     */
    @Override
    public ServiceResponse<?> addVisit(Visit visit) {

        //设置创建时间为当前时间
        visit.setCreate_time(new Date());

        //设置拜访计划的状态
        visit.setStatus(0);

        int rowCount = visitDao.insert(visit);
        if( rowCount > 0 ){
            return  ServiceResponse.createBySuccessMessage("添加成功！");
        }
        return ServiceResponse.createByErrorMessage("添加失败！");
    }


    /**
     * 根据id删除拜访计划
     * @param id
     * @return
     */
    @Override
    public ServiceResponse<?> deleteVisit(int id) {
        int rowCount = visitDao.deleteByPrimaryKey(id);
        if( rowCount > 0 ){
          return  ServiceResponse.createBySuccessMessage("删除成功！");
        }
        return ServiceResponse.createByErrorMessage("删除失败！");
    }


    /**
     * 更新拜访计划
     * @param id
     * @return
     */
    @Override
    public ServiceResponse<?> updateVisit(Visit visit) {
        int rowCount = visitDao.updateByPrimaryKey(visit);
        if( rowCount > 0 ){
            return  ServiceResponse.createBySuccessMessage("更新成功！");
        }
        return ServiceResponse.createByErrorMessage("更新失败！");
    }


    /**
     * 查询拜访计划
     * @param visit
     * @return
     */
    @Override
    public ServiceResponse<?> selectVisit(Visit visit) {
        TotalAndDataVo dataVo = new TotalAndDataVo();

        //分页数据
        visit.setPageNum( (visit.getPageNum() - 1) * 10 );

        //获取数据
        List<Visit> visitList = visitDao.selectByPrimaryKey(visit);

        //获取数量
        int total = visitDao.total();

        //设值
        dataVo.setObjectList(visitList);
        dataVo.setTotal(total);

        return ServiceResponse.createByError("查询成功！",dataVo);

    }
}
