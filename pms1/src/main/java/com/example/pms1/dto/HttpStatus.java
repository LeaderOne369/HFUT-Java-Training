package com.example.pms1.dto;



/**
 * @author loself
 * @date 2024-07-01 10:20
 */



public enum HttpStatus {


    ERROR(500, "未知异常"),
    BAD_REQUEST(400, "账号或密码错误"),
    FORBIDDEN(403, "请先登录"),
    ACCOUNT_EXPIRED(405, "账号或密码已过期"),
    ACCOUNT_LOCKED(405, "账号已锁定"),
    ACCOUNT_DISABLED(405, "账号已停用"),
    LOGIN_ERROR_TYPE(408,"错误的登录方式"),
    LOGIN_ERROR_PHONE(408,"手机号未注册"),
    LOGIN_WRONG_CAPTCHA(408,"验证码错误"),
    REGISTER_USEREXISTS(409,"用户名已注册"),
    REGISTER_PHONEXISTS(409,"手机号已注册"),
    ORIGIN_WRONG(410,"原密码输入错误"),
    ERROR_CONDITION(410,"错误类型"),
    ERROR_DATE(410,"日期不符合规定"),
    AVATAR_ERRORTYPE(412,"错误的头像类型，仅支持jpeg，png，jpg，gif格式"),
    AVATAR_SIZELIMITS(412,"大小超过限制--2MB"),
    MODIFY_ERROR(413,"修改失败"),
    ADD_ERROR(413,"添加失败"),
    DELETE_ERROR(413,"删除失败"),
    NO_PERMISSION(413,"无权限"),
    CANNOT_DELETE_SELF(413,"不能删除自己"),

    CANNOT_QUIT_PROJECT(413,"项目经理不能退出项目");


    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 状态码对应的含义
     */
    private final String msg;

    HttpStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
