package com.sj.repository.impl;

import com.sj.entity.BookCase;
import com.sj.repository.BookCaseRepository;
import com.sj.util.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookCaseRepositoryImpl implements BookCaseRepository {
    private QueryRunner queryRunner=new QueryRunner();

    @Override
    public BookCase find(int id) {
        Connection connection= JDBCTools.getConnection();
        String sql="select * from bookcase where id=?";
        BookCase bookCase=null;
        try {
             bookCase=queryRunner.query(connection,sql,new BeanHandler<>(BookCase.class),id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }

        return bookCase;
    }

    @Override
    public List<BookCase> findAllBookCase() {
        Connection connection=JDBCTools.getConnection();
        String sql="select * from bookcase";
        List<BookCase> bookCaseList=null;
        try {
            bookCaseList=queryRunner.query(connection,sql,new BeanListHandler<>(BookCase.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return bookCaseList;
    }
}
