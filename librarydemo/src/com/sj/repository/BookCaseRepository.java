package com.sj.repository;

import com.sj.entity.BookCase;

import java.util.List;

public interface BookCaseRepository {
    public BookCase find(int id);
    public List<BookCase> findAllBookCase();
}
