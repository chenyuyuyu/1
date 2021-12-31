package com.chenxy.blog.service;

import com.chenxy.blog.vo.CategoryVo;
import com.chenxy.blog.vo.Result;

public interface CategoryService {
    CategoryVo findCategoryById(Long categoryId);

    Result findAll();

    Result findAllDetail();

    Result categoryDetailById(Long id);
}
