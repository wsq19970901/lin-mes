package com.siqi.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.siqi.util.MyStringUtils;
import com.google.common.base.Preconditions;
import com.siqi.beans.PageQuery;
import com.siqi.beans.PageResult;
import com.siqi.dao.MesOrderMapper;
import com.siqi.dao.MesPlanCustomerMapper;
import com.siqi.dao.MesPlanMapper;
import com.siqi.dto.SearchPlanDto;
import com.siqi.exception.ParamException;
import com.siqi.model.MesOrder;
import com.siqi.model.MesPlan;
import com.siqi.param.MesPlanVo;
import com.siqi.param.SearchPlanParam;
import com.siqi.util.BeanValidator;

@Service
public class PlanService {
	
	@Resource
	private MesOrderMapper mesOrderMapper;
	@Resource
	private SqlSession sqlSession;
	@Resource
	private MesPlanMapper mesPlanMapper;
	@Resource
	private MesPlanCustomerMapper mesPlanCustomerMapper;
	//计划分页
	public PageResult<MesPlan> searchPageList(SearchPlanParam param, PageQuery page) {
		//验证页码是否为空	
		BeanValidator.check(page);
		
		SearchPlanDto dto=new SearchPlanDto();
		if(StringUtils.isNoneBlank(param.getKeyword())) {
			dto.setKeyword("%"+param.getKeyword()+"%");
		}
		if(StringUtils.isNotBlank(param.getSearch_msource())) {
			dto.setSearch_msource(param.getSearch_msource());
		}
		if(StringUtils.isNotBlank(param.getSearch_status())) {
			dto.setSearch_status(Integer.parseInt(param.getSearch_status()));
		}
		try {
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			if(StringUtils.isNoneBlank(param.getFromTime())) {
				dto.setFromTime(dateFormat.parse(param.getFromTime()));
			}
			if(StringUtils.isNoneBlank(param.getToTime())) {
				dto.setToTime(dateFormat.parse(param.getToTime()));
			}
		} catch (Exception e) {
			throw new ParamException("传入的日期格式有问题，正确格式为:yyyy-MM-dd");
		}
		int count=mesPlanCustomerMapper.countBySearchDto(dto);
		if(count>0) {
			List<MesPlan> planList=mesPlanCustomerMapper.getPageListBySearchDto(dto,page);
			return PageResult.<MesPlan>builder().total(count).data(planList).build();
		}
		return PageResult.<MesPlan>builder().build();
	}
	public void batchStartWithIds(String ids) {
		if(ids!=null&&ids.length()>0) {
			//批量处理
			MesPlanMapper mapper=sqlSession.getMapper(MesPlanMapper.class);
			//判断Id是否为空，执行自定义update语句
			String[] strs=ids.split(",");
			String[] idsTemp=strs[0].split("&");
			String[] datesTemp=strs[1].split("&");
			String startTime=datesTemp[0];
			String endTime=datesTemp[1];
			for(int i=0;i<idsTemp.length;i++) {
				MesPlan mesPlan=new MesPlan();
				mesPlan.setId(Integer.parseInt(idsTemp[i]));
				mesPlan.setPlanWorkstarttime(MyStringUtils.string2Date(startTime,null));
				mesPlan.setPlanWorkendtime(MyStringUtils.string2Date(endTime,null));
				mesPlan.setPlanStatus(1);
				mesPlan.setPlanCurrentremark("计划已启动");
				mapper.updateByPrimaryKeySelective(mesPlan);
			//半成品材料生成
			//TODO
			}
		}
		
		
		
	}
	//创建为启动计划
	public void prePlan(MesOrder mesOrder) {
		MesPlanMapper planMapper = sqlSession.getMapper(MesPlanMapper.class);
		MesPlan mesPlan =MesPlan.builder().planOrderid(mesOrder.getOrderId()).planProductname(mesOrder.getOrderProductname())//
				.planClientname(mesOrder.getOrderClientname()).planContractid(mesOrder.getOrderContractid()).planImgid(mesOrder.getOrderImgid())//
				.planMaterialname(mesOrder.getOrderMaterialname()).planCurrentstatus("计划").planCurrentremark("计划待执行").planSalestatus(mesOrder.getOrderSalestatus())//
				.planMaterialsource(mesOrder.getOrderMaterialsource()).planHurrystatus(mesOrder.getOrderHurrystatus()).planStatus(0).planCometime(mesOrder.getOrderCometime())//
				.planCommittime(mesOrder.getOrderCommittime()).planInventorystatus(mesOrder.getOrderInventorystatus()).build();
		mesPlan.setPlanOperator("user01");
		mesPlan.setPlanOperateIp("127.0.0.1");
		mesPlan.setPlanOperateTime(new Date());
		planMapper.insertSelective(mesPlan);
	}
	//批量启动order后的批量plan启动
	public void startPlansByOrderIDs(String[] ids) {
		for(String temId:ids) {
			Integer id=Integer.parseInt(temId);
			MesOrder order=mesOrderMapper.selectByPrimaryKey(id);
			//查询内容非空，使用google的guava工具类
			Preconditions.checkNotNull(order);
			prePlan(order);
		}
	}
	public void update(MesPlanVo mesPlanVo) {
		BeanValidator.check(mesPlanVo);
		MesPlan mesPlan=new MesPlan();
		BeanUtils.copyProperties(mesPlanVo, mesPlan);
		mesPlan.setPlanCometime(MyStringUtils.string2Date(mesPlanVo.getPlanCometime(),null));
		mesPlan.setPlanCommittime(MyStringUtils.string2Date(mesPlanVo.getPlanCommittime(),null));
		mesPlan.setPlanWorkstarttime(MyStringUtils.string2Date(mesPlanVo.getPlanWorkstarttime(),null));
		mesPlan.setPlanWorkendtime(MyStringUtils.string2Date(mesPlanVo.getPlanWorkendtime(),null));
		mesPlan.setPlanOperator("user01");
		mesPlan.setPlanOperateIp("127.0.0.1");
		mesPlan.setPlanOperateTime(new Date());
		mesPlanMapper.updateByPrimaryKeySelective(mesPlan);
	}
		
}
