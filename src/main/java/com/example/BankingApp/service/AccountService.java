package com.example.BankingApp.service;

import com.example.BankingApp.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
      public Account createAccount(Account account);
      public Account getAccount(long id);
      public Account depositAmount(long id,double amount);
      public Account withdrawAmount(long id,double amount);
      public List<Account> displayAll();
}
