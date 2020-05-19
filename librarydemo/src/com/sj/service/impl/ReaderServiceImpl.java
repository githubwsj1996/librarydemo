package com.sj.service.impl;

import com.sj.entity.Book;
import com.sj.entity.Borrow;
import com.sj.entity.Reader;
import com.sj.repository.ReaderRepository;
import com.sj.repository.impl.ReaderRepositoryImpl;
import com.sj.service.ReaderService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

public class ReaderServiceImpl implements ReaderService {
    private ReaderRepository readerRepository=new ReaderRepositoryImpl();
    @Override
    public List<Borrow> findAllById(Integer id,int page,int limit) {
        int index=(page-1)* limit;
        List<Borrow> list=readerRepository.findBorrowById(id,index,limit);
        for(Borrow borrow:list) {
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
        return list;
    }

    @Override
    public int borrowCountById(int id) {
        return readerRepository.borrowCountById(id);
    }

    @Override
    public void saveReader(Reader reader) {
          readerRepository.saveReader(reader);
    }

    @Override
    public List<Reader> findAllReader(int page, int limit) {
        int index=(page-1)*limit;
        List<Reader> readerList= readerRepository.findAllReader(index,limit);
        return readerList;
    }

    @Override
    public int countReader() {
        return readerRepository.countReader();
    }

    @Override
    public void deleteReader(int id) {
        readerRepository.deleteReader(id);
    }

    @Override
    public Reader findByReaderById(int id) {
        return readerRepository.findByReaderById(id);
    }

    @Override
    public void updateReader(Reader reader) {
        readerRepository.updateReader(reader);
    }

    @Override
    public HSSFWorkbook getExportBorrowed(int id) {
        HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
        Sheet sheet=hssfWorkbook.createSheet("订单");
        List<Borrow> list=readerRepository.findBorrowById(id);
        Row row=sheet.createRow(0);
        Cell cell=row.createCell(0);
        cell.setCellValue("订单编号");
        cell=row.createCell(1);
        cell.setCellValue("图书名称");
        cell=row.createCell(2);
        cell.setCellValue("读者");
        cell=row.createCell(3);
        cell.setCellValue("借书时间");
        cell=row.createCell(4);
        cell.setCellValue("还书时间");
        cell=row.createCell(5);
        cell.setCellValue("状态");
        for(int i=0;i<list.size();i++)
        {
            Borrow borrow=list.get(i);
            row=sheet.createRow(i+1);
            cell=row.createCell(0);
            cell.setCellValue(borrow.getId());
            cell=row.createCell(1);
            cell.setCellValue(borrow.getBookName());
            cell=row.createCell(2);
            cell.setCellValue(borrow.getReaderName());
            cell=row.createCell(3);
            cell.setCellValue(borrow.getBorrowTime());
            cell=row.createCell(4);
            cell.setCellValue(borrow.getReturnTime());

            switch (borrow.getState())
            {
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
            cell=row.createCell(5);
            cell.setCellValue(borrow.getState());
        }
        return hssfWorkbook;
    }
}
