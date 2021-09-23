package com.xyxy.util;

import java.util.List;

/*分页工具类*/
public class PageUtil<T> {

    private Integer pageSize;//每页显示条数
    private Integer currentPage;//当前页码值
    private Integer totalCount;//数据库表总条数
    private Integer totalPage;//总页数
    private Integer startIndex;//分页查询起始索引
    private Integer lastPage;//上一页
    private Integer nextPage;//下一页
    private List<T> list; //分页查询的数据集合

    /*创建一个有参的初始化类的构造方法*/
    //,String pageSize写死了就等于三
    public PageUtil(Integer totalCount, String currentPage,Integer pageSize){

        this.pageSize=pageSize;//自定义每页显示条数
        this.totalCount=totalCount;//初始化总条数
        //初始化当前页码值
        initCurrentPage(currentPage);
        //初始化总页数
        initTotalPage();
        //初始化起始索引
        initStartIndex();
        //上一页
        initLastPage();
        //下一页
        initNextPage();
    }
    //下一页
    private void initNextPage() {
        if(this.currentPage==this.totalPage){//如果已经在尾页，点击下一页显示当前最后一页即可
            this.nextPage=this.totalPage;
        }else{
           this.nextPage=this.currentPage+1;
        }
    }

    //上一页
    private void initLastPage() {
        if(this.currentPage==1){//当前在首页，点击上一页继续停留当前页
           this.lastPage=1;
        }else{
            this.lastPage=this.currentPage-1;
        }
    }

    //查询的起始索引
    private void initStartIndex() {
        this.startIndex=(this.currentPage-1)*this.pageSize;
    }

    //初始化总页码
    private void initTotalPage() {
        if(this.totalCount%this.pageSize==0){
            this.totalPage=this.totalCount/this.pageSize;
        }else{
            this.totalPage=this.totalCount/this.pageSize+1;
        }
    }

    //初始化当前页码
    private void initCurrentPage(String currentPage) {
        if(currentPage==null){//首次进入，访问第一页
            this.currentPage=1;
        }else{
            this.currentPage=Integer.valueOf(currentPage);
        }
    }


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
