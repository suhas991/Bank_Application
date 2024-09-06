package com.example.BankingApp.api;

import com.example.BankingApp.model.Account;
import com.example.BankingApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bank/accounts")
@CrossOrigin("http://localhost:5173/")
public class AccountController {

       @Autowired
       private AccountService accountService;

       @GetMapping
       @ResponseStatus(HttpStatus.OK)
       public List<Account> displayAll(){
           return accountService.displayAll();
       }

       @PostMapping
       @ResponseStatus(HttpStatus.CREATED)
       public Account addAccount(@RequestBody Account account){
           return accountService.createAccount(account);
       }

       @GetMapping("/{id}")
       @ResponseStatus(HttpStatus.OK)
       public Account findAccount(@PathVariable long id){
           return accountService.getAccount(id);
       }

       @PostMapping("/{id}/deposit")
       @ResponseStatus(HttpStatus.ACCEPTED)
       public Account addMoney(@PathVariable long id,@RequestBody Map<String,Double> request){
           Double amount = request.get("amount");
           return accountService.depositAmount(id,amount);
       }

       @PostMapping("/{id}/withdraw")
       @ResponseStatus(HttpStatus.ACCEPTED)
       public Account withdrawMoney(@PathVariable long id,@RequestBody Map<String,Double> request){
           Double amount = request.get("amount");
           return accountService.withdrawAmount(id,amount);
       }

       @GetMapping("/balance/{id}")
       @ResponseStatus(HttpStatus.ACCEPTED)
       public double getBalance(@PathVariable long id){
           return accountService.getBalance(id);
       }

       @PutMapping
       @ResponseStatus(HttpStatus.ACCEPTED)
       public Account update(@RequestBody Account account){
           return accountService.updateDetails(account);
       }

       @GetMapping("/currentAccount")
       @ResponseStatus(HttpStatus.ACCEPTED)
       public List<Account> getCurrent(){
           return accountService.getCurrent();
       }
       
       @DeleteMapping("/delete/{id}")
       @ResponseStatus(HttpStatus.ACCEPTED)
       public Account delete(@PathVariable long id){
           return accountService.deleteAccount(id);
       }
}
