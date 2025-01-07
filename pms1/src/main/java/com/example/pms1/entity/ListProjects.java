package com.example.pms1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author loself
 * @date 2024-07-03 14:04
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("project_list_view")
public class ListProjects implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId( type = IdType.AUTO)
    private Long projectId;
    private String projectName;
    private String projectCategoryName;
    private String projectSubcategoryName;
    private String importanceLevel;
    private Double totalBudget;
    private Double projectProgress;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date projectCreationTime;
    private String projectStatus;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectCategoryName() {
        return projectCategoryName;
    }

    public void setProjectCategoryName(String projectCategoryName) {
        this.projectCategoryName = projectCategoryName;
    }

    public String getProjectSubcategoryName() {
        return projectSubcategoryName;
    }

    public void setProjectSubcategoryName(String projectSubcategoryName) {
        this.projectSubcategoryName = projectSubcategoryName;
    }

    public String getImportanceLevel() {
        return importanceLevel;
    }

    public void setImportanceLevel(String importanceLevel) {
        this.importanceLevel = importanceLevel;
    }

    public Double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(Double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public Double getProjectProgress() {
        return projectProgress;
    }

    public void setProjectProgress(Double projectProgress) {
        this.projectProgress = projectProgress;
    }

    public Date getProjectCreationTime() {
        return projectCreationTime;
    }

    public void setProjectCreationTime(Date projectCreationTime) {
        this.projectCreationTime = projectCreationTime;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    @Override
    public String toString() {
        return "ListProjects{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectCategoryName='" + projectCategoryName + '\'' +
                ", projectSubcategoryName='" + projectSubcategoryName + '\'' +
                ", importanceLevel=" + importanceLevel +
                ", totalBudget=" + totalBudget +
                ", projectProgress=" + projectProgress +
                ", projectCreationTime=" + projectCreationTime +
                '}';
    }
}
