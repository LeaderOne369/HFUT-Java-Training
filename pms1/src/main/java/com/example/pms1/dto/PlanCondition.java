package com.example.pms1.dto;

/**
 * @author loself
 * @date 2024-07-04 12:51
 */
public enum PlanCondition {

    /**
     * 项目计划的排序类型
     * CREATION_TIME(1,"创建时间"),
     *     NAME(2,"名称"),
     *     PRIORITY(3,"优先级");
     */
    CREATION_TIME(1,"创建时间"),
    NAME(2,"名称"),
    PRIORITY(3,"优先级");


    private final Integer code;
    private final String description;

    PlanCondition(Integer code, String description) {
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
