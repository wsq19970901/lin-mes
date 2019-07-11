package com.siqi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.siqi.beans.PageQuery;
import com.siqi.dto.SearchOrderDto;
import com.siqi.model.MesOrder;

public interface MesOrderCustomerMapper {

	Long getOrderCount();

	int countBySearchDto(@Param("dto") SearchOrderDto dto);

	List<MesOrder> getPageListBySearchDto(@Param("dto") SearchOrderDto dto,@Param("page") PageQuery page);

	void batchStart(@Param("list") String[] idArray);

}
