package com.example.pms1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pms1.entity.ListProjects;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author loself
 * @date 2024-07-03 14:09
 */


@Mapper
public interface ListProjectsMapper extends BaseMapper<ListProjects> {
    @Select("SELECT project_id FROM project_list_view")
    List<Integer> getAllProjectIds();
}
