package com.ei.math.service;

import com.ei.math.entity.Group;
import com.ei.math.entity.Message;
import com.ei.math.repository.MessageRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageService{
    
    @Autowired
    MessageRepository memberRepository;
    
    public Page<Message> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }
    
    public Page<Message> findMessagesByGroup(Pageable pageable, Group group) {
        return memberRepository.findMessagesByGroup(pageable, group);
    }    

    public Message save(Message group){
        return memberRepository.save(group);
    }

    public void remove(String id){
        memberRepository.deleteById(UUID.fromString(id));
    }

    public void remove(UUID id){
        memberRepository.deleteById(id);
    }    
    
    public Optional<Message> findOneExample(Message member){
        return memberRepository.findOne(Example.of(member));
    }
    
    public boolean exists(Message member){
        return memberRepository.exists(Example.of(member));
    }
    
}
