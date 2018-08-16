package com.yangy.common.model;

/**
 * 分页对象
 *
 * @author yang yang
 * @email java_yangy@126.com
 * @create 2018/8/1
 * @since 1.0.0
 */
public class PageInfo<T> {

    private int pageSize;
    private int pageIndex;

    private int pages;
    private int count;
    private int offset;

    private boolean first;
    private boolean last;

    private T data;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPages() {
        if (0 == this.count || 0 == this.pageSize) {
            return 1;
        }
        return count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean getFirst() {
        return 1 == getPages();
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean getLast() {
        return getPages() == this.pageIndex;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}