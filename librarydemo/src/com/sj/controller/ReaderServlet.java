package com.sj.controller;

import com.sj.entity.Borrow;
import com.sj.entity.BorrowVO;
import com.sj.entity.Reader;
import com.sj.entity.ReaderVO;
import com.sj.service.ReaderService;
import com.sj.service.impl.ReaderServiceImpl;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

public class ReaderServlet extends HttpServlet {

    private ReaderService readerService=new ReaderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=utf-8");
        HttpSession session=req.getSession();
        Reader reader=(Reader)session.getAttribute("reader");
        int readerId=reader.getId();
        String pageStr = null;
        int page = 0;
        String limitStr =null;
        int limit = 0;
        String method=req.getParameter("method");
        String idStr=null;
        int id=0;
        String username=null;
        String password=null;
        String name=null;
        String tel=null;
        String cardid=null;
        String gender=null;
        if(method==null)
        {
            method="getBorrowed";

        }
        switch (method){
            case "getBorrowed":
                pageStr = req.getParameter("page");
                page = Integer.parseInt(pageStr);
                limitStr = req.getParameter("limit");
                limit = Integer.parseInt(limitStr);
                List<Borrow> list=readerService.findAllById(readerId,page,limit);
                BorrowVO borrowVO = new BorrowVO(0,"",readerService.borrowCountById(readerId),list);
                JSONObject jsonObject=JSONObject.fromObject(borrowVO);
                resp.getWriter().write(jsonObject.toString());
                break;
            case "addReader":
                username=req.getParameter("username");
                password=req.getParameter("password");
                name=req.getParameter("name");
                tel=req.getParameter("tel");
                cardid=req.getParameter("cardid");
                gender=req.getParameter("gender");
                Reader reader1=new Reader();
                reader1.setUsername(username);
                reader1.setPassword(password);
                reader1.setName(name);
                reader1.setTel(tel);
                reader1.setCardid(cardid);
                reader1.setGender(gender);
                readerService.saveReader(reader1);
                resp.sendRedirect("/reader_manage.jsp");
                break;
            case "findAll":
                pageStr = req.getParameter("page");
                page = Integer.parseInt(pageStr);
                limitStr = req.getParameter("limit");
                limit = Integer.parseInt(limitStr);
                ReaderVO readerVO=new ReaderVO(0,"",readerService.countReader(),readerService.findAllReader(page,limit));
                JSONObject jsonObject1=JSONObject.fromObject(readerVO);
                resp.getWriter().write(jsonObject1.toString());
                break;
            case "deleteReader":
                idStr=req.getParameter("id");
                id=Integer.parseInt(idStr);
                readerService.deleteReader(id);
                resp.sendRedirect("/reader_manage.jsp");
                break;
            case "findReaderById":
                idStr=req.getParameter("id");
                id=Integer.parseInt(idStr);
                Reader readera=readerService.findByReaderById(id);
                req.setAttribute("readera",readera);
                req.getRequestDispatcher("/reader_edit.jsp").forward(req,resp);
                break;
            case "updateReader":
                idStr=req.getParameter("id");
                id=Integer.parseInt(idStr);
                username=req.getParameter("username");
                password=req.getParameter("password");
                name=req.getParameter("name");
                tel=req.getParameter("tel");
                cardid=req.getParameter("cardid");
                gender=req.getParameter("gender");
                Reader reader2=new Reader();
                reader2.setId(id);
                reader2.setUsername(username);
                reader2.setPassword(password);
                reader2.setName(name);
                reader2.setTel(tel);
                reader2.setCardid(cardid);
                reader2.setGender(gender);
                readerService.updateReader(reader2);
                resp.sendRedirect("/reader_manage.jsp");
                break;
            case "exportBorrowed":
                HSSFWorkbook hssfWorkbook=readerService.getExportBorrowed(readerId);
                resp.setContentType("application/x-msdownload");
                String fileName= URLEncoder.encode(reader.getName()+"的借书记录详情.xls","utf-8");
                resp.setHeader("Content-Disposition","attachment;filename="+fileName);
                OutputStream outputStream=resp.getOutputStream();
                hssfWorkbook.write(outputStream);
                outputStream.close();
                break;

        }
    }
}
