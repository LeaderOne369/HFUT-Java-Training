package com.example.pms1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pms1.dto.Resp;
import com.example.pms1.entity.ProjectMember;

import java.util.Map;

/**
 * @author loself
 * @date 2024-07-05 9:10
 */
public interface ProjectMemberService extends IService<ProjectMember> {
    Resp getMemberOfProj(String token, Integer projectId);

    Map<String,Boolean> getPermissionsOfMember(Integer memberId);

    Boolean validateViewPermissionsToProj(String token,Integer projectId);

    Boolean validateAddPermissionsToProj(String token,Integer projectId);

    Boolean validateDeletePermissionsToProj(String token,Integer projectId);

    Resp addNewMember(String token, Integer projectId, Integer userId);

    Resp authorizeMember(String token, Map<String, Boolean> permissions, Integer userId, Integer projectId);

    Resp deleteMember(String token, Integer memberId);

    Resp quitProject(String token, Integer projectId);

    Boolean memberExists(Integer projectId, Integer userId);

    Boolean isManager(String token, Integer projectId);
}
