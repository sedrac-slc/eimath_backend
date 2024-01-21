package com.ei.math.service;

import com.ei.math.entity.Group;
import com.ei.math.entity.UserPeople;
import com.ei.math.repository.GroupRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GroupService{
    @Autowired
    GroupRepository groupRepository;
    
    public Optional<Group> findById(String id){
        return groupRepository.findById(UUID.fromString(id));
    }
    
    public List<Group> findAll() {
        return groupRepository.findAll();
    }
    
    public Page<Group> findAll(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }
    
    public Page<Group> findAll(Pageable pageable,UserPeople people) {
        return groupRepository.findAllByUserPeople(pageable,people);
    }
    
    public Page<Group> findAllMember(Pageable pageable,UserPeople people) {
        return groupRepository.findGroupsByPerson(pageable,people);
    }    
    
    public Group save(Group group){
        return groupRepository.save(group);
    }

    public void remove(String id){
        groupRepository.deleteById(UUID.fromString(id));
    }
    
    public boolean exists(Group group){
        return groupRepository.exists(Example.of(group));
    }
    
}
