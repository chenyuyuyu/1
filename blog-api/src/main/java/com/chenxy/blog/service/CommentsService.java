package com.chenxy.blog.service;

import com.chenxy.blog.vo.Result;
import com.chenxy.blog.vo.params.CommentParam;

public interface CommentsService {
    /**
     * 根据文章id查询所有的评论列表
     * @param articleId
     * @return
     */
    Result commentsByArticleId(Long articleId);

    Result comment(CommentParam commentParam);
}
