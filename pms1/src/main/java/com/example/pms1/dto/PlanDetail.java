package com.example.pms1.dto;

import com.example.pms1.entity.Plan;
import lombok.Data;

@Data
public class PlanDetail extends Plan {

    private String projectName;

    private String predecessorPlanName;

}
