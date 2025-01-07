package com.example.pms1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pms1.dto.HttpStatus;
import com.example.pms1.dto.MemberDetail;
import com.example.pms1.dto.Resp;
import com.example.pms1.dto.Role;
import com.example.pms1.entity.ProjectMember;
import com.example.pms1.entity.User;
import com.example.pms1.mapper.ProjectMemberMapper;
import com.example.pms1.service.ProjectMemberService;
import com.example.pms1.service.UserService;
import com.example.pms1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * @author loself
 * @date 2024-07-05 9:14
 */

@Service
public class ProjectMemberServiceImpl extends ServiceImpl<ProjectMemberMapper, ProjectMember> implements ProjectMemberService {

    @Autowired
    private UserService userService;

    @Override
    public Resp getMemberOfProj(String token, Integer projectId) {
        try {
            String username = JwtUtil.parseJWT(token).getSubject();
            Integer userId = userService.selectUserByUsername(username).getUserId();
            LambdaQueryWrapper<ProjectMember> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ProjectMember::getUserId, userId);
            queryWrapper.eq(ProjectMember::getProjectId, projectId);
            ProjectMember one = getOne(queryWrapper);
            if (Objects.isNull(one)) {
                return Resp.error(HttpStatus.NO_PERMISSION);
            }
            Map<String, Boolean> permissionsOfMember = getPermissionsOfMember(one.getMemberId());
            Boolean view = permissionsOfMember.get("view");
            if (view) {
                LambdaQueryWrapper<ProjectMember> qw = new LambdaQueryWrapper<>();
                qw.eq(ProjectMember::getProjectId, projectId);
                List<ProjectMember> list = list(qw);
                List<MemberDetail> memberDetails = new ArrayList<>();
                for (ProjectMember pm : list) {
                    MemberDetail memberDetail = new MemberDetail();
                    memberDetail.setProjectMember(pm);
                    memberDetail.setUser(userService.getById(pm.getUserId()));
                    memberDetails.add(memberDetail);
                }
                return Resp.success(memberDetails);
            } else {
                return Resp.error(HttpStatus.NO_PERMISSION);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Boolean> getPermissionsOfMember(Integer memberId) {
        ProjectMember member = getById(memberId);
        if (Objects.isNull(member)) {
            throw new RuntimeException("error memberId");
        }
        return member.getPermissions();
    }

    @Override
    public Boolean validateViewPermissionsToProj(String token, Integer projectId) {
        try {
            String username = JwtUtil.parseJWT(token).getSubject();
            User user = userService.selectUserByUsername(username);
            LambdaQueryWrapper<ProjectMember> projectMemberLambdaQueryWrapper = new LambdaQueryWrapper<>();
            projectMemberLambdaQueryWrapper.eq(ProjectMember::getUserId, user.getUserId());
            projectMemberLambdaQueryWrapper.eq(ProjectMember::getProjectId, projectId);
            ProjectMember one = getOne(projectMemberLambdaQueryWrapper);
            if (Objects.isNull(one)) {
                return false;
            }
            return one.getPermissions().get("view");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean validateAddPermissionsToProj(String token, Integer projectId) {
        try {
            String username = JwtUtil.parseJWT(token).getSubject();
            User user = userService.selectUserByUsername(username);
            LambdaQueryWrapper<ProjectMember> projectMemberLambdaQueryWrapper = new LambdaQueryWrapper<>();
            projectMemberLambdaQueryWrapper.eq(ProjectMember::getUserId, user.getUserId());
            projectMemberLambdaQueryWrapper.eq(ProjectMember::getProjectId, projectId);
            ProjectMember one = getOne(projectMemberLambdaQueryWrapper);
            if (Objects.isNull(one)) {
                return false;
            }
            return one.getPermissions().get("add");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean validateDeletePermissionsToProj(String token, Integer projectId) {
        try {
            String username = JwtUtil.parseJWT(token).getSubject();
            User user = userService.selectUserByUsername(username);
            LambdaQueryWrapper<ProjectMember> projectMemberLambdaQueryWrapper = new LambdaQueryWrapper<>();
            projectMemberLambdaQueryWrapper.eq(ProjectMember::getUserId, user.getUserId());
            projectMemberLambdaQueryWrapper.eq(ProjectMember::getProjectId, projectId);
            ProjectMember one = getOne(projectMemberLambdaQueryWrapper);
            if (Objects.isNull(one)) {
                return false;
            }
            return one.getPermissions().get("delete");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resp addNewMember(String token, Integer projectId, Integer userId) {
        Boolean result = validateAddPermissionsToProj(token, projectId);
        Boolean exists = memberExists(projectId, userId);
        if (result && !exists) {
            ProjectMember projectMember = new ProjectMember();
            Map<String, Boolean> map = new HashMap<>();
            map.put("add", false);
            map.put("view", true);
            map.put("delete", false);
            projectMember.setProjectId(projectId);
            projectMember.setUserId(userId);
            projectMember.setRole(Role.EMPLOYEE);
            projectMember.setJoinedAt(new Timestamp(new Date().getTime()));
            projectMember.setPermissions(map);
            boolean saved = save(projectMember);
            if (saved) {
                return Resp.success("新增成功");
            }
            return Resp.error(HttpStatus.ADD_ERROR);
        } else {
            return Resp.error(HttpStatus.NO_PERMISSION);
        }
    }

    @Override
    public Resp authorizeMember(String token, Map<String, Boolean> permissions, Integer userId, Integer projectId) {
        try {
            String username = JwtUtil.parseJWT(token).getSubject();
            Integer authorizerId = userService.selectUserByUsername(username).getUserId();
            LambdaQueryWrapper<ProjectMember> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ProjectMember::getUserId, authorizerId);
            queryWrapper.eq(ProjectMember::getProjectId, projectId);
            ProjectMember one = getOne(queryWrapper);
            if (Objects.isNull(one)) {
                return Resp.error(HttpStatus.ERROR);
            }
            boolean isManager = one.getRole().equals(Role.MANAGER);
            if (isManager) {
                LambdaQueryWrapper<ProjectMember> qw = new LambdaQueryWrapper<>();
                qw.eq(ProjectMember::getUserId, userId);
                qw.eq(ProjectMember::getProjectId, projectId);
                ProjectMember member = getOne(qw);
                if (Objects.isNull(member)) {
                    return Resp.error(HttpStatus.ERROR);
                }
                member.setPermissions(permissions);
                boolean res = updateById(member);
                if (res) {
                    return Resp.success("修改成员权限成功");
                }
                return Resp.error(HttpStatus.MODIFY_ERROR);
            } else {
                return Resp.error(HttpStatus.NO_PERMISSION);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resp deleteMember(String token, Integer memberId) {
        try {
            String username = JwtUtil.parseJWT(token).getSubject();
            ProjectMember member = getById(memberId);
            if (username.equals(userService.getById(member.getUserId()).getUsername())) {
                return Resp.error(HttpStatus.CANNOT_DELETE_SELF);
            }
            Boolean validated = validateDeletePermissionsToProj(token, member.getProjectId());
            if (validated) {
                boolean res = removeById(memberId);
                return res ? Resp.success("成员删除成功") : Resp.error(HttpStatus.DELETE_ERROR);
            } else {
                return Resp.error(HttpStatus.NO_PERMISSION);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resp quitProject(String token, Integer projectId) {
        try {
            String username = JwtUtil.parseJWT(token).getSubject();
            Integer userId = userService.selectUserByUsername(username).getUserId();
            LambdaQueryWrapper<ProjectMember> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ProjectMember::getUserId, userId);
            queryWrapper.eq(ProjectMember::getProjectId, projectId);
            ProjectMember one = getOne(queryWrapper);
            if (Objects.isNull(one)) {
                return Resp.error(HttpStatus.ERROR);
            }
            if (one.getRole() == Role.MANAGER) {
                return Resp.error(HttpStatus.CANNOT_QUIT_PROJECT);
            }
            boolean res = removeById(one);
            if (res) {
                return Resp.success("退出项目成功");
            }
            return Resp.error(HttpStatus.ERROR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Boolean memberExists(Integer projectId, Integer userId) {
        LambdaQueryWrapper<ProjectMember> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProjectMember::getProjectId, projectId);
        queryWrapper.eq(ProjectMember::getUserId, userId);
        ProjectMember one = getOne(queryWrapper);
        if (Objects.isNull(one)) {
            return false;
        }
        return true;
    }


    @Override
    public Boolean isManager(String token, Integer projectId) {
        try {
            String username = JwtUtil.parseJWT(token).getSubject();
            User user = userService.selectUserByUsername(username);
            LambdaQueryWrapper<ProjectMember> projectMemberLambdaQueryWrapper = new LambdaQueryWrapper<>();
            projectMemberLambdaQueryWrapper.eq(ProjectMember::getUserId, user.getUserId());
            projectMemberLambdaQueryWrapper.eq(ProjectMember::getProjectId, projectId);
            ProjectMember one = getOne(projectMemberLambdaQueryWrapper);
            if (Objects.isNull(one)) {
                return false;
            }
            return one.getRole().equals(Role.MANAGER);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
