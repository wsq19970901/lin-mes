package com.siqi.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.siqi.dao.CategoryMapper;

public class TestDemo {
	private static ApplicationContext bean=new ClassPathXmlApplicationContext("spring\\applicationContext.xml");
	private CategoryMapper categoryMapper;
	@Test
	public void CateTest() {
		categoryMapper=bean.getBean(CategoryMapper.class);
		categoryMapper.deleteByPrimaryKey(5);
	}
}
