package com.example.BankingApp.service;

import com.example.BankingApp.exception.AccountNotFoundException;
import com.example.BankingApp.exception.DuplicateAccountException;
import com.example.BankingApp.exception.InsufficientFundException;
import com.example.BankingApp.model.Account;
import com.example.BankingApp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpli implements AccountService{

    @Autowired
    private AccountRepository acRepo;

    @Override
    public Account createAccount(Account account) {
        List<Account> all = acRepo.findAll();
        for(Account acc:all){
            if(account.getPhoneNumber() == acc.getPhoneNumber()){
                throw new DuplicateAccountException("Account with phone number "+acc.getPhoneNumber()+" already exists");
            }
        }
        return acRepo.save(account);
    }

    @Override
    public Account getAccount(long id) {
        return acRepo.findById(id).orElseThrow(()->new AccountNotFoundException("Account with id "+id+" not found"));
    }

    @Override
    public Account depositAmount(long id,double amount) {
        Account acc = acRepo.findById(id).orElseThrow(()->new AccountNotFoundException("Account with id "+id+" not found"));
        acc.setBalance(acc.getBalance()+amount);
        return acRepo.save(acc);
    }

    @Override
    public Account withdrawAmount(long id,double amount) {
        Account acc = acRepo.findById(id).orElseThrow(()->new AccountNotFoundException("Account with id "+id+" not found"));
        if(acc.getBalance() < amount){
            throw new InsufficientFundException("Insufficient funds");
        }
        acc.setBalance(acc.getBalance()-amount);
        return acRepo.save(acc);
    }

    @Override
    public List<Account> displayAll() {
        return acRepo.findAll();
    }

    @Override
    public double getBalance(long id) {
        Account acc = acRepo.findById(id).orElseThrow(()->new AccountNotFoundException("Account with id  "+id+ " not found"));
        return acc.getBalance();
    }

    @Override
    public Account updateDetails(Account account) {

        if(acRepo.existsById(account.getId())){
            return acRepo.save(account);
        }
        else{
            throw new AccountNotFoundException("Account with id  "+account.getId()+ " not found");
        }

    }

    @Override
    public List<Account> getCurrent() {
        List<Account> all = acRepo.findAll();
        all.removeIf(acc -> acc.getAccType().equals("Saving"));
        return all;
    }

    @Override
    public Account deleteAccount(long id) {
        Account acc = acRepo.findById(id).orElseThrow(()->new AccountNotFoundException("Account with id  "+id+ " not found"));
        acRepo.deleteById(id);
        return acc;
    }

}
