package com.example.pms1.dto;

import com.example.pms1.entity.ProjectInfo;
import lombok.Data;

@Data
public class ProjectDetail extends ProjectInfo {

    private String companyName;

    private String departmentName;

    private String projectCategoryName;

    private String projectSubCategoryName;

    private String projectStageName;

    private String regionName;

}
