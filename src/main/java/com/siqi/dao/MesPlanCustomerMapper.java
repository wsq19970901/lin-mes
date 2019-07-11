package com.siqi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.siqi.beans.PageQuery;
import com.siqi.dto.SearchPlanDto;
import com.siqi.model.MesPlan;

public interface MesPlanCustomerMapper {

	int countBySearchDto(@Param("dto") SearchPlanDto dto);

	List<MesPlan> getPageListBySearchDto(@Param("dto") SearchPlanDto dto,@Param("page") PageQuery page);


}
