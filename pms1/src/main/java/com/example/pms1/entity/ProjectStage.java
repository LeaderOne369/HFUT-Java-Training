package com.example.pms1.entity;

/**
 * @author loself
 * @date 2024-07-02 22:04
 */
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("project_stages")
public class ProjectStage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "stage_id", type = IdType.AUTO)
    private Integer stageId;

    private String stageName;

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    @Override
    public String toString() {
        return "ProjectStage{" +
                "stageId=" + stageId +
                ", stageName='" + stageName + '\'' +
                '}';
    }
}