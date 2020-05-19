package com.sj.service.impl;

import com.sj.entity.Account;
import com.sj.repository.BookAdminRepository;
import com.sj.repository.ReaderRepository;
import com.sj.repository.impl.BookAdminRepositoryImpl;
import com.sj.repository.impl.ReaderRepositoryImpl;
import com.sj.service.AccountService;


public class AccountServiceImpl implements AccountService {

    private ReaderRepository readerRepository=new ReaderRepositoryImpl();

    private BookAdminRepository bookAdminRepository=new BookAdminRepositoryImpl();

    @Override
    public Account login(String username, String password, String type) {
        Account account=null;
        switch (type) {
            case "reader":
               account= readerRepository.login(username,password);
                break;
            case "bookadmin":
               account= bookAdminRepository.login(username,password);
               break;
        }
        return account;
    }
}
