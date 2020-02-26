package com.wu.blog.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDTO<T> {
    /**
     * 所有问题数
     */
    private List<T> data;
    /**
     * 是否显示前一页的标志
     */
    private boolean showPrevious;
    /**
     * 是否有第一页按钮
     */
    private boolean showFirstPage;
    /**
     * 是否显示后一页的标志
     */
    private boolean showNext;
    /**
     * 是否有最后一页按钮
     */
    private boolean showEndPages;
    /**
     * 当前页码
     */
    private Integer currentPage;
    /**
     * 当前页面附近的所有页码
     */
    private List<Integer> pages = new ArrayList<>();

    /**
     * 总页数
     */
    private Integer totalPages;
    public void setPagination(Integer totalPages, Integer page) {
        //计算总页数
         this.totalPages=totalPages;
        this.currentPage=page;
         pages.add(page);
        for (int i = 1; i <=3 ; i++) {
            if(page-i > 0){
                pages.add(0,page-i);
            }
            if(page+i <=totalPages){
                pages.add(page+i);
            }
        }
         //如果当前页为第一页则不显示前一页的标志，而展示向后页
        if(page == 1){
            showPrevious=false;
        }else {
            showPrevious=true;
        }
        //如果当前页为最后一页则不显示向后一页的标志，而展示向前页
        if(page == totalPages){
            showNext=false;
        }else {
            showNext=true;
        }
        //是否有跳转到第一页的标志
        if(pages.contains(1)){
            showFirstPage=false;
        }else {
            showFirstPage=true;
        }
        //是否有跳转到最后一页的标志
        if(pages.contains(totalPages)){
            showEndPages=false;
        }else {
            showEndPages=true;
        }
    }
}
