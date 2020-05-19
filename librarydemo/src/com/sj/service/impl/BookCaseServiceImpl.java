package com.sj.service.impl;

import com.sj.entity.BookCase;
import com.sj.repository.BookCaseRepository;
import com.sj.repository.impl.BookCaseRepositoryImpl;
import com.sj.service.BookCaseService;

import java.util.List;

public class BookCaseServiceImpl implements BookCaseService {

    private BookCaseRepository bookCaseRepository=new BookCaseRepositoryImpl();

    @Override
    public List<BookCase> findAllBookCase() {
        return bookCaseRepository.findAllBookCase();
    }
}
