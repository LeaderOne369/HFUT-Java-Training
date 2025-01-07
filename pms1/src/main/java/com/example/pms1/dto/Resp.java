package com.example.pms1.dto;

/**
 * @author loself
 * @date 2024-07-01 10:19
 */

import lombok.Data;

/**
 * 统一响应消息
 *
 * @param <T> 携带的数据类型
 * @author liuqian
 */
@Data
public class Resp<T> {
    /**
     * 响应码 如：200-成功，500-未知异常
     */
    private Integer code;

    /**
     * 异常描述信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 成功
     *
     * @param data 响应数据
     * @return 响应对象
     */
    public static <T> Resp<T> success(T data) {
        Resp<T> result = new Resp<>();
        result.setCode(200);
        result.setData(data);
        return result;
    }

    /**
     * 失败
     *
     * @param status HTTP 响应状态
     * @return 响应对象
     */
    public static <T> Resp<T> error(HttpStatus status) {
        Resp<T> result = new Resp<>();
        result.setCode(status.getCode());
        result.setMsg(status.getMsg());
        return result;
    }

    /**
     * 失败
     *
     * @param status HTTP 响应状态
     * @param msg 异常描述信息
     * @return 响应对象
     */
    public static <T> Resp<T> error(HttpStatus status, String msg) {
        Resp<T> result = new Resp<>();
        result.setCode(status.getCode());
        result.setMsg(msg);
        return result;
    }
}
