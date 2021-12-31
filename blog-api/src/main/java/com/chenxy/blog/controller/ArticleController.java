package com.chenxy.blog.controller;

import com.chenxy.blog.common.aop.LogAnnotation;
import com.chenxy.blog.common.cache.Cache;
import com.chenxy.blog.service.ArticleService;
import com.chenxy.blog.vo.Result;
import com.chenxy.blog.vo.params.ArticleParam;
import com.chenxy.blog.vo.params.PageParams;
import org.apache.commons.collections.ResettableListIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//json数据进行交互
@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    /*首页文章列表*/
    @PostMapping
    //加上此注解，代表要对该接口记录日志
    @LogAnnotation(module = "文章",operation = "获取文章列表")
    public Result listArticle(@RequestBody PageParams pageParams){
        return articleService.listArticle(pageParams);
    }

    /*首页最热文章*/
    @PostMapping("hot")
    @Cache(expire = 5*60*1000,name = "hot_article")
    public Result hotArticle(){
        int limit=5;
        return articleService.hotArticle(limit);
    }

    /*首页最热文章*/
    @PostMapping("new")
    public Result newArticles(){
        int limit=5;
        return articleService.newArticles(limit);
    }

    /*文章归档*/
    @PostMapping("listArchives")
    public Result listArchives(){
        return articleService.listArchives();
    }

    @PostMapping("view/{id}")
    public Result findArticleById(@PathVariable("id") Long articleId){
        return articleService.findArticleById(articleId);
    }

    @PostMapping("publish")
    public Result publish(@RequestBody ArticleParam articleParam){
        return articleService.publish(articleParam);
    }
}
