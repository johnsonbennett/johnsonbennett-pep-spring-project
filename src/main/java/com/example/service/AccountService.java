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

    public boolean accountExist(String username){
        return accountRepository.existsByUsername(username);
    }
    public Account registerUser(Account user) {
        System.out.println("Exist(Service): " + accountExist(user.getUsername()));
        if (!user.getUsername().isBlank() && user.getUsername().length() >= 4) {
            System.out.println("Account not exist, returning user");
            accountRepository.save(user);
            return user;
        }
        else{ 
            System.out.println("Invalid account, returning null");
            return null;
        }
    }
    
    public Account loginUser(Account user) {
        return accountRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
    

}
