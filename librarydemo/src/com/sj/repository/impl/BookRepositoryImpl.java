package com.sj.repository.impl;

import com.sj.entity.Book;
import com.sj.repository.BookRepository;
import com.sj.util.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    private QueryRunner queryRunner=new QueryRunner();

    @Override
    public List<Book> findAll(int index,int limit) {
        Connection connection= JDBCTools.getConnection();
        String sql="select * from book where abled=1 limit ?,? ";
        List<Book> list=null;
        try {
            list = queryRunner.query(connection,sql,new BeanListHandler<>(Book.class),index,limit);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,null,null);
        }
        return list;
    }

    @Override
    public int count() {
        Connection connection=JDBCTools.getConnection();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String sql="select count(id) from book";
        int count=0;
        try {
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next())
            {
                count=resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return count;
    }

    @Override
    public void borrow(int bookId, int readerId, String borrowTime, String returnTime, int state) {
        Connection connection=JDBCTools.getConnection();
        String sql="insert into borrow(bookid,readerid,borrowtime,returntime,state) values(?,?,?,?,?)";
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,bookId);
            preparedStatement.setInt(2,readerId);
            preparedStatement.setString(3,borrowTime);
            preparedStatement.setString(4,returnTime);
            preparedStatement.setInt(5,state);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }

    }

    @Override
    public void updateAbled(int id, int abled) {
        Connection connection=JDBCTools.getConnection();
        String sql="update book set abled=? where id=?";
        QueryRunner queryRunner=new QueryRunner();
        try {
            queryRunner.update(connection,sql,abled,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }

    }

    @Override
    public List<Book> findAll() {
        Connection connection=JDBCTools.getConnection();
        String sql="select b.name,b.author,b.publish,b.pages,b.price,bc.name as bookCaseName from book b,bookcase bc where b.bookcaseid=bc.id";
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        List<Book> books=new ArrayList<>();
        Book book=null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String name=resultSet.getString(1);
                String author=resultSet.getString(2);
                String publish=resultSet.getString(3);
                Integer pages=resultSet.getInt(4);
                Float price=resultSet.getFloat(5);
                String bookCaseName=resultSet.getString(6);
                book=new Book(name,author,publish,pages,price,bookCaseName);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return books;
    }
}
