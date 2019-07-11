package com.siqi.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siqi.beans.PageQuery;
import com.siqi.beans.PageResult;
import com.siqi.common.JsonData;
import com.siqi.model.MesPlan;
import com.siqi.param.MesPlanVo;
import com.siqi.param.SearchPlanParam;
import com.siqi.service.PlanService;

@Controller
@RequestMapping("/plan")
public class PlanController {
	private static String FPATH="plan/";
	
	@Resource
	private PlanService planService;
	
	@RequestMapping("/plan.page")
	public String planPage() {
		return FPATH+"plan";
	}
	@RequestMapping("/planStarted.page")
	public String planStartedPage() {
		return FPATH+"planStarted";
	}
	@ResponseBody
	@RequestMapping("/plan.json")
	public JsonData searchPage(SearchPlanParam param,PageQuery page) {
		PageResult<MesPlan> pr=(PageResult<MesPlan>)planService.searchPageList(param,page);
		return JsonData.success(pr);
	}
	
	@ResponseBody
	@RequestMapping("/planBatchStart.json")
	public JsonData planBatchStart(String ids) {
		planService.batchStartWithIds(ids);
		return JsonData.success();
	}
	//更新操作
	@ResponseBody
	@RequestMapping("/update.json")
	public JsonData updatePlan(MesPlanVo mesPlanVo) {
		planService.update(mesPlanVo);
		return JsonData.success();
	}
}
