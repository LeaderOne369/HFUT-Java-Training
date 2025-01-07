package com.example.pms1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pms1.dto.PlanDetail;
import com.example.pms1.entity.Plan;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author loself
 * @date 2024-07-03 17:23
 */

@Mapper
public interface PlanMapper extends BaseMapper<Plan> {

    PlanDetail getPlanDetail(Integer planId);

}
