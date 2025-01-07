package com.example.pms1.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {

    private String oldPassword;

    private String newPassword;

}
