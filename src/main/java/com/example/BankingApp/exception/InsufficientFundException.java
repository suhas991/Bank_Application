package com.example.BankingApp.exception;

public class InsufficientFundException extends RuntimeException{
    public InsufficientFundException(String msg){
        super(msg);
    }
}
