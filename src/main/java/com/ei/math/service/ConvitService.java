package com.ei.math.service;

import com.ei.math.entity.Convit;
import com.ei.math.entity.UserPeople;
import com.ei.math.repository.ConvitRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConvitService{
    @Autowired
    ConvitRepository convitRepository;
    
    public List<Convit> findAll() {
        return convitRepository.findAll();
    }
    
    public Page<Convit> findAll(Pageable pageable) {
        return convitRepository.findAll(pageable);
    }
    
    public Page<Convit> findConvitsByPerson(Pageable pageable, UserPeople userPeople) {
        return convitRepository.findConvitsByPerson(pageable, userPeople);
    }    

    @Transactional
    public Convit save(Convit convit){
        return convitRepository.save(convit);
    }

    @Transactional
    public void remove(String id){
        convitRepository.deleteById(UUID.fromString(id));
    }
    
    public boolean exists(Convit convit){
        return convitRepository.exists(Example.of(convit));
    }
    
}
