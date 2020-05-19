package com.sj.service;

import com.sj.entity.Account;

public interface AccountService {
    public Account login(String username,String password,String type);
}
