package com.example.pms1.dto;

import com.example.pms1.entity.ProjectMember;
import com.example.pms1.entity.User;
import lombok.Data;

/**
 * @author loself
 * @date 2024-07-07 17:49
 */

@Data
public class MemberDetail {
    private ProjectMember projectMember;
    private User user;

    public ProjectMember getProjectMember() {
        return projectMember;
    }

    public void setProjectMember(ProjectMember projectMember) {
        this.projectMember = projectMember;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MemberDetail{" +
                "projectMember=" + projectMember +
                ", user=" + user +
                '}';
    }
}
