package org.jsche.web.dao;

/**
 * Created by Intellij IDEA. Author myan
 * Date 2017/3/29.
 */
public class Pager {
    private int currentPage = 1;
    private int pageSize = 10;
    private int totalRecords;
    private int totalPages;

    private int startPosition;

    public Pager(int currentPage, int totalRecords) {
        this.currentPage = currentPage;
        this.totalRecords = totalRecords;
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
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * total pages
     */
    public int totalPage() {
        if (0 == totalRecords) {
            return 0;
        }
        int totalPage = (totalRecords / pageSize);
        return totalRecords % pageSize == 0 ? totalPage :
                totalPage + 1;
    }

    public int getStartPosition(){
        startPosition = (currentPage - 1)* pageSize;
        return startPosition;
    }


}
