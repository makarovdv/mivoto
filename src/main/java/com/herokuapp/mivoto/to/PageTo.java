package com.herokuapp.mivoto.to;

import java.util.List;

public class PageTo<T extends BaseTo> {
    private List<T> content;

    private int page, pageSize, totalPages;

    public PageTo(List<T> content, int page, int pageSize, int totalPages) {
        this.content = content;
        this.page = page;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
    }

    public List<T> getContent() {
        return content;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    @Override
    public String toString() {
        return "PageTo{" +
                "content=" + content +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", totalPages=" + totalPages +
                '}';
    }
}
