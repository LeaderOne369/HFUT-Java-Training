package com.example.pms1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pms1.dto.BudgetCondition;
import com.example.pms1.dto.Resp;
import com.example.pms1.entity.Budget;

/**
 * @author loself
 * @date 2024-07-04 16:20
 */
public interface BudgetService extends IService<Budget> {
    Resp editBudget(String token, Budget budget);

    Resp getBudgetOfProject(String token, Integer projectId, Integer pageSize, Integer pageNum, BudgetCondition condition);

    Resp search(String token, Integer pageSize, Integer pageNum, Integer projectId, BudgetCondition condition, String requirement, String status);

    Resp addNewBudget(String token, Budget budget);

    Resp deleteBudget(String token, Integer budgetId);

    Resp getBudgetById(String token, Integer budgetId);

    Resp settleBudget(String token, Integer budgetId);
}
