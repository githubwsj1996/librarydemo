package com.sj.repository.impl;

import com.sj.entity.Borrow;
import com.sj.repository.ReturnRepository;
import com.sj.util.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

public class ReturnRepositoryImpl implements ReturnRepository {

    private QueryRunner queryRunner=new QueryRunner();

    @Override
    public void saveReturn(Borrow borrow) {
        Connection connection= JDBCTools.getConnection();
        String sql="insert into returnbook(bookid,readerid,returntime,adminid) values(?,?,?,?)";
        try {
            queryRunner.update(connection,sql,borrow.getBookId(),borrow.getReaderId(),borrow.getReturnTime(),borrow.getAdminId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }

    }
}
