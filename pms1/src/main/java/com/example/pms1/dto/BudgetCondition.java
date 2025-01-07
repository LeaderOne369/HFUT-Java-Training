package com.example.pms1.dto;

import com.example.pms1.entity.Budget;

/**
 * @author loself
 * @date 2024-07-04 16:26
 */
public enum BudgetCondition {
    /**
     * 预算项的排序类型
     * CREATION_TIME(1,"创建时间"),
     *     NAME(2,"名称"),
     *     AMOUNT(3,"金额");
     */
    CREATION_TIME(1,"创建时间"),
    NAME(2,"名称"),
    AMOUNT(3,"金额");


    private final Integer code;
    private final String description;

    BudgetCondition(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
