package org.jsche.web.dao;

/**
 * Created by Intellij IDEA. Author myan
 * Date 2017/3/29.
 */
public class Pager {
    private int currentPage = 1;
    private int pageSize;
    private int totalRecords;
    private int totalPages;
    private boolean isFirst;
    private boolean hasNext;
    private boolean hasPrevious;
    private boolean isLast;

    public int getCurrentPage() {
        return currentPage;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public boolean hasNext() {
        return hasNext;
    }

    public boolean hasPrevious() {
        return hasPrevious;
    }

    public boolean isLast() {
        return isLast;
    }

    private int startPosition;

    public Pager(int currentPage, int totalRecords, int pageSize) {
        this.currentPage = currentPage;
        this.totalRecords = totalRecords;
        this.pageSize = pageSize;
        //total pages
        int totalPage = (totalRecords / pageSize);
        this.totalPages = totalRecords % pageSize == 0 ? totalPage :
                totalPage + 1;
        //basic analysis
        this.isFirst = currentPage == 1;
        this.isLast = currentPage == getTotalPages();
        this.hasNext = !isLast;
        this.hasPrevious = !isFirst;
    }

    public int getCurrentpage() {
        return currentPage;
    }

    public void setCurrentpage(int currentpage) {
        this.currentPage = currentpage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public int getStartPosition(){
        startPosition = (currentPage - 1)* pageSize;
        return startPosition;
    }

}
