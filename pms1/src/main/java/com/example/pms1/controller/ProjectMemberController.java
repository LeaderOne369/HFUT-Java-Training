package com.example.pms1.controller;

import com.example.pms1.dto.Resp;
import com.example.pms1.entity.ProjectMember;
import com.example.pms1.service.ProjectMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author loself
 * @date 2024-07-05 9:16
 */

/**
 * 用于操作项目成员的相关接口
 */
@RestController
@RequestMapping("/projectmembers")
public class ProjectMemberController {
    @Autowired
    private ProjectMemberService projectMemberService;

    /**
     * 用户用于获取对应可见项目下的成员列表
     * @param token 携带的用户信息
     * @param projectId  对应的项目id
     * @return
     */
    @GetMapping("/getmembersofproj")
    public Resp getMemberOfProj(
            @RequestHeader String token,
            @RequestParam Integer projectId
    ){
        return projectMemberService.getMemberOfProj(token,projectId);
    }

    /**
     * 拥有添加成员权限的项目成员用户用于添加项目成员的接口
     * @param token 携带的用户信息
     * @param projectId 对应的项目id
     * @param userId    要添加的用户id
     * @return
     */
    @PostMapping("/addnewmember")
    public Resp addNewMember(
            @RequestHeader String token,
            @RequestParam Integer projectId,
            @RequestParam Integer userId
            ){
        return projectMemberService.addNewMember(token,projectId,userId);
    }

    /**
     * 项目经理用于给项目成员授权的接口
     * @param token 携带的用户信息
     * @param permissions   授权信息，add，view，delete 对应的有true和false
     * @param userId    授权的项目成员的用户id
     * @param projectId 对应的项目id
     * @return
     */
    @PutMapping("/authorizemember")
    public Resp authorizeMember(
            @RequestHeader String token,
            @RequestBody Map<String,Boolean> permissions,
            @RequestParam Integer userId,
            @RequestParam Integer projectId
            ){
        return projectMemberService.authorizeMember(token,permissions,userId,projectId);
    }

    /**
     * 拥有删除项目成员权限的用户用于删除项目成员的接口
     * @param token 携带的用户信息
     * @param memberId  项目成员id
     * @return
     */
    @DeleteMapping("/deletemember")
    public Resp deleteMember(
            @RequestHeader String token,
            @RequestParam Integer memberId
    ){
        return projectMemberService.deleteMember(token,memberId);
    }

    /**
     * 项目成员用于退出项目的接口，不允许项目经理退出，只能删除项目
     * @param token 携带的用户信息
     * @param projectId 对应的项目id
     * @return
     */
    @DeleteMapping("/quitproject")
    public Resp quitProject(@RequestHeader String token,
                            @RequestParam   Integer projectId
    ){
        return projectMemberService.quitProject(token,projectId);
    }


}
