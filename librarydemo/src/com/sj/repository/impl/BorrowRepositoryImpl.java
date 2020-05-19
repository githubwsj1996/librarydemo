package com.sj.repository.impl;

import com.sj.entity.BarData;
import com.sj.entity.Borrow;
import com.sj.entity.PieData;
import com.sj.repository.BorrowRepository;
import com.sj.util.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowRepositoryImpl implements BorrowRepository {

    private QueryRunner queryRunner=new QueryRunner();
    private PreparedStatement preparedStatement=null;
    private ResultSet resultSet=null;
    private List<Borrow> borrowList=null;

    @Override
    public List<Borrow> findAll(int index,int limit) {
        Connection connection= JDBCTools.getConnection();
        String sql="select b.name as bookname,r.name as readername,br.id,br.bookid as bookId," +
                "br.borrowtime,br.returntime,br.state from" +
                " borrow br,book b,reader r where br.bookid=b.id and br.readerid=r.id and state=0 limit ?,?";
        try {
            borrowList=queryRunner.query(connection,sql,new BeanListHandler<>(Borrow.class),index,limit);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return borrowList;
    }

    @Override
    public int count() {
        Connection connection=JDBCTools.getConnection();
        String sql="select count(*) from borrow where state=0";
        int count=0;
        try {
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next())
            {
                count=resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return count;
    }

    @Override
    public void updateByState(int id, int bookadmin,int state) {
            Connection connection=JDBCTools.getConnection();
            String sql="update borrow set  adminid=?,state=? where id=?";
        try {
            queryRunner.update(connection,sql,bookadmin,state,id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
    }

    @Override
    public List<Borrow> findBorrows(int index, int limit) {
            Connection connection=JDBCTools.getConnection();
        String sql="select b.name as bookname,r.name as readername,br.id,br.bookid as bookId,br.readerid as readerid," +
                "br.borrowtime,br.returntime,br.state from" +
                " borrow br,book b,reader r where br.bookid=b.id and br.readerid=r.id and br.state=1 limit ?,?";
        try {
            borrowList=queryRunner.query(connection,sql,new BeanListHandler<>(Borrow.class),index,limit);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return borrowList;
    }

    @Override
    public int countBorrows() {
        Connection connection=JDBCTools.getConnection();
        String sql="select count(*) from borrow where state=1";
        int count=1;
        ResultSet resultSet=null;
        try{
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next())
            {
                count=resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return count;
    }

    @Override
    public BarData getBarData() {
        Connection connection=JDBCTools.getConnection();
        String sql="select b.name,count(br.bookid) from book b,borrow br where b.id=br.bookid and state in (1,3) group by br.bookid";
        List<String> names=new ArrayList<>();
        List<Integer> counts=new ArrayList<>();
        BarData barData=new BarData();
        try {
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                String name=(resultSet.getString(1));
                Integer count=(resultSet.getInt(2));
                names.add(name);
                counts.add(count);
            }
            barData.setName(names);
            barData.setCount(counts);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return barData;
    }

    @Override
    public List<PieData> getPieData() {
        Connection connection=JDBCTools.getConnection();
        String sql="select b.name,count(br.bookid) from book b,borrow br where b.id=br.bookid and state in (1,3) group by br.bookid";
        List<PieData> list=new ArrayList<>();
        PieData pieData=null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Integer value=resultSet.getInt(2);
                String name=resultSet.getString(1);
                pieData= new PieData(value,name);
                list.add(pieData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }

        return list;
    }


}
