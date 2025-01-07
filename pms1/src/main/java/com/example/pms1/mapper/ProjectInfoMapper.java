package com.example.pms1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pms1.dto.ProjectDetail;
import com.example.pms1.entity.ProjectInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author loself
 * @date 2024-07-03 9:11
 */

@Mapper
public interface ProjectInfoMapper extends BaseMapper<ProjectInfo> {

    ProjectDetail getDetail(Integer id);

}
