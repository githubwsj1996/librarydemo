package com.sj.controller;

import com.sj.entity.*;
import com.sj.repository.BorrowRepository;
import com.sj.repository.ReturnRepository;
import com.sj.service.BookCaseService;
import com.sj.service.BookService;
import com.sj.service.BorrowService;
import com.sj.service.ReturnService;
import com.sj.service.impl.BookCaseServiceImpl;
import com.sj.service.impl.BookServiceImpl;
import com.sj.service.impl.BorrowServiceImpl;
import com.sj.service.impl.ReturnServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookAdminServlet extends HttpServlet
{
    private BookCaseService bookCaseService=new BookCaseServiceImpl();
    private BookService bookService=new BookServiceImpl();
    private BorrowService borrowService=new BorrowServiceImpl();
    private ReturnService returnService=new ReturnServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=utf-8");
        String method=req.getParameter("method");
        Book book=null;
        int id=0;
        String idStr=null;
        String name=null;
        String author=null;
        String publish=null;
        String pagesStr=null;
        int pages=0;
        String priceStr=null;
        Float  price=null;
        String bookCaseIdStr=null;
        int bookCaseId=0;
        String pageStr=null;
        int page=0;
        String limitStr=null;
        int limit =0;
        String bookIdStr=null;
        int bookId=0;
        String readerIdStr=null;
        int readerId=0;

        if(method!=null)
        {
            switch (method){
                case "findBookCases":
                    List<BookCase> list=bookCaseService.findAllBookCase();
                    req.setAttribute("list",list);
                    req.getRequestDispatcher("/book_add.jsp").forward(req,resp);
                    break;
                case "addBook":
                     name=req.getParameter("name");
                     author=req.getParameter("author");
                     publish=req.getParameter("publish");
                     pagesStr=req.getParameter("pages");
                     pages=Integer.parseInt(pagesStr);
                     priceStr=req.getParameter("price");
                     price=Float.parseFloat(priceStr);
                     bookCaseIdStr=req.getParameter("bookCaseId");
                     bookCaseId=Integer.parseInt(bookCaseIdStr);
                     book=new Book(name,author,publish,pages,price,bookCaseId);
                     bookService.save(book);
                    resp.sendRedirect("/book_manage.jsp");
                    break;
                case "deleteById":
                    idStr=req.getParameter("id");
                    id=Integer.parseInt(idStr);
                    bookService.deleteById(id);
                    resp.sendRedirect("/book_manage.jsp");
                    break;
                case "findById":
                    idStr=req.getParameter("id");
                    id=Integer.parseInt(idStr);
                    Book book1=bookService.findById(id);
                    req.setAttribute("book",book1);
                    List<BookCase> list1=bookCaseService.findAllBookCase();
                    req.setAttribute("list",list1);
                    req.getRequestDispatcher("/book_edit.jsp").forward(req,resp);
                    break;
                case "bookEdit":
                    idStr=req.getParameter("id");
                    id=Integer.parseInt(idStr);
                    name=req.getParameter("name");
                    author=req.getParameter("author");
                    publish=req.getParameter("publish");
                    pagesStr=req.getParameter("pages");
                    pages=Integer.parseInt(pagesStr);
                    priceStr=req.getParameter("price");
                    price=Float.parseFloat(priceStr);
                    bookCaseIdStr=req.getParameter("bookCaseId");
                    bookCaseId=Integer.parseInt(bookCaseIdStr);
                    book=new Book(id,name,author,publish,pages,price,bookCaseId);
                    bookService.updateById(book);
                    resp.sendRedirect("/book_manage.jsp");
                    break;
                case "getBorrows":
                    pageStr=req.getParameter("page");
                    page=Integer.parseInt(pageStr);
                    limitStr=req.getParameter("limit");
                    limit=Integer.parseInt(limitStr);
                    List<Borrow> borrowList=borrowService.findAll(page,limit);
                    BorrowVO borrowVO=new BorrowVO(0,"",borrowService.count(),borrowList);
                    JSONObject jsonObject=JSONObject.fromObject(borrowVO);
                    resp.getWriter().write(jsonObject.toString());
                    break;
                case "agreeBorrow":
                    idStr=req.getParameter("id");
                    id=Integer.parseInt(idStr);
                    HttpSession session=req.getSession();
                    BookAdmin bookAdmin=(BookAdmin)session.getAttribute("bookadmin");
                    int bookAdminId=bookAdmin.getId();
                    borrowService.updateByState(id,bookAdminId,1);
                    resp.sendRedirect("/borrow_manage.jsp");
                    break;
                case "disAgreeBorrow" :
                    idStr=req.getParameter("id");
                    id=Integer.parseInt(idStr);
                    HttpSession session1=req.getSession();
                    BookAdmin bookAdmin1=(BookAdmin)session1.getAttribute("bookadmin");
                    int bookAdminId1=bookAdmin1.getId();
                    borrowService.updateByState(id,bookAdminId1,2);
                    bookIdStr=req.getParameter("bookId");
                    bookId=Integer.parseInt(bookIdStr);
                    bookService.updateAbled(bookId,1);
                    resp.sendRedirect("/borrow_manage.jsp");
                    break;
                case "getReturn":
                    pageStr=req.getParameter("page");
                    page=Integer.parseInt(pageStr);
                    limitStr=req.getParameter("limit");
                    limit=Integer.parseInt(limitStr);
                    List<Borrow> borrowList1=borrowService.findBorrows(page,limit);
                    BorrowVO borrowVO1=new BorrowVO();
                    borrowVO1.setCode(0);
                    borrowVO1.setMsg("");
                    borrowVO1.setCount(borrowService.countBorrows());
                    borrowVO1.setData(borrowList1);
                    JSONObject jsonObject1=JSONObject.fromObject(borrowVO1);
                    resp.getWriter().write(jsonObject1.toString());
                    break;
                case "agreeReturn":
                    idStr=req.getParameter("id");
                    id=Integer.parseInt(idStr);
                    HttpSession session2=req.getSession();
                    BookAdmin bookAdmin2=(BookAdmin)session2.getAttribute("bookadmin");
                    int bookAdminId2=bookAdmin2.getId();
                    bookIdStr=req.getParameter("bookId");
                    bookId=Integer.parseInt(bookIdStr);
                    readerIdStr=req.getParameter("readerId");
                    readerId=Integer.parseInt(readerIdStr);
                    borrowService.updateByState(id,bookAdminId2,3);
                    Borrow borrow=new Borrow();
                    Date date=new Date();
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                    String returnTime=sdf.format(date);
                    borrow.setBookId(bookId);
                    borrow.setReaderId(readerId);
                    borrow.setReturnTime(returnTime);
                    borrow.setAdminId(bookAdminId2);
                    returnService.saveReturn(borrow);
                    bookService.updateAbled(bookId,1);
                    resp.sendRedirect("/book_manage.jsp");
                    break;
                case "getBarData2":
                    BarData barData=borrowService.getBarData();
                    JSONObject jsonObject2=JSONObject.fromObject(barData);
                    resp.getWriter().write(jsonObject2.toString());
                    break;
                case "getPieData1":
                    List<PieData> list2=borrowService.getPieData();
                    JSONArray jsonArray=JSONArray.fromObject(list2);
                    resp.getWriter().write(jsonArray.toString());
                    break;
                case  "exportBook":
                    HSSFWorkbook hssfWorkbook=bookService.getHSSFWorkbook();
                    resp.setContentType("application/x-msdownload");
                    String fileName= URLEncoder.encode("图书信息.xls","utf-8");
                    resp.setHeader("Content-Disposition","attachment;filename="+fileName);
                    OutputStream outputStream=resp.getOutputStream();
                    hssfWorkbook.write(outputStream);
                    outputStream.close();
                    break;

            }
        }

    }

}

