package com.sj.repository.impl;

import com.sj.entity.Book;
import com.sj.entity.BookAdmin;
import com.sj.repository.BookAdminRepository;
import com.sj.util.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class BookAdminRepositoryImpl implements BookAdminRepository {

    private QueryRunner queryRunner=new QueryRunner();

    @Override
    public BookAdmin login(String username, String password) {
       Connection connection = JDBCTools.getConnection();
       String sql="select * from bookadmin where username=? and password=?";
       BookAdmin bookAdmin=null;
        try {
            bookAdmin=queryRunner.query(connection,sql,new BeanHandler<>(BookAdmin.class),username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return  bookAdmin;
    }

    @Override
    public void save(Book book) {
        Connection connection=JDBCTools.getConnection();
        String sql="insert into book(name,author,publish,pages,price,bookcaseid,abled) values(?,?,?,?,?,?,1)";
        try {
            queryRunner.update(connection,sql,book.getName(),book.getAuthor(),book.getPublish(),book.getPages(),book.getPrice(),book.getBookCaseId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    @Override
    public void deleteById(int id) {
        Connection connection=JDBCTools.getConnection();
        String sql="delete from book where id=?";
        try {
            queryRunner.update(connection,sql,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    @Override
    public Book findById(int id) {
        Connection connection=JDBCTools.getConnection();
        String sql="select * from book where id=?";
        Book book=null;
        try {
            book=queryRunner.query(connection,sql,new BeanHandler<>(Book.class),id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return book;
    }

    @Override
    public void updateById(Book book) {
        Connection connection=JDBCTools.getConnection();
        String sql="update book set name=?,author=?,publish=?,pages=?,price=?,bookcaseid=? where id=?";
        try {
            queryRunner.update(connection,sql,book.getName(),book.getAuthor(),book.getPublish(),book.getPages(),book.getPrice(),book.getBookCaseId(),book.getId());
        }catch (SQLException e)
        {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,null,null);
        }

    }
}
