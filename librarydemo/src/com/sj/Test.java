package com.sj;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Test {
    public static void main(String[] args) {
        HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
        Sheet sheet=hssfWorkbook.createSheet("测试");
        Row row=sheet.createRow(0);
        Cell cell=row.createCell(0);
        cell.setCellValue("加油");
        cell=row.createCell(1);
        cell.setCellValue("奋斗");
        row=sheet.createRow(1);
        cell=row.createCell(0);
        cell.setCellValue("中国");
        cell=row.createCell(1);
        cell.setCellValue("加油");
        OutputStream outputStream=null;
        try {
            outputStream= new FileOutputStream("c:/File/测试.xls");
            hssfWorkbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
