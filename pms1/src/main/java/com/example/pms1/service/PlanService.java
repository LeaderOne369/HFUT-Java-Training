package com.example.pms1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pms1.dto.PlanCondition;
import com.example.pms1.dto.Resp;
import com.example.pms1.entity.Plan;

/**
 * @author loself
 * @date 2024-07-03 17:24
 */
public interface PlanService extends IService<Plan> {
    Resp addNewPlan(String token, Plan plan, Integer projectId);

    Resp getPlansOfProject(String token, Integer projectId, Integer pageSize, Integer pageNum, PlanCondition condition);

    Resp editPlan(String token, Plan planId);

    Resp deletePlan(String token, Integer planid);

    Resp completePlan(String token, Integer planId);

    Resp search(String token, Integer pageSize, Integer pageNum, PlanCondition condition, String requirement, Integer projectId, String status);

    Resp getTopoList(String token, Integer projectId, Integer pageSize, Integer pageNum, String status);

    Resp getPlanById(String token, Integer planId);
}
