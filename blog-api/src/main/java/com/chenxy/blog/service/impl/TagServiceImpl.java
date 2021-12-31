package com.chenxy.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chenxy.blog.dao.mapper.TagMapper;
import com.chenxy.blog.dao.pojo.Tag;
import com.chenxy.blog.service.TagService;
import com.chenxy.blog.vo.Result;
import com.chenxy.blog.vo.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    /*ms_article_tag是文章和标签的关联表，ms_tag是单纯的标签的表*/
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<TagVo> findTagsByArticleId(Long articleId) {
        //mybatisplus无法进行多表查询
        List<Tag> tags=tagMapper.findTagsByArticleId(articleId);
        return copyList(tags);
    }

    private List<TagVo> copyList(List<Tag> tagList) {
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag tag : tagList) {
            tagVoList.add(copy(tag));
        }
        return tagVoList;

    }

    private TagVo copy(Tag tag) {
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag, tagVo);
        tagVo.setId(String.valueOf(tag.getId()));
        return tagVo;
    }

    @Override
    public Result hots(int limit) {
        /**
         * 最热标签就是对标签ms_article_tag中的tag_id进行排序数量最多的就是我们的最热标签
         * 1、标签所拥有的文章数量最多就是最热标签
         * 2、查询 根据tag_id分组计数，从大到小排列取前limit个
         */
        List<Long> tagIds=tagMapper.findHotsTagIds(limit);
        //因为id in（1,2,3） 里面不能为空所以我们需要进行判断
        //  CollectionUtils.isEmpty作用 https://blog.csdn.net/qq_42848910/article/details/105717235
        if(CollectionUtils.isEmpty(tagIds)){
            return Result.success(Collections.emptyList());
        }
        //需求的是tagId 和tagName Tag对象
        //我们的sql语句类似于select * from tag where id in (1,2,3)
        List<Tag> tagList=tagMapper.findTagsByTagIds(tagIds);

        return Result.success(tagList);
    }

    @Override
    public Result findAll() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId,Tag::getTagName);
        List<Tag> tagList = tagMapper.selectList(queryWrapper);
        return Result.success(copyList(tagList));
    }

    @Override
    public Result findAllDetail() {
        List<Tag> tagList = tagMapper.selectList(new LambdaQueryWrapper<>());
        return Result.success(copyList(tagList));
    }

    @Override
    public Result findDetailById(Long id) {
        Tag tag = tagMapper.selectById(id);
        return Result.success(copy(tag));
    }
}
