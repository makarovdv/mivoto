package com.herokuapp.mivoto.to;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class PageTo<T extends BaseTo> implements Iterable<T> {
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
        // pagination starts from 1
        return page + 1;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public Stream<T> stream(){
        return content.stream();
    }

    @Override
    public void forEach(Consumer<? super T> consumer) {
        content.forEach(consumer);
    }

    @Override
    public Spliterator<T> spliterator() {
        return content.spliterator();
    }

    @Override
    public Iterator<T> iterator() {
        return content.iterator();
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
