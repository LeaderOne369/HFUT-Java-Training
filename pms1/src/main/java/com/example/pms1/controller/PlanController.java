package com.example.pms1.controller;

import com.example.pms1.dto.PlanCondition;
import com.example.pms1.dto.ProjectCondition;
import com.example.pms1.dto.Resp;
import com.example.pms1.entity.Plan;
import com.example.pms1.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author loself
 * @date 2024-07-03 17:25
 */

/**
 * 用户用于处理可见项目的项目计划的一些接口
 */
@RestController
@RequestMapping("/plans")
public class PlanController {
    @Autowired
    private PlanService planService;

    /**
     * 用户用于添加可见项目下的项目计划的接口
     * @param token 携带的用户信息
     * @param plan  新增的项目计划的信息
     * @param projectId 对应的项目id
     * @return
     */
    @PostMapping("/addnewplan")
    public Resp addNewPlan(@RequestHeader String token, @RequestBody Plan plan,@RequestParam Integer projectId){
        return planService.addNewPlan(token,plan,projectId);
    }

    /**
     * 用户用于获取对应项目下的所有项目计划的接口
     * @param token 携带的用户信息
     * @param projectId 对应的项目id
     * @param pageSize  分页大小
     * @param pageNum   分页序号
     * @param condition 排序类型
     * @return
     */

    @GetMapping("/getplansofproject")
    public Resp getPlansOfProject(@RequestHeader String token,
                                  @RequestParam Integer projectId,
                                  @RequestParam Integer pageSize,
                                  @RequestParam Integer pageNum,
                                  @RequestParam(required = false, defaultValue = "CREATION_TIME") PlanCondition condition
                                  ){
        return planService.getPlansOfProject(token,projectId,pageSize,pageNum,condition);
    }

    /**
     * 获取单一项目计划信息的接口
     * @param token 携带的用户信息
     * @param planId    所查询的计划id
     * @return
     */
    @GetMapping("/getplanbyid")
    public Resp getById(
            @RequestHeader String token,
            @RequestParam Integer planId){
        return planService.getPlanById(token,planId);
    }

    /**
     * 计划负责人编辑计划信息的接口
     * @param token 携带的用户信息
     * @param plan  编辑后的计划信息
     * @return
     */
    @PutMapping("/editplan")
    public Resp editPlan(@RequestHeader String token,
                         @RequestBody Plan plan
                         ){
        return planService.editPlan(token,plan);
    }

    /**
     * 计划负责人删除计划的接口
     * @param token 携带的用户信息
     * @param planId    要删除的计划id
     * @return
     */
    @DeleteMapping("/deleteplan")
    public Resp deletePlan(@RequestHeader String token,
                           @RequestParam Integer planId){
        return planService.deletePlan(token,planId);
    }


    /**
     * 计划负责人用于完成计划的接口
     * @param token 携带的用户信息2
     * @param planId    要完成的计划id
     * @return
     */
    @PutMapping("/completeplan")
    public Resp complete(@RequestHeader String token
            ,@RequestParam Integer planId){
        return planService.completePlan(token,planId);
    }


    /**
     * 用户模糊查询对应项目下的计划列表接口
     * @param token 携带的用户信息
     * @param pageSize  分页大小
     * @param pageNum   分页序号
     * @param condition   排序类型
     * @param requirement   模糊查询内容
     * @param projectId 对应的项目id
     * @return
     */
    @GetMapping("/search")
    public Resp search(
            @RequestHeader String token,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNum,
            @RequestParam(required = false, defaultValue = "CREATION_TIME") PlanCondition condition,
            @RequestParam String requirement,
            @RequestParam Integer projectId,
            @RequestParam(required = false) String status
    ){
        return planService.search(token,pageSize,pageNum,condition,requirement,projectId, status);
    }

    /**
     * 用户获取对应项目下的计划拓扑序列的接口
     * @param token 携带的用户信息
     * @param projectId 对应的项目id
     * @param pageSize  分页大小
     * @param pageNum   分页序号
     * @return
     */

    @GetMapping("/gettopolist")
    public Resp getTopoList(
            @RequestHeader String token,
            @RequestParam Integer projectId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNum,
            @RequestParam(required = false) String status
    ){
        return planService.getTopoList(token,projectId,pageSize,pageNum, status);
    }

}
