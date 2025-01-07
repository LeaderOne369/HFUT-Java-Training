package com.example.pms1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pms1.dto.*;
import com.example.pms1.entity.Plan;
import com.example.pms1.mapper.PlanMapper;
import com.example.pms1.service.PlanService;
import com.example.pms1.service.ProjectMemberService;
import com.example.pms1.service.UserService;
import com.example.pms1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * @author loself
 * @date 2024-07-03 17:24
 */

@Service
public class PlanServiceImpl extends ServiceImpl<PlanMapper, Plan> implements PlanService {


    @Autowired
    private UserService userService;

    @Autowired
    private ProjectMemberService projectMemberService;

    @Override
    public Resp addNewPlan(String token, Plan plan, Integer projectId) {
        try {
            String username = JwtUtil.parseJWT(token).getSubject();
            if(Objects.isNull(username)){
                return Resp.error(HttpStatus.ERROR);
            }
            if(!plan.getDueDate().after(plan.getStartDate())){
                return Resp.error(HttpStatus.ERROR_DATE);
            }
            plan.setProjectId(projectId);
            plan.setAssignedTo(userService.selectUserByUsername(username).getUserId());
            plan.setCreatedAt(new Timestamp(new Date().getTime()));
            plan.setUpdatedAt(new Timestamp( new Date().getTime()));
            boolean res = save(plan);
            if (res){
                return Resp.success("新增项目计划成功");
            }else {
                return Resp.error(HttpStatus.ADD_ERROR);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Resp getPlansOfProject(String token, Integer projectId, Integer pageSize, Integer pageNum, PlanCondition condition) {
        Boolean validated = projectMemberService.validateViewPermissionsToProj(token, projectId);
        if(validated){
            LambdaQueryWrapper<Plan> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Plan::getProjectId,projectId);
            switch (condition) {
                case CREATION_TIME:
                    queryWrapper.orderByAsc(Plan::getCreatedAt);
                    break;
                case NAME:
                    queryWrapper.orderByAsc(Plan::getName);
                    break;
                case PRIORITY:
                    queryWrapper.orderByAsc(Plan::getPriority);
                    break;
                default:
                    queryWrapper.orderByAsc(Plan::getCreatedAt);
            }
            Page<Plan> page = new Page<>(pageNum, pageSize);
            Page<Plan> page1 = page(page, queryWrapper);
            return Resp.success(page1);
        }
       return Resp.error(HttpStatus.NO_PERMISSION);
    }

    @Override
    public Resp editPlan(String token, Plan plan) {
        try {
            String username = JwtUtil.parseJWT(token).getSubject();
            if(Objects.isNull(username)){
                return Resp.error(HttpStatus.FORBIDDEN);
            }
            boolean check = !Objects.equals(userService.selectUserByUsername(username).getUserId(), plan.getAssignedTo());
            if(check){
                return Resp.error(HttpStatus.NO_PERMISSION);
            }
            plan.setUpdatedAt(new Timestamp(new Date().getTime()));
            boolean res = updateById(plan);
            if(res){
                return Resp.success("计划信息更新成功");
            }else {
                return Resp.error(HttpStatus.MODIFY_ERROR);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resp deletePlan(String token, Integer planId) {
        try {
            String username = JwtUtil.parseJWT(token).getSubject();
            if(Objects.isNull(username)){
                return Resp.error(HttpStatus.ERROR);
            }
            LambdaQueryWrapper<Plan> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Plan::getPlanId,planId);
            Plan one = getOne(queryWrapper);
            if(Objects.isNull(one)){
                return Resp.error(HttpStatus.ERROR);
            }
            boolean check = !Objects.equals(userService.selectUserByUsername(username).getUserId(), one.getAssignedTo());
            if(check){
                return Resp.error(HttpStatus.NO_PERMISSION);
            }
            boolean res = removeById(one.getPlanId());
            if(res){
                return Resp.success("计划删除成功");
            }else {
                return Resp.error(HttpStatus.DELETE_ERROR);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resp completePlan(String token, Integer planId) {
        try {
            String username = JwtUtil.parseJWT(token).getSubject();
            if(Objects.isNull(username)){
                return Resp.error(HttpStatus.NO_PERMISSION);
            }
            LambdaQueryWrapper<Plan> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Plan::getPlanId,planId);
            Plan one = getOne(queryWrapper);
            if(Objects.isNull(one)){
                return Resp.error(HttpStatus.NO_PERMISSION);
            }
            boolean check = !Objects.equals(userService.selectUserByUsername(username).getUserId(), one.getAssignedTo());
            if(check){
                return Resp.error(HttpStatus.NO_PERMISSION);
            }
            one.setStatus(Status.COMPLETED);
            one.setCompletionDate(new Date());
            boolean res = updateById(one);
            if(res){
                return Resp.success("计划已完成");
            }else {
                return Resp.error(HttpStatus.NO_PERMISSION);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public Resp search(String token, Integer pageSize, Integer pageNum, PlanCondition condition, String requirement, Integer projectId, String status) {
        Boolean validated = projectMemberService.validateViewPermissionsToProj(token, projectId);
        if(validated){
            LambdaQueryWrapper<Plan> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Plan::getProjectId,projectId);
            Page<Plan> page = new Page<>(pageNum, pageSize);
            switch (condition) {
                case CREATION_TIME:
                    queryWrapper.orderByAsc(Plan::getCreatedAt);
                    break;
                case NAME:
                    queryWrapper.orderByAsc(Plan::getName);
                    break;
                case PRIORITY:
                    queryWrapper.orderByAsc(Plan::getPriority);
                    break;
                default:
                    queryWrapper.orderByAsc(Plan::getCreatedAt);
            }
            if(status != null){
                queryWrapper.eq(Plan::getStatus,status);
            }
            queryWrapper.and(q->q
                    .or(w->w.like(Plan::getName,requirement)));
            Page<Plan> resultPage = page(page, queryWrapper);
            return Resp.success(resultPage);
        }
        return Resp.error(HttpStatus.NO_PERMISSION);
    }




    @Override
    public Resp getTopoList(String token, Integer projectId, Integer pageSize, Integer pageNum, String status) {
        Boolean validated = projectMemberService.validateViewPermissionsToProj(token, projectId);
        if(validated){
            LambdaQueryWrapper<Plan> planLambdaQueryWrapper = new LambdaQueryWrapper<Plan>().eq(Plan::getProjectId, projectId);
            if (status != null) {
                planLambdaQueryWrapper.eq(Plan::getStatus, status);
            }
            List<Plan> list = list();
            List<Plan> topologicalSortList = topologicalSort(list);
            if (Objects.isNull(topologicalSortList)){
                return Resp.error(HttpStatus.ERROR);
            }
            Page<Plan> planPage = new Page<>(pageNum, pageSize);
            Page<Plan> resultpage = page(planPage, planLambdaQueryWrapper);
            return Resp.success(resultpage);
        }
        return Resp.error(HttpStatus.NO_PERMISSION);
    }



    @Override
    public Resp getPlanById(String token, Integer planId) {
        PlanDetail plan = this.baseMapper.getPlanDetail(planId);
        if(Objects.isNull(plan)){
            return Resp.error(HttpStatus.ERROR);
        }
        Boolean validated = projectMemberService.validateViewPermissionsToProj(token, plan.getProjectId());
        if(validated){
            return Resp.success(plan);
        }
        return Resp.error(HttpStatus.NO_PERMISSION);
    }

    private List<Plan> topologicalSort(List<Plan> plans) {
        // 构建图的邻接表
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Plan> planMap = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        // 初始化图和入度
        for (Plan plan : plans) {
            int planId = plan.getPlanId();
            int predecessorPlanId = plan.getPredecessorPlanId() != null ? plan.getPredecessorPlanId() : -1;

            graph.putIfAbsent(planId, new ArrayList<>());
            planMap.put(planId, plan);
            inDegree.put(planId, 0);  // 初始化入度为0
        }
        // 构建图和计算入度
        for (Plan plan : plans) {
            int planId = plan.getPlanId();
            int predecessorPlanId = plan.getPredecessorPlanId() != null ? plan.getPredecessorPlanId() : -1;

            if (predecessorPlanId != -1) {
                graph.get(predecessorPlanId).add(planId);
                inDegree.put(planId, inDegree.get(planId) + 1);
            }
        }
        // 拓扑排序
        List<Plan> sortedPlans = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        // 将所有入度为 0 的节点入队
        for (int planId : inDegree.keySet()) {
            if (inDegree.get(planId) == 0) {
                queue.offer(planId);
            }
        }
        while (!queue.isEmpty()) {
            int current = queue.poll();
            sortedPlans.add(planMap.get(current));
            if (graph.containsKey(current)) {
                for (int neighbor : graph.get(current)) {
                    inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                    if (inDegree.get(neighbor) == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
        }
        if (sortedPlans.size() != plans.size()) {
            System.out.println("循环依赖");
            return null;
        }
        return sortedPlans;
    }
}
