package com.example.pms1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pms1.dto.ProjectCondition;
import com.example.pms1.dto.Resp;
import com.example.pms1.entity.ListProjects;

import java.util.List;

/**
 * @author loself
 * @date 2024-07-03 9:10
 */
public interface ProjectListService extends IService<ListProjects> {

    Resp getProjectList(String token, Integer pagesize, Integer pagenum, ProjectCondition condition);

    Resp getListByRequirement(String requirement, String status, String token, Integer pagesize, Integer pagenum, ProjectCondition condition) ;

    List<Integer> getValidProjectIds(String token);
}
