package com.chenxy.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenxy.blog.dao.pojo.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper extends BaseMapper<Tag> {

    /*根据文章id查询标签列表*/
    List<Tag> findTagsByArticleId(Long articleId);

    /*查询前n条最热的标签*/
    List<Long> findHotsTagIds(int limit);

    List<Tag> findTagsByTagIds(List<Long> tagIds);

}
