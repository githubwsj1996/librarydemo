package com.sj.repository.impl;

import com.sj.entity.Borrow;
import com.sj.entity.Reader;
import com.sj.repository.ReaderRepository;
import com.sj.util.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReaderRepositoryImpl implements ReaderRepository {

   private QueryRunner queryRunner=new QueryRunner();

    @Override
    public Reader login(String username, String password) {
        Connection connection= JDBCTools.getConnection();
        String sql="select * from reader where username=? and password=?";
        Reader reader=null;
        try {
            reader=queryRunner.query(connection,sql, new BeanHandler<>(Reader.class),username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,null,null);
        }
        return reader;
    }

    @Override
    public List<Borrow> findBorrowById(Integer id,int index,int limit) {
        Connection connection=JDBCTools.getConnection();
        String sql="select br.id as id,b.name as bookName,b.author ,b.price,r.cardid,r.name as readerName,r.tel, br.borrowtime,br.returntime,br.state as state from borrow br,book b,reader r where br.bookid=b.id and br.readerid=r.id and br.readerid=? limit ?,?";
        List<Borrow> list =null;
        try {
           list=queryRunner.query(connection,sql,new BeanListHandler<>(Borrow.class),id,index,limit);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null );
        }

        return list;
    }

    @Override
    public int borrowCountById(int id) {
        Connection connection=JDBCTools.getConnection();
        String sql="select count(*) from borrow where state=0 and readerid=?";
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        int count=0;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
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
    public void saveReader(Reader reader) {
        Connection connection=JDBCTools.getConnection();
        String sql="insert into reader(username,password,name,tel,cardid,gender) value(?,?,?,?,?,?)";
        try {
            queryRunner.update(connection,sql,reader.getUsername(),reader.getPassword(),reader.getName(),reader.getTel(),reader.getCardid(),reader.getGender());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }

    }

    @Override
    public List<Reader> findAllReader(int index, int limit) {
        Connection connection=JDBCTools.getConnection();
        String sql="select * from reader limit ?,?";
        List<Reader> readerList=null;
        try {
            readerList=queryRunner.query(connection,sql,new BeanListHandler<>(Reader.class),index,limit);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return readerList;
    }

    @Override
    public int countReader() {
        Connection connection=JDBCTools.getConnection();
        String sql="select count(*) from reader";
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        int count=0;
        try {
           preparedStatement=connection.prepareStatement(sql);
           resultSet=preparedStatement.executeQuery();
           if(resultSet.next())
           {
               count=resultSet.getInt(1);
           }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return count;
    }

    @Override
    public void deleteReader(int id) {
        Connection connection=JDBCTools.getConnection();
        String sql="delete from reader where id=?";
        try {
            queryRunner.update(connection,sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    @Override
    public Reader findByReaderById(int id) {
        Connection connection=JDBCTools.getConnection();
        String sql="select * from reader where id=?";
        Reader reader=null;
        try {
            reader=queryRunner.query(connection,sql,new BeanHandler<>(Reader.class),id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return reader;
    }

    @Override
    public void updateReader(Reader reader) {
        Connection connection=JDBCTools.getConnection();
        String sql="update reader set username=?,password=?,name=?,tel=?,cardid=?,gender=? where id=?";
        try {
            queryRunner.update(connection,sql,reader.getUsername(),reader.getPassword(),reader.getName(),reader.getTel(),reader.getCardid(),reader.getGender(),reader.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    @Override
    public List<Borrow> findBorrowById(int id) {
        Connection connection=JDBCTools.getConnection();
        String sql="select br.id as id,b.name as bookName,b.author ,b.price,r.cardid,r.name as readerName,r.tel, br.borrowtime,br.returntime,br.state as state from borrow br,book b,reader r where br.bookid=b.id and br.readerid=r.id and br.readerid=? ";
        List<Borrow> list =null;
        try {
            list=queryRunner.query(connection,sql,new BeanListHandler<>(Borrow.class),id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null );
        }

        return list;
    }


}
