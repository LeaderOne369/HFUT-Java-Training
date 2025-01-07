package com.example.pms1;

import com.example.pms1.dto.Resp;
import com.example.pms1.entity.ProjectMember;
import com.example.pms1.service.ProjectMemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author loself
 * @date 2024-07-05 9:38
 */

@SpringBootTest
public class MemberTests {
    @Autowired
    private ProjectMemberService projectMemberService;

    @Test
    void  getmem(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJkNTgyNzJlYTRiZjQ0NjkxYjIyNTUyMzEzNDRmOTFkNSIsInN1YiI6InVzZXIyIiwiaXNzIjoieGwiLCJpYXQiOjE3MjAxNDM4MDUsImV4cCI6MTcyMDE0NzQwNX0.Rl00pCspTUO-Ihm2t-qZ--9177dXhF0q-sf-JQEjyUw";
        Integer projectId=1;
        Resp memberOfProj = projectMemberService.getMemberOfProj(token, projectId);
        List<ProjectMember> data =(List<ProjectMember>) memberOfProj.getData();
        data.forEach(System.out::println);
//        String string = projectMemberService.getPermissionsOfMember(1).toString();
//        System.out.println(string);
    }

    @Test
    void getmemexist(){
        Boolean aBoolean = projectMemberService.memberExists(1, 240713);
        System.out.println(aBoolean);
    }



}
