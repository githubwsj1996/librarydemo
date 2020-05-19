package com.sj.service.impl;


import com.sj.entity.Borrow;
import com.sj.repository.ReturnRepository;
import com.sj.repository.impl.ReturnRepositoryImpl;
import com.sj.service.ReturnService;


public class ReturnServiceImpl implements ReturnService {

    private ReturnRepository returnRepository=new ReturnRepositoryImpl();

    @Override
    public void saveReturn(Borrow borrow) {
        returnRepository.saveReturn(borrow);
    }
}
