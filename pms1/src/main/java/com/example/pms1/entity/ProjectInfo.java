package com.example.pms1.entity;

/**
 * @author loself
 * @date 2024-07-02 17:19
 */
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
 * 项目信息表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("project_info")
public class ProjectInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String projectName;

    private String englishAbbreviation;

    private Integer projectStage;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date plannedLaunchDate;

    private String financialCode;

    private Integer projectCategory;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date plannedInitialInspectionDate;

    private Integer department;

    private Integer projectSubCategory;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date plannedFinalInspectionDate;

    private Integer region;

    private String importanceLevel;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date projectStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date plannedCompletionDate;

    private Integer company;

    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getEnglishAbbreviation() {
        return englishAbbreviation;
    }

    public void setEnglishAbbreviation(String englishAbbreviation) {
        this.englishAbbreviation = englishAbbreviation;
    }

    public Integer getProjectStage() {
        return projectStage;
    }

    public void setProjectStage(Integer projectStage) {
        this.projectStage = projectStage;
    }

    public Date getPlannedLaunchDate() {
        return plannedLaunchDate;
    }

    public void setPlannedLaunchDate(Date plannedLaunchDate) {
        this.plannedLaunchDate = plannedLaunchDate;
    }

    public String getFinancialCode() {
        return financialCode;
    }

    public void setFinancialCode(String financialCode) {
        this.financialCode = financialCode;
    }

    public Integer getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(Integer projectCategory) {
        this.projectCategory = projectCategory;
    }

    public Date getPlannedInitialInspectionDate() {
        return plannedInitialInspectionDate;
    }

    public void setPlannedInitialInspectionDate(Date plannedInitialInspectionDate) {
        this.plannedInitialInspectionDate = plannedInitialInspectionDate;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Integer getProjectSubCategory() {
        return projectSubCategory;
    }

    public void setProjectSubCategory(Integer projectSubCategory) {
        this.projectSubCategory = projectSubCategory;
    }

    public Date getPlannedFinalInspectionDate() {
        return plannedFinalInspectionDate;
    }

    public void setPlannedFinalInspectionDate(Date plannedFinalInspectionDate) {
        this.plannedFinalInspectionDate = plannedFinalInspectionDate;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public String getImportanceLevel() {
        return importanceLevel;
    }

    public void setImportanceLevel(String importanceLevel) {
        this.importanceLevel = importanceLevel;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public Date getPlannedCompletionDate() {
        return plannedCompletionDate;
    }

    public void setPlannedCompletionDate(Date plannedCompletionDate) {
        this.plannedCompletionDate = plannedCompletionDate;
    }

    public Integer getCompany() {
        return company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "ProjectInfo{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", englishAbbreviation='" + englishAbbreviation + '\'' +
                ", projectStage=" + projectStage +
                ", plannedLaunchDate=" + plannedLaunchDate +
                ", financialCode='" + financialCode + '\'' +
                ", projectCategory=" + projectCategory +
                ", plannedInitialInspectionDate=" + plannedInitialInspectionDate +
                ", department=" + department +
                ", projectSubCategory=" + projectSubCategory +
                ", plannedFinalInspectionDate=" + plannedFinalInspectionDate +
                ", region=" + region +
                ", importanceLevel='" + importanceLevel + '\'' +
                ", projectStartDate=" + projectStartDate +
                ", plannedCompletionDate=" + plannedCompletionDate +
                ", company=" + company +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
