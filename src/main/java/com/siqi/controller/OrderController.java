package com.siqi.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siqi.beans.PageQuery;
import com.siqi.beans.PageResult;
import com.siqi.common.JsonData;
import com.siqi.model.MesOrder;
import com.siqi.param.MesOrderVo;
import com.siqi.param.SearchOrderParam;
import com.siqi.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	private static String FPATH="order/";
	
	
	
	@Resource
	private OrderService orderService;
	@RequestMapping("/orderBatch.page")
	public String orderBatchPage() {
		return FPATH+"orderBatch";
	}
	
	//批量启动
	@ResponseBody
	@RequestMapping("/orderBatchStart.json")
	public JsonData orderBatchStart(String ids) {
		orderService.batchStart(ids);
		return JsonData.success();
	}
	
	
	@RequestMapping("/order.page")
	public String orderPage() {
		return FPATH+"order";
	}
	//order添加
	@ResponseBody
	@RequestMapping("insert.json")
	public JsonData insertAjax(MesOrderVo mesOrderVo) {
		orderService.orderBatchInserts(mesOrderVo);
		return JsonData.success();
	}
	@ResponseBody
	@RequestMapping("/order.json")
	public JsonData searchPage(SearchOrderParam param,PageQuery page) {
		PageResult<MesOrder> pr=(PageResult<MesOrder>)orderService.serachPageList(param,page);
		return JsonData.success(pr);
		
	}
	@ResponseBody
	@RequestMapping("/update.json")
	public JsonData updateOrder(MesOrderVo mesOrderVo) {
		orderService.update(mesOrderVo);
		return JsonData.success();
	}
		
	
	
}
