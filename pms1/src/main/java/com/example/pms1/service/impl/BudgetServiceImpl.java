package com.example.pms1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pms1.dto.BudgetCondition;
import com.example.pms1.dto.HttpStatus;
import com.example.pms1.dto.Resp;
import com.example.pms1.dto.Status;
import com.example.pms1.entity.Budget;
import com.example.pms1.mapper.BudgetMapper;
import com.example.pms1.service.BudgetService;
import com.example.pms1.service.ProjectMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @author loself
 * @date 2024-07-04 16:20
 */
@Service
public class BudgetServiceImpl extends ServiceImpl<BudgetMapper, Budget> implements BudgetService {
    @Autowired
    private ProjectMemberService projectMemberService;

    @Override
    public Resp editBudget(String token, Budget budget) {
        Boolean validated = projectMemberService.isManager(token, budget.getProjectId());
        if (validated) {
            budget.setUpdatedAt(new Timestamp(new Date().getTime()));
            boolean res = updateById(budget);
            if (res) {
                return Resp.success("预算信息更新成功");
            } else {
                return Resp.error(HttpStatus.MODIFY_ERROR);
            }
        }
        return Resp.error(HttpStatus.NO_PERMISSION);
    }

    @Override
    public Resp getBudgetOfProject(String token, Integer projectId, Integer pageSize, Integer pageNum, BudgetCondition condition) {
        Boolean validated = projectMemberService.validateViewPermissionsToProj(token, projectId);
        if (validated) {
            LambdaQueryWrapper<Budget> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Budget::getProjectId, projectId);
            switch (condition) {
                case CREATION_TIME:
                    queryWrapper.orderByAsc(Budget::getCreatedAt);
                    break;
                case NAME:
                    queryWrapper.orderByAsc(Budget::getName);
                    break;
                case AMOUNT:
                    String customSortSql = "CASE `currency` " +
                            "WHEN 'CNY' THEN `amount` " +
                            "WHEN 'USD' THEN `amount` * 7.2 " +
                            "WHEN 'EUR' THEN `amount` * 7.8 " +
                            "WHEN 'JPY' THEN `amount` * 0.05 " +
                            "WHEN 'GBP' THEN `amount` * 9.3 " +
                            "ELSE `amount` " +
                            "END";
                    queryWrapper.last("ORDER BY " + customSortSql + " ASC");
                    break;
                default:
                    queryWrapper.orderByAsc(Budget::getCreatedAt);
            }
            Page<Budget> budgetPage = new Page<>(pageNum, pageSize);
            Page<Budget> page = page(budgetPage, queryWrapper);
            return Resp.success(page.getRecords());
        }
        return Resp.error(HttpStatus.NO_PERMISSION);
    }

    @Override
    public Resp search(String token, Integer pageSize, Integer pageNum, Integer projectId, BudgetCondition condition, String requirement, String status) {
        Boolean validated = projectMemberService.validateViewPermissionsToProj(token, projectId);
        if (validated) {
            LambdaQueryWrapper<Budget> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Budget::getProjectId, projectId);
            switch (condition) {
                case CREATION_TIME:
                    queryWrapper.orderByAsc(Budget::getCreatedAt);
                    break;
                case NAME:
                    queryWrapper.orderByAsc(Budget::getName);
                    break;
                case AMOUNT:
                    String customSortSql = "CASE `currency` " +
                            "WHEN 'CNY' THEN `amount` " +
                            "WHEN 'USD' THEN `amount` * 7.2 " +
                            "WHEN 'EUR' THEN `amount` * 7.8 " +
                            "WHEN 'JPY' THEN `amount` * 0.05 " +
                            "WHEN 'GBP' THEN `amount` * 9.3 " +
                            "ELSE `amount` " +
                            "END";
                    queryWrapper.last("ORDER BY " + customSortSql + " ASC");
                    break;
                default:
                    queryWrapper.orderByAsc(Budget::getCreatedAt);
            }
            Page<Budget> budgetPage = new Page<>(pageNum, pageSize);
            if (status != null) {
                queryWrapper.eq(Budget::getStatus, status);
            }
            queryWrapper.and(w -> {
                w.or(q -> q.like(Budget::getName, requirement))
                        .or(q -> q.like(Budget::getDescription, requirement));
            });
            Page<Budget> page = page(budgetPage, queryWrapper);
            return Resp.success(page);
        }
        return Resp.error(HttpStatus.NO_PERMISSION);
    }

    @Override
    public Resp addNewBudget(String token, Budget budget) {
        Boolean validated = projectMemberService.isManager(token, budget.getProjectId());
        if (validated) {
            budget.setStatus(Status.UNSETTLED);
            budget.setCreatedAt(new Timestamp(new Date().getTime()));
            budget.setUpdatedAt(new Timestamp(new Date().getTime()));
            boolean saved = save(budget);
            return saved ? Resp.success("新增预算项成功") : Resp.error(HttpStatus.ADD_ERROR);
        }
        return Resp.error(HttpStatus.NO_PERMISSION);
    }

    @Override
    public Resp deleteBudget(String token, Integer budgetId) {
        Budget budget = getById(budgetId);
        if (Objects.isNull(budget)) {
            return Resp.error(HttpStatus.ERROR);
        }
        Boolean validated = projectMemberService.isManager(token, budget.getProjectId());
        if (validated) {
            boolean res = removeById(budgetId);
            return res ? Resp.success("删除预算项成功") : Resp.error(HttpStatus.DELETE_ERROR);
        }
        return Resp.error(HttpStatus.NO_PERMISSION);
    }

    @Override
    public Resp getBudgetById(String token, Integer budgetId) {
        Budget budget = getById(budgetId);
        if (Objects.isNull(budget)) {
            return Resp.error(HttpStatus.ERROR);
        }
        Boolean validated = projectMemberService.validateViewPermissionsToProj(token, budget.getProjectId());
        if (validated) {
            return Resp.success(budget);
        }
        return Resp.error(HttpStatus.NO_PERMISSION);
    }

    @Override
    public Resp settleBudget(String token, Integer budgetId) {
        Budget budget = getById(budgetId);
        if (Objects.isNull(budget)) {
            return Resp.error(HttpStatus.ERROR);
        }
        Boolean validated = projectMemberService.isManager(token, budget.getProjectId());
        if (validated) {
            budget.setStatus(Status.SETTLED);
            boolean res = updateById(budget);
            return res ? Resp.success("预算项结算成功") : Resp.error(HttpStatus.MODIFY_ERROR);
        }
        return Resp.error(HttpStatus.NO_PERMISSION);
    }
}
