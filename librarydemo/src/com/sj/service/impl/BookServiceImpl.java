package com.sj.service.impl;

import com.sj.entity.Book;
import com.sj.entity.BookCase;
import com.sj.entity.Borrow;
import com.sj.repository.BookAdminRepository;
import com.sj.repository.BookCaseRepository;
import com.sj.repository.BookRepository;
import com.sj.repository.impl.BookAdminRepositoryImpl;
import com.sj.repository.impl.BookCaseRepositoryImpl;
import com.sj.repository.impl.BookRepositoryImpl;
import com.sj.service.BookService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookRepository bookRepository=new BookRepositoryImpl();
    private BookCaseRepository bookCaseRepository=new BookCaseRepositoryImpl();
    private BookAdminRepository bookAdminRepository=new BookAdminRepositoryImpl();

    @Override
    public List<Book> findAll(int page, int limit) {
        int index=(page-1)*limit;
        List<Book> list=bookRepository.findAll(index,limit);
        for(Book book:list)
        {
            int bookcaseId=book.getBookCaseId();
            BookCase bookCase=bookCaseRepository.find(bookcaseId);
            book.setBookCaseName(bookCase.getName());
        }
        return list;
    }

    @Override
    public int count() {
        return bookRepository.count();
    }

    @Override
    public void borrow(int bookId, int readerId, String borrowTime, String returnTime, int state) {
        bookRepository.borrow(bookId,readerId,borrowTime,returnTime,state);
        bookRepository.updateAbled(bookId,0);
    }

    @Override
    public void save(Book book) {
        bookAdminRepository.save(book);
    }

    @Override
    public void deleteById(int id) {
        bookAdminRepository.deleteById(id);
    }

    @Override
    public Book findById(int id) {
        return bookAdminRepository.findById(id);
    }

    @Override
    public void updateById(Book book) {
            bookAdminRepository.updateById(book);
    }

    @Override
    public void updateAbled(int bookId, int abled) {
        bookRepository.updateAbled(bookId,abled);
    }

    @Override
    public HSSFWorkbook getHSSFWorkbook() {
        HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
        Sheet sheet=hssfWorkbook.createSheet("图书");
        List<Book> list=bookRepository.findAll();
        Row row=sheet.createRow(0);
        Cell cell=row.createCell(0);
        cell.setCellValue("图书名称");
        cell=row.createCell(1);
        cell.setCellValue("作者");
        cell=row.createCell(2);
        cell.setCellValue("出版社");
        cell=row.createCell(3);
        cell.setCellValue("总页数");
        cell=row.createCell(4);
        cell.setCellValue("价格");
        cell=row.createCell(5);
        cell.setCellValue("图书分类");
        for(int i=0;i<list.size();i++)
        {
            Book book=list.get(i);
            row=sheet.createRow(i+1);
            cell=row.createCell(0);
            cell.setCellValue(book.getName());
            cell=row.createCell(1);
            cell.setCellValue(book.getAuthor());
            cell=row.createCell(2);
            cell.setCellValue(book.getPublish());
            cell=row.createCell(3);
            cell.setCellValue(book.getPages());
            cell=row.createCell(4);
            cell.setCellValue(book.getPrice());
            cell=row.createCell(5);
            cell.setCellValue(book.getBookCaseName());
        }
        return hssfWorkbook;
    }


}
