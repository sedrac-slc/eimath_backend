package com.ei.math.service;

import com.ei.math.entity.Group;
import com.ei.math.entity.UserPeople;
import com.ei.math.repository.GroupRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupService{
    @Autowired
    GroupRepository groupRepository;
    
    public List<Group> findAll() {
        return groupRepository.findAll();
    }
    
    public Page<Group> findAll(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }
    
    public Page<Group> findAll(Pageable pageable,UserPeople people) {
        return groupRepository.findAllByUserPeople(pageable,people);
    }
    
    @Transactional
    public Group save(Group group){
        return groupRepository.save(group);
    }

    @Transactional
    public void remove(String id){
        groupRepository.deleteById(UUID.fromString(id));
    }
    
    public boolean exists(Group group){
        return groupRepository.exists(Example.of(group));
    }
    
}
