package com.example.pms1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pms1.dto.Resp;
import com.example.pms1.entity.ProjectInfo;

/**
 * @author loself
 * @date 2024-07-03 15:33
 */
public interface ProjectInfoService extends IService<ProjectInfo> {
    Resp completeProject(String token, Integer projectId);

    Resp editProjectDetail(String token, ProjectInfo projectInfo, Integer projectId);

    Resp deleteProject(Integer projectId, String token);

    Resp addNewProject(ProjectInfo projectInfo, String token);

    Resp getProjectById(String token, Integer projectId);
}
