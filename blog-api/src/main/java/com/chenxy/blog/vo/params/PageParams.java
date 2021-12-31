package com.chenxy.blog.vo.params;

import lombok.Data;

//VO（View Object）：视图层，用于展示层，它的作用是把某个指定页面（或组件）的所有数据封装起来
//业务层操作对象
@Data
public class PageParams {
    private int page=1;
    private int pageSize=10;
    private Long categoryId;
    private Long tagId;
    private String year;
    private String month;

    //传递6的话变成06
    public String getMonth(){
        if(this.month!=null && this.month.length()==1){
            return "0"+this.month;
        }
        return this.month;
    }

}
