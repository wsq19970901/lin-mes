package com.siqi.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.siqi.dao.CategoryMapper;
import com.siqi.model.Category;

@Service
public class CategoryService {
	@Resource
	private CategoryMapper categoryMapper;

	public void deleteByPrimaryKey(int i) {
		categoryMapper.deleteByPrimaryKey(i);
	}

}
