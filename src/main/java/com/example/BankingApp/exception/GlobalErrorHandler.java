package com.example.BankingApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(DuplicateAccountException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ProblemDetail handleDuplicateAccountException(DuplicateAccountException ex){
        ProblemDetail response = ProblemDetail.forStatus(HttpStatus.NOT_ACCEPTABLE);
        response.setDetail(ex.getMessage());
        return response;
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleAccountNotFoundException(AccountNotFoundException ex){
        ProblemDetail res = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        res.setDetail(ex.getMessage());
        return res;
    }

    @ExceptionHandler(InsufficientFundException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ProblemDetail handleInsufficientFundException(InsufficientFundException ex){
        ProblemDetail res = ProblemDetail.forStatus(HttpStatus.NOT_ACCEPTABLE);
        res.setDetail(ex.getMessage());
        return res;
    }
}
