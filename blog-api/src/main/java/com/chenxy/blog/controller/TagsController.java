package com.chenxy.blog.controller;

import com.chenxy.blog.service.TagService;
import com.chenxy.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tags")
public class TagsController {
    @Autowired
    private TagService tagService;

    //  /tags/hot
    @GetMapping("hot")
    public Result hot(){
        int limit=6;
        return tagService.hots(limit);
    }

    @GetMapping
    private Result findAll(){
        return tagService.findAll();
    }

    @GetMapping("detail")
    private Result findAllDetail(){
        return tagService.findAllDetail();
    }

    @GetMapping("detail/{id}")
    private Result findDetailById(@PathVariable("id")Long id){
        /**
         * 查询文章标签下的所有文章
         */
        return tagService.findDetailById(id);
    }
}
