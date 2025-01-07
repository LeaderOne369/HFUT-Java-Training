package com.example.pms1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pms1.dto.HttpStatus;
import com.example.pms1.dto.ProjectDetail;
import com.example.pms1.dto.Resp;
import com.example.pms1.dto.Role;
import com.example.pms1.entity.ProjectInfo;
import com.example.pms1.entity.ProjectMember;
import com.example.pms1.entity.User;
import com.example.pms1.mapper.ProjectInfoMapper;
import com.example.pms1.service.ProjectInfoService;
import com.example.pms1.service.ProjectMemberService;
import com.example.pms1.service.UserService;
import com.example.pms1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author loself
 * @date 2024-07-03 15:34
 */
@Service
public class ProjectInfoServiceImpl extends ServiceImpl<ProjectInfoMapper, ProjectInfo> implements ProjectInfoService {

    @Autowired
    private ProjectMemberService projectMemberService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectInfoMapper projectInfoMapper;

    @Override
    public Resp completeProject(String token, Integer projectId) {
        Boolean validated = projectMemberService.isManager(token, projectId);
        if (validated) {
            LambdaQueryWrapper<ProjectInfo> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ProjectInfo::getId, projectId);
            ProjectInfo projectInfo = new ProjectInfo();
            projectInfo.setStatus("已结项");
            boolean res = update(projectInfo, queryWrapper);
            if (!res) {
                return Resp.error(HttpStatus.ERROR);
            }
            return Resp.success("项目状态更新成功");
        }
        return Resp.error(HttpStatus.NO_PERMISSION);
    }

    @Override
    public Resp editProjectDetail(String token, ProjectInfo projectInfo, Integer projectId) {
        Boolean validated = projectMemberService.isManager(token, projectId);
        if (validated) {
            boolean res = updateById(projectInfo);
            return res ? Resp.success("项目信息更新成功") : Resp.error(HttpStatus.MODIFY_ERROR);
        }
        return Resp.error(HttpStatus.NO_PERMISSION);

    }

    //TODO deleteProject
    @Override
    public Resp deleteProject(Integer projectId, String token) {
        Boolean validated = projectMemberService.isManager(token, projectId);
        if (validated) {
            boolean res = removeById(projectId);
            return res ? Resp.success("项目删除成功") : Resp.error(HttpStatus.DELETE_ERROR);
        }
        return Resp.error(HttpStatus.NO_PERMISSION);
    }

    //TODO addNewProject
    @Override
    public Resp addNewProject(ProjectInfo projectInfo, String token) {
        try {
            String username = JwtUtil.parseJWT(token).getSubject();
            User user = userService.selectUserByUsername(username);
            projectInfo.setStatus("在途");
            projectInfo.setCreatedAt(new Date());
            boolean saved = projectInfoMapper.insert(projectInfo) > 0;
            if (!saved) {
                return Resp.error(HttpStatus.ERROR);
            }
            Integer projectId = projectInfo.getId();
            if (projectId == null) {
                return Resp.error(HttpStatus.ERROR);
            }
            Map<String, Boolean> map = new HashMap<>();
            map.put("add", true);
            map.put("view", true);
            map.put("delete", true);
            ProjectMember projectMember = new ProjectMember();
            projectMember.setProjectId(projectId);
            projectMember.setUserId(user.getUserId());
            projectMember.setRole(Role.MANAGER);
            projectMember.setJoinedAt(new Timestamp(new Date().getTime()));
            projectMember.setPermissions(map);
            boolean saved1 = projectMemberService.save(projectMember);
            return saved1 ? Resp.success("新增项目成功") : Resp.error(HttpStatus.ADD_ERROR);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Resp.error(HttpStatus.ERROR);
        }
    }

    @Override
    public Resp getProjectById(String token, Integer projectId) {
        Boolean validated = projectMemberService.validateViewPermissionsToProj(token, projectId);
        if (validated) {
            ProjectDetail projectInfo = projectInfoMapper.getDetail(projectId);
            if (Objects.isNull(projectInfo)) {
                return Resp.error(HttpStatus.ERROR);
            }
            return Resp.success(projectInfo);
        }
        return Resp.error(HttpStatus.NO_PERMISSION);
    }
}
