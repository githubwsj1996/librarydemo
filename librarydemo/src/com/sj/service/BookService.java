package com.sj.service;

import com.sj.entity.Book;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public interface BookService {
    public List<Book> findAll(int page,int limit);
    public int  count();
    public void borrow(int bookId,int readerId,String borrowTime,String returnTime,int state);
    public void save(Book book);
    public void deleteById(int id);
    public Book findById(int id);
    public void updateById(Book book);
    public void updateAbled(int bookId,int abled);

    public HSSFWorkbook getHSSFWorkbook();
}
