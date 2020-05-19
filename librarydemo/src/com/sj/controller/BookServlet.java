package com.sj.controller;

import com.sj.entity.Book;
import com.sj.entity.BookVO;
import com.sj.entity.Reader;
import com.sj.service.BookService;
import com.sj.service.impl.BookServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookServlet extends HttpServlet {

    private BookService bookService=new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method == null){
            method = "findAll";
        }
        switch (method){
            case "findAll":
                String pageStr = req.getParameter("page");
                int page = Integer.parseInt(pageStr);
                String limitStr = req.getParameter("limit");
                int limit = Integer.parseInt(limitStr);
                resp.setContentType("text/json;charset=utf-8");
                List<Book> list = bookService.findAll(page,limit);
                BookVO bookVO = new BookVO();
                bookVO.setCode(0);
                bookVO.setMsg("");
                bookVO.setCount(bookService.count());
                bookVO.setData(list);
                JSONObject jsonObject = JSONObject.fromObject(bookVO);
                resp.getWriter().write(jsonObject.toString());
                break;
            case "borrow":
                String bookIdStr=req.getParameter("bookid");
                int bookId=Integer.parseInt(bookIdStr);
                HttpSession session=req.getSession();
                Reader reader=(Reader) session.getAttribute("reader");
                int readerId=reader.getId();
                Date date=new Date();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                String borrowTime=sdf.format(date);
                Calendar calendar=Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR)+14);
                String returnTime=sdf.format(calendar.getTime());
                bookService.borrow(bookId,readerId,borrowTime,returnTime,0);
                resp.sendRedirect("/index.jsp");
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
