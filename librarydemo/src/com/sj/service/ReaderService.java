package com.sj.service;

import com.sj.entity.Borrow;
import com.sj.entity.Reader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public interface ReaderService {
    public List<Borrow> findAllById(Integer id,int page,int limit);
    public int borrowCountById(int id);

    public void saveReader(Reader reader);
    public List<Reader> findAllReader(int page,int limit);
    public int countReader();
    public void deleteReader(int id);
    public Reader findByReaderById(int id);
    public void updateReader(Reader reader);

    public HSSFWorkbook getExportBorrowed(int  id);
}
