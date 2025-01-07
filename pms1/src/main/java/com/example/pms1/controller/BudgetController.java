package com.example.pms1.controller;

import com.example.pms1.dto.BudgetCondition;
import com.example.pms1.dto.Resp;
import com.example.pms1.entity.Budget;
import com.example.pms1.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 项目预算表的相关接口
 * @author loself
 * @date 2024-07-04 16:23
 */

@RestController
@RequestMapping("/budgets")
public class BudgetController {
    @Autowired
    private BudgetService budgetService;

    /**
     * 根据用户的项目可见性获取对应的项目预算项列表
     * @param token     携带的用户token
     * @param projectId 选择的项目id
     * @param pageSize  分页大小
     * @param pageNum   分页序号
     * @param condition 排序类型
     * @return  状态码200的resp的data为项目的所有预算项
     */

    @GetMapping("/getbudgetsofproject")
    public Resp getBudgetOfProject(
            @RequestHeader String token,
            @RequestParam Integer projectId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNum,
            @RequestParam(required = false, defaultValue = "CREATION_TIME") BudgetCondition condition
    ){
        return budgetService.getBudgetOfProject(token,projectId,pageSize,pageNum,condition);
    }

    /**
     * 根据用户输入信息模糊查询项目中的预算项
     * @param token 用户token
     * @param projectId 项目id
     * @param pageSize  分页大小
     * @param pageNum   分页序号
     * @param condition   排序类型
     * @param requirement   用户输入的信息
     * @return  状态码200的resp的data为该项目中模糊查询到的预算项
     */
    @GetMapping("/search")
    public Resp search(
            @RequestHeader String token,
            @RequestParam Integer projectId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNum,
            @RequestParam(required = false, defaultValue = "CREATION_TIME")BudgetCondition condition,
            @RequestParam String requirement,
            @RequestParam(required = false) String status
    ){
//        return budgetService.search(token,projectId,pageSize,pageNum,condition,requirement);
        return budgetService.search(token,pageSize,pageNum,projectId,condition,requirement, status);
    }


    /**
     * 根据判断用户是否为项目的项目经理，添加对应的项目的新预算项
     * @param token 携带的用户信息
     * @param budget    新增的预算项信息
     * @return
     */

    @PostMapping("/addnewbudget")
    public Resp addNewBudget(@RequestHeader String token, @RequestBody Budget budget){
        return budgetService.addNewBudget(token,budget);
    }

    /**
     * 根据判断用户是否为项目的项目经理，删除对应的项目的预算项
     * @param token 携带的用户信息
     * @param budgetId  要删除的预算项id
     * @return
     */
    @DeleteMapping("/deletebudget")
    public Resp deletePlan(@RequestHeader String token,
                           @RequestParam Integer budgetId){
        return budgetService.deleteBudget(token,budgetId);
    }

    /**
     * 根据用户对项目的可见性，获取单预算项的信息
     * @param token 携带的用户信息
     * @param budgetId  要查询的预算项id
     * @return
     */

    @GetMapping("/getbudgetbyid")
    public Resp getById(
            @RequestHeader String token,
            @RequestParam Integer budgetId){
        return budgetService.getBudgetById(token,budgetId);
    }

    /**
     * 根据判断用户是否为项目的项目经理，编辑对应的项目的预算项信息
     * @param token 携带的用户信息
     * @param budget    编辑后的预算项信息
     * @return
     */

    @PutMapping("/editbudget")
    public Resp editBudget(@RequestHeader String token,
                         @RequestBody Budget budget
    ){
        return budgetService.editBudget(token,budget);
    }

    /**
     * 根据判断用户是否为项目的项目经理，结算对应的项目的预算项
     * @param token 携带的用户信息
     * @param budgetId  要结算的预算项id
     * @return
     */
    @PutMapping("/settlebudget")
    public Resp settleBudget(
            @RequestHeader String token,
            @RequestParam Integer budgetId
    ){
        return budgetService.settleBudget(token,budgetId);
    }




}
