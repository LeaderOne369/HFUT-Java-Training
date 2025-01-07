package com.example.pms1;

import com.example.pms1.dto.ProjectCondition;
import com.example.pms1.dto.Resp;
import com.example.pms1.entity.ListProjects;
import com.example.pms1.entity.ProjectInfo;
import com.example.pms1.mapper.ProjectInfoMapper;
import com.example.pms1.service.ProjectInfoService;
import com.example.pms1.service.ProjectListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * @author loself
 * @date 2024-07-03 14:24
 */

@SpringBootTest
public class ListProjectsTests {
    @Autowired
    private ProjectListService projectListService;

    @Autowired
    private ProjectInfoService projectInfoService;

    @Autowired
    private ProjectInfoMapper projectInfoMapper;
    @Test
    void getall(){
            String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJmOWI0M2EzNWZlODA0YjA5YmE5OTlhMTEwZGI2Nzg1NiIsInN1YiI6InVzZXIzIiwiaXNzIjoieGwiLCJpYXQiOjE3MjAxNjc3NTksImV4cCI6MTcyMDE3MTM1OX0.zsoG96Mjv7xI5umTh3F79IR90a5hylzQV2E2mvX5aD8";
            projectListService.getValidProjectIds(token).forEach(System.out::println);
//        Resp projectList = projectListService.getProjectList(token, 2, 1, ProjectCondition.PROGRESS);
//
//        List<ListProjects> list = (List<ListProjects>) projectList.getData();
//        list.stream().forEach(System.out::println);
    }

    @Test
    void getbyid(){
    }

    @Test
    void savenewproj(){
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setProjectName("p2");
        projectInfo.setProjectStage(1);
        projectInfo.setPlannedLaunchDate(new Date());
        projectInfo.setProjectCategory(1);
        projectInfo.setPlannedInitialInspectionDate(new Date());
        projectInfo.setDepartment(1);
        projectInfo.setProjectSubCategory(1);
        projectInfo.setPlannedFinalInspectionDate(new Date());
        projectInfo.setRegion(1);
        projectInfo.setImportanceLevel("low");
        projectInfo.setProjectStartDate(new Date());
        projectInfo.setPlannedCompletionDate(new Date());
        projectInfo.setCompany(1);
//        projectInfo.setStatus("在途");
//        projectInfo.setCreatedAt(new Date());
//        boolean res = projectInfoMapper.insert(projectInfo) > 0;
//        System.out.println(res);
//        System.out.println(projectInfo.getId());
        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIwZjVjY2IxZjlhYWE0NjVlOGZlZGU4MjA0OTVjOWViYSIsInN1YiI6InVzZXIzIiwiaXNzIjoieGwiLCJpYXQiOjE3MjAxODM2NzUsImV4cCI6MTcyMDE4NzI3NX0.2SD0Rz4x-J9_jmMopL0scNpIDRXwyuqzB8rpuNf3jZA";
        Object data = projectInfoService.addNewProject(projectInfo, token).getData();
        System.out.println(data.toString());

    }


}
