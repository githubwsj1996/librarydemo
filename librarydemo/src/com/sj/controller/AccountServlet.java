package com.sj.controller;


import com.sj.entity.Account;
import com.sj.entity.BookAdmin;
import com.sj.entity.Reader;
import com.sj.service.AccountService;
import com.sj.service.impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AccountServlet extends HttpServlet {

    private AccountService accountService=new AccountServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method=req.getParameter("method");
        HttpSession session=req.getSession();
        if(method!=null)
        {
            switch (method) {
                case "login":
                    String username = req.getParameter("username");
                    String password = req.getParameter("password");
                    String type = req.getParameter("type");
                    Account account = accountService.login(username, password, type);
                    if (account == null) {
                        resp.sendRedirect("/login.jsp");
                    } else {
                        switch (type) {
                            case "reader":
                                Reader reader = new Reader();
                                reader.setId(account.getId());
                                reader.setUsername(account.getUsername());
                                reader.setPassword(account.getPassword());
                                reader.setName(account.getName());
                                reader.setTel(account.getTel());
                                reader.setCardid(account.getCardid());
                                reader.setGender(account.getGender());
                                session.setAttribute("reader", reader);
                                resp.sendRedirect("/index.jsp");
                                break;
                            case "bookadmin":
                                BookAdmin bookAdmin = new BookAdmin();
                                bookAdmin.setId(account.getId());
                                bookAdmin.setUsername(account.getUsername());
                                bookAdmin.setPassword(account.getPassword());
                                session.setAttribute("bookadmin", bookAdmin);
                                resp.sendRedirect("/main.jsp");
                                break;
                        }
                    }
                    break;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method=req.getParameter("method");
        HttpSession session=req.getSession();
        if(method==null) resp.sendRedirect("/login.jsp");

        switch (method)
        {
                case "logout":
                session.invalidate();
                resp.sendRedirect("/login.jsp");
                break;
        }
    }


}
