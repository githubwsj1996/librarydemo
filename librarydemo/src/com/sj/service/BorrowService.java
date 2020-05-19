package com.sj.service;

import com.sj.entity.BarData;
import com.sj.entity.Borrow;
import com.sj.entity.PieData;

import java.util.List;

public interface BorrowService {
    public List<Borrow> findAll(int page,int limit);
    public int count();
    public void updateByState(int id, int bookadmin,int state);
    public List<Borrow> findBorrows(int page,int limit);
    public int countBorrows();

    public BarData getBarData();
    public List<PieData> getPieData();
}
