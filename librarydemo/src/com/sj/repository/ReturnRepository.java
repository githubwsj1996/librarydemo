package com.sj.repository;


import com.sj.entity.Borrow;

public interface ReturnRepository {
    public void saveReturn(Borrow borrow);
}
