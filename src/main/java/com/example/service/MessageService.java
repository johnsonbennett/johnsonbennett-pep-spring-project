package com.example.service;

import java.util.List;
import java.util.Optional;

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

   public Message deleteById(int id) {
    Optional<Message> deleted = messageRepository.findById(id);
    if (deleted.isPresent()) {
        messageRepository.deleteById(id);
        return deleted.get();
    }
    return null;
    }

    public Message getMessageById(int id) {
        Optional<Message> message = messageRepository.findById(id);
        if (message.isPresent()) {
            return message.get();
        }
        return null;
    }

    public List<Message> getAllMessageByUser(int accountId){
        List<Message> messages = messageRepository.getMessageByPostedBy(accountId);
        return messages;
    }


}
