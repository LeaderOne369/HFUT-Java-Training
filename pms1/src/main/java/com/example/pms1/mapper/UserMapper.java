package com.example.pms1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pms1.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author loself
 * @date 2024-07-01 9:57
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
