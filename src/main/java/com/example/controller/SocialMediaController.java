package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@Controller
public class SocialMediaController {
    private final AccountService accountService;
    private final MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }
    
    @PostMapping("/register")
    public @ResponseBody ResponseEntity<Account> register(@RequestBody Account user) {
        Account registeredUser = accountService.registerUser(user);
        if (registeredUser != null) {
            return ResponseEntity.status(200).body(registeredUser);
        }
        else {
            return ResponseEntity.status(400).build();
        }
    }

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<Account> login(@RequestBody Account user) {
        Account loggedInUser = accountService.loginUser(user);
        if (loggedInUser != null) {
            return ResponseEntity.status(200).body(loggedInUser);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/messages")
    public @ResponseBody ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message messageCreated = messageService.createMessage(message);
        if (messageCreated != null) {
            return ResponseEntity.status(200).body(messageCreated);
        } else {
            return ResponseEntity.status(400).build();
        }
    }
}