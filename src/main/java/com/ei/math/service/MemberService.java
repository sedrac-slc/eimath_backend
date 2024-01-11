package com.ei.math.service;

import com.ei.math.entity.Group;
import com.ei.math.entity.Member;
import com.ei.math.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
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
    MemberRepository memberRepository;
    
    public Page<Member> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }
    
    public Page<Member> findMembersByGroup(Pageable pageable, Group group) {
        return memberRepository.findMembersByGroup(pageable, group);
    }    

    @Transactional
    public Member save(Member group){
        return memberRepository.save(group);
    }

    @Transactional
    public void remove(String id){
        memberRepository.deleteById(UUID.fromString(id));
    }

    @Transactional
    public void remove(UUID id){
        memberRepository.deleteById(id);
    }    
    
    public Optional<Member> findOneExample(Member member){
        return memberRepository.findOne(Example.of(member));
    }
    
    public boolean exists(Member member){
        return memberRepository.exists(Example.of(member));
    }
    
}
