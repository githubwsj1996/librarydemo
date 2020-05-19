package com.sj.repository;

import com.sj.entity.Book;
import com.sj.entity.BookAdmin;

public interface BookAdminRepository {
    public BookAdmin login(String username,String password);
    public void save(Book book);
    public void deleteById(int id);
    public Book findById(int id);
    public void updateById(Book book);
}
