package com.example.pms1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pms1.dto.HttpStatus;
import com.example.pms1.dto.ProjectCondition;
import com.example.pms1.dto.Resp;
import com.example.pms1.entity.ListProjects;
import com.example.pms1.mapper.ListProjectsMapper;
import com.example.pms1.service.ProjectListService;
import com.example.pms1.service.ProjectMemberService;
import com.example.pms1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author loself
 * @date 2024-07-03 9:10
 */

@Service
public class ProjectListServiceImpl extends ServiceImpl<ListProjectsMapper, ListProjects> implements ProjectListService {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectMemberService projectMemberService;

    @Autowired
    private ListProjectsMapper listProjectsMapper;

    @Override
    public Resp getProjectList(String token, Integer pageSize, Integer pageNum, ProjectCondition condition) {

        List<Integer> validProjectIds = getValidProjectIds(token);
        Page<ListProjects> page = new Page<>(pageNum, pageSize);
        if (validProjectIds.isEmpty()) {
            return Resp.success(page);
        }
        QueryWrapper<ListProjects> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("project_id", validProjectIds);
        switch (condition) {
            case TIME:
                queryWrapper.orderByAsc("project_creation_time");
                break;
            case NAME:
                queryWrapper.orderByAsc("project_name");
                break;
            case PROGRESS:
                queryWrapper.orderByAsc("project_progress");
                break;
            case Budget:
                queryWrapper.orderByAsc("total_budget");
                break;
            default:
                queryWrapper.orderByAsc("project_creation_time");
        }
        Page<ListProjects> resultPage = page(page, queryWrapper);
        return Resp.success(resultPage);
    }

    @Override
    public Resp getListByRequirement(String requirement, String status, String token, Integer pageSize, Integer pageNum, ProjectCondition condition) {
        List<Integer> validProjectIds = getValidProjectIds(token);
        LambdaQueryWrapper<ListProjects> queryWrapper = new LambdaQueryWrapper<>();
        Page<ListProjects> page = new Page<>(pageNum, pageSize);
        if (validProjectIds.isEmpty()) {
            return Resp.success(page);
        }
        queryWrapper.in(ListProjects::getProjectId, validProjectIds);

        switch (condition) {
            case TIME:
                queryWrapper.orderByAsc(ListProjects::getProjectCreationTime);
                break;
            case NAME:
                queryWrapper.orderByAsc(ListProjects::getProjectName);
                break;
            case PROGRESS:
                queryWrapper.orderByAsc(ListProjects::getProjectProgress);
                break;
            case Budget:
                queryWrapper.orderByAsc(ListProjects::getTotalBudget);
                break;
            default:
                queryWrapper.orderByAsc(ListProjects::getProjectId);
        }
        if (status != null) {
            queryWrapper.eq(ListProjects::getProjectStatus, status);
        }
        queryWrapper.and(q -> q
                .or(w -> w.like(ListProjects::getProjectName, requirement))
                .or(w -> w.like(ListProjects::getProjectCategoryName, requirement))
                .or(w -> w.like(ListProjects::getProjectSubcategoryName, requirement)));


        Page<ListProjects> resultPage = page(page, queryWrapper);
        return Resp.success(resultPage);
    }

    @Override
    public List<Integer> getValidProjectIds(String token) {
        List<Integer> allProjectIds = listProjectsMapper.getAllProjectIds();
        List<Integer> validProjectIds = allProjectIds.stream().filter(projectId -> projectMemberService.validateViewPermissionsToProj(token, projectId))
                .collect(Collectors.toList());
        return validProjectIds;
    }

}
