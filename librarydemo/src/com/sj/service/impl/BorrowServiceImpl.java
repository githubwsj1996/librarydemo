package com.sj.service.impl;

import com.sj.entity.BarData;
import com.sj.entity.Borrow;
import com.sj.entity.PieData;
import com.sj.repository.BorrowRepository;
import com.sj.repository.impl.BorrowRepositoryImpl;
import com.sj.service.BorrowService;

import java.util.List;

public class BorrowServiceImpl implements BorrowService {

   private BorrowRepository borrowRepository=new BorrowRepositoryImpl();

    @Override
    public List<Borrow> findAll(int page,int limit)
    {
        int index=(page-1)*limit;
        List<Borrow> list=borrowRepository.findAll(index,limit);
        for(Borrow borrow:list)
        {
            switch (borrow.getState()){
                case "0":
                    borrow.setState("未审核");
                    break;
                case "1":
                    borrow.setState("审核通过");
                    break;
                case "2":
                    borrow.setState("审核未通过");
                    break;
                case "3":
                    borrow.setState("已归还");
                    break;
            }
        }
        return list;
    }

    @Override
    public int count() {
        return borrowRepository.count();
    }

    @Override
    public void updateByState(int id,int bookadmin, int state) {
        borrowRepository.updateByState(id,bookadmin,state);
    }

    @Override
    public List<Borrow> findBorrows(int page, int limit) {
        int index=(page-1)*limit;
        List<Borrow> borrowList=borrowRepository.findBorrows(index,limit);
        for(Borrow borrow:borrowList) {
            switch (borrow.getState()) {
                case "0":
                    borrow.setState("未审核");
                    break;
                case "1":
                    borrow.setState("审核通过");
                    break;
                case "2":
                    borrow.setState("审核未通过");
                    break;
                case "3":
                    borrow.setState("已归还");
                    break;
            }
        }
        return borrowList;
    }

    @Override
    public int countBorrows() {
        return borrowRepository.countBorrows();
    }

    @Override
    public BarData getBarData() {
        return borrowRepository.getBarData();
    }

    @Override
    public List<PieData> getPieData() {
        return borrowRepository.getPieData();
    }
}
