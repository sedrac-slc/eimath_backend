package com.ei.math.service;

import com.ei.math.entity.Group;
import com.ei.math.entity.Member;
import com.ei.math.repository.MemberRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService{
    @Autowired
    MemberRepository groupRepository;
    
    public List<Member> findAll() {
        return groupRepository.findAll();
    }
    
    public Page<Member> findAll(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }
    
    public Page<Member> findMembersByGroup(Pageable pageable, Group group) {
        return groupRepository.findMembersByGroup(pageable, group);
    }    

    @Transactional
    public Member save(Member group){
        return groupRepository.save(group);
    }

    @Transactional
    public void remove(String id){
        groupRepository.deleteById(UUID.fromString(id));
    }
    
    public boolean exists(Member group){
        return groupRepository.exists(Example.of(group));
    }
    
}
