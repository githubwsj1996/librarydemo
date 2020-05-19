package com.sj.repository;

import com.sj.entity.BarData;
import com.sj.entity.Borrow;
import com.sj.entity.PieData;

import java.util.List;

public interface BorrowRepository {
    public List<Borrow> findAll(int index,int limit);
    public int count();
    public void updateByState(int id,int bookadmin,int state);
    public List<Borrow> findBorrows(int index,int limit);
    public int countBorrows();

    public BarData getBarData();
    public List<PieData> getPieData();
}
