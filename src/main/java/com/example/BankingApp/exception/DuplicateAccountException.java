package com.example.BankingApp.exception;

public class DuplicateAccountException extends RuntimeException{
    public DuplicateAccountException(String msg){
        super(msg);
    }
}
