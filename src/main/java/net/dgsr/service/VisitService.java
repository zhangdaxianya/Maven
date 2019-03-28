package net.dgsr.service;

import net.dgsr.comment.ServiceResponse;
import net.dgsr.model.Visit;

public interface VisitService {

    //添加拜访计划
    ServiceResponse<?> addVisit(Visit visit);

    //根据id删除拜访计划
    ServiceResponse<?> deleteVisit(int id);

    //更新拜访计划
    ServiceResponse<?> updateVisit(Visit visit);

    //查询拜访计划
    ServiceResponse<?> selectVisit(Visit visit);
}


