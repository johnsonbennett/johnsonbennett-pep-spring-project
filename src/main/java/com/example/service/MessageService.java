package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    
    @Autowired
    public void setMessageRepository(MessageRepository messageRepository){
        this.messageRepository=messageRepository;
    }

    public Message createMessage(Message message){
        if(!message.getMessageText().isBlank() && message.getMessageText().length() < 255){
            messageRepository.save(message);
            return message;
        }
        else return null;
    }

   public List<Message> getAllMessage(){
        return messageRepository.findAll();
   }

   public Message deleteById(int id){
        Message deleted = messageRepository.getById(id);
        messageRepository.deleteById(id);
        return deleted;
   }
}
