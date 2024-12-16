package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    
    @Autowired
    public void setAccountRepository(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }
    public Account registerUser(Account user) {
        if (user.getUsername() != null && !user.getUsername().isBlank() && user.getUsername().length() > 4) {
            accountRepository.save(user);
            return user;
        }
        else return null;
    }
    
    public Account loginUser(Account user) {
        return accountRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
    

}
