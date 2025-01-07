package com.example.pms1.dto;

/**
 * @author loself
 * @date 2024-07-03 9:45
 */
public enum ProjectCondition {
    /**
     * 项目的排序类型
     *      TIME(1,"创建时间顺序"),
     *     NAME(2,"名称顺序"),
     *     PROGRESS(3,"进度顺序"),
     *     Budget(4,"预算顺序");
     */
    TIME(1,"创建时间顺序"),
    NAME(2,"名称顺序"),
    PROGRESS(3,"进度顺序"),
    Budget(4,"预算顺序");


    private final Integer code;
    private final String description;

    ProjectCondition(Integer code, String description) {
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
