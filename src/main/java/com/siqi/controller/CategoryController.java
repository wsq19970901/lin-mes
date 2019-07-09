package com.siqi.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.siqi.service.CategoryService;

@Controller
@RequestMapping("")
public class CategoryController {
	private static String FPATH = "category/";
	@Resource
	private CategoryService categoryService;
	
	@RequestMapping("/listCategory")
	public String listCategory() {
		return FPATH+"listCategory";
	}
}
