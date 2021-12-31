package com.chenxy.blog.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.chenxy.blog.dao.mapper.ArticleMapper;
import com.chenxy.blog.dao.pojo.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ThreadService {
    //期望此操作在线程池中执行不会影响原有主线程
    //了解线程池——》JUC并发编程
    @Async("taskExecutor")
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article) {
        Integer viewCounts = article.getViewCounts();
        Article articleUpdate=new Article();
        articleUpdate.setViewCounts(viewCounts+1);
        LambdaUpdateWrapper<Article> updateWrapper=new LambdaUpdateWrapper<>();
        //根据id更新
        updateWrapper.eq(Article::getId,article.getId());
        //为了保证在多线程环境下的线程安全
        //改之前再确认这个值有没有被其他线程抢先修改，类似于CAS操作 cas加自旋，加个循环就是cas
        updateWrapper.eq(Article::getViewCounts,viewCounts);
        //update article set view_count=100 where view_count=99 and id=111
        //实体类加更新条件
        articleMapper.update(articleUpdate,updateWrapper);
//        try {
//            Thread.sleep(5000);
//            System.out.println("更新完成了");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

}
