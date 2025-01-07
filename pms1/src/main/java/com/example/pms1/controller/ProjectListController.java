package com.example.pms1.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.pms1.dto.ProjectCondition;
import com.example.pms1.dto.Resp;
import com.example.pms1.entity.*;
import com.example.pms1.mapper.*;
import com.example.pms1.service.ProjectInfoService;
import com.example.pms1.service.ProjectListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author loself
 * @date 2024-07-03 9:09
 */

/**
 * 用户用于操作可见的所有项目列表的接口
 */
@RestController
@RequestMapping("/projectlist")
public class ProjectListController {
    @Autowired
    private ProjectListService projectListService;

    @Autowired
    private ProjectInfoService projectInfoService;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private ProjectCategoryMapper projectCategoryMapper;

    @Autowired
    private ProjectStageMapper projectStageMapper;

    @Autowired
    private ProjectSubCategoryMapper projectSubCategoryMapper;

    @Autowired
    private RegionMapper regionMapper;

    /**
     * 用户获取所有可见项目
     * @param token 携带的用户信息
     * @param pageSize  分页大小
     * @param pageNum   分页序号
     * @param condition 排序类型
     * @return
     */
    @GetMapping("/getall")
    public Resp getProjectList(
                               @RequestHeader String token,
                               @RequestParam Integer pageSize,
                               @RequestParam Integer pageNum,
                               @RequestParam(required = false, defaultValue = "TIME") ProjectCondition condition

    ){
        return projectListService.getProjectList(token, pageSize, pageNum,condition);
    }

    /**
     * 获取单一项目信息的接口
     * @param token 携带的用户信息
     * @param projectId 要查询的项目id
     * @return
     */
    @GetMapping("/getbyid")
    public Resp getProjectById(@RequestHeader String token, @RequestParam Integer projectId){
        return projectInfoService.getProjectById(token,projectId);
    }

    /**
     * 用户在所有可见项目中进行模糊查询的接口
     * @param token 携带的用户信息
     * @param requirement   模糊查询的条件
     * @param pagesize  分页大小
     * @param pagenum   分页序号
     * @param condition     排序类型
     * @return
     */
    @GetMapping("/searchbyrequirement")
    public Resp getListByRequirement(
            @RequestHeader String token,@RequestParam String requirement,
                                     @RequestParam Integer pagesize,
                                     @RequestParam Integer pagenum,
                                     @RequestParam(required = false) String status,
                                     @RequestParam(required = false, defaultValue = "TIME") ProjectCondition condition
                                     ){
        return projectListService.getListByRequirement(requirement,status, token,pagesize,pagenum,condition);
    }

    /**
     * 项目经理用于结项的接口
     * @param token 携带的用户类型
     * @param projectId 要结项的项目
     * @return
     */
    @PutMapping("/complete")
    public Resp completeProject(@RequestHeader String token,@RequestParam Integer projectId){
        return projectInfoService.completeProject(token,projectId);
    }

    /**
     * 项目经理用于编辑项目信息的接口
     * @param token 携带的用户信息
     * @param projectInfo   更新后的项目信息
     * @param projectId     要更新的项目id
     * @return
     */
    @PutMapping("/edit")
    public Resp editProjectDetail(@RequestHeader String token,@RequestBody ProjectInfo projectInfo,@RequestParam Integer projectId){
        return projectInfoService.editProjectDetail(token,projectInfo,projectId);
    }


    /**
     * 项目经理用于删除项目的接口
     * @param projectId 要删除的项目id
     * @param token 携带的用户信息
     * @return
     */
    @DeleteMapping("/delete")
    public Resp deleteProject(@RequestParam Integer projectId,@RequestHeader String token){
        return projectInfoService.deleteProject(projectId,token);
    }


    /**
     * 用户用于新增项目的接口，新增后用户会成为该项目的项目经理
     * @param projectInfo   新增项目的信息
     * @param token 携带的用户信息
     * @return
     */
    @PostMapping("/addnewproject")
    public Resp addNewProject(@RequestBody ProjectInfo projectInfo,@RequestHeader String token){
        return  projectInfoService.addNewProject(projectInfo,token);
    }

    /**
     * 获取所有公司
     * @return
     */
    @GetMapping("/getallcompanies")
    public  Resp getAllCompanies(){
        List<Company> companies = companyMapper.selectList(new LambdaQueryWrapper<>());
        return Resp.success(companies);
    }

    /**
     * 获取所有部门
     * @return
     */
    @GetMapping("/getalldepartments")
    public  Resp getAllDepartments(){
        List<Department> departments = departmentMapper.selectList(new LambdaQueryWrapper<>());
        return Resp.success(departments);
    }

    /**
     * 获取所有项目大类
     * @return
     */
    @GetMapping("/getallcategories")
    public  Resp getAllCategories(){
        List<ProjectCategory> projectCategories = projectCategoryMapper.selectList(new LambdaQueryWrapper<>());
        return Resp.success(projectCategories);
    }

    /**
     * 获取所有项目阶段
     * @return
     */
    @GetMapping("/getallstages")
    public  Resp getAllStages(){
        List<ProjectStage> projectStages = projectStageMapper.selectList(new LambdaQueryWrapper<>());
        return Resp.success(projectStages);
    }

    /**
     * 获取所有项目小类
     * @return
     */
    @GetMapping("/getallsubcategories")
    public  Resp getAllSubCategories(@RequestParam String categoryId){
        if("null".equals(categoryId)){ return Resp.success(null);}
        else {
            List<ProjectSubCategory> projectSubCategories = projectSubCategoryMapper.selectList(new LambdaQueryWrapper<ProjectSubCategory>().eq(ProjectSubCategory::getCategoryId, Integer.parseInt(categoryId)));
            return Resp.success(projectSubCategories);
        }
    }

    /**
     * 获取所有区域
     * @return
     */
    @GetMapping("/getallregions")
    public  Resp getAllRegions(){
        List<Region> regions = regionMapper.selectList(new LambdaQueryWrapper<>());
        return Resp.success(regions);
    }

}
