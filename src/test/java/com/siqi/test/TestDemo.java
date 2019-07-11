package com.siqi.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.siqi.dao.MesOrderCustomerMapper;


public class TestDemo {
	private static ApplicationContext bean=new ClassPathXmlApplicationContext("spring\\applicationContext.xml");
	private MesOrderCustomerMapper mesOrderCustomerMapper;
	@Test
	public void CateTest() {
		mesOrderCustomerMapper=bean.getBean(MesOrderCustomerMapper.class);
		System.out.println(mesOrderCustomerMapper.getOrderCount());
	}
}
