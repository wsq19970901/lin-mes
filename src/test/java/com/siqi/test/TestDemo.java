package com.siqi.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.siqi.dao.CategoryMapper;
import com.siqi.model.Category;

public class TestDemo {
	private static ApplicationContext bean=new ClassPathXmlApplicationContext("spring\\applicationContext.xml");
	private CategoryMapper categoryMapper;
	@Test
	public void CateTest() {
		categoryMapper=bean.getBean(CategoryMapper.class);
		Category category=categoryMapper.selectByPrimaryKey(1);
		System.out.println(category);
	}
}
