package com.ei.math.service;

import com.ei.math.entity.UserPeople;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ei.math.repository.UserRepository;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService{
    @Autowired
    UserRepository userRepository;
    
    public List<UserPeople> findAll() {
        return userRepository.findAll();
    }
    
    public Page<UserPeople> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    
    @Transactional
    public UserPeople save(UserPeople user){
        return userRepository.save(user);
    }

    public UserPeople findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    
    public Optional<UserPeople> findById(String id){
        return userRepository.findById(UUID.fromString(id));
    }    
    
}
