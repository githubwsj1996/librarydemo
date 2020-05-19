package com.sj.repository;

import com.sj.entity.Book;

import java.util.List;

public interface BookRepository {
    public List<Book> findAll(int index,int limit);
    public  int count();
    public void borrow(int bookId, int readerId, String borrowTime, String returnTime, int state);
    public void updateAbled(int id,int abled);

    public List<Book> findAll();
}
