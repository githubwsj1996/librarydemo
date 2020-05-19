package com.sj.repository;

import com.sj.entity.Borrow;
import com.sj.entity.Reader;

import java.util.List;

public interface ReaderRepository {
    public Reader login(String username,String password);

    public List<Borrow> findBorrowById(Integer id,int index,int limit);
    public int borrowCountById(int id);

    public void saveReader(Reader reader);
    public List<Reader> findAllReader(int index,int limit);
    public int countReader();
    public void deleteReader(int id);
    public Reader findByReaderById(int id);
    public void updateReader(Reader reader);

    public List<Borrow> findBorrowById(int id);
}
