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
        boolean valid_poster = messageRepository.existsByPostedBy(message.getPostedBy());
        if(!message.getMessageText().isBlank() && message.getMessageText().length() < 255 && valid_poster){
            messageRepository.save(message);
            return message;
        }
        else return null;
    }

   public List<Message> getAllMessage(){
        return messageRepository.findAll();
   }

   public Integer deleteById(int id) {
    Optional<Message> deleted = messageRepository.findById(id);
    Integer deleteCount = messageRepository.countByMessageId(id);
    if (deleted.isPresent()) {
        messageRepository.deleteById(id);
        return deleteCount;
    }
    else return null;
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

    public Integer UpdateById(int id, String message) {
        Optional<Message> updated = messageRepository.findById(id);
        Integer updateCount = messageRepository.countByMessageId(id);
        if (updated.isPresent() && !message.isBlank() && message.length() < 255) {
            return updateCount;
        }
        return null;
        }


}
