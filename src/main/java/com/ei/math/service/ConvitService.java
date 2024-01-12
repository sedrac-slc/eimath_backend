package com.ei.math.service;

import com.ei.math.entity.Convit;
import com.ei.math.entity.Member;
import com.ei.math.entity.UserPeople;
import com.ei.math.repository.ConvitRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConvitService{
    @Autowired
    private ConvitRepository convitRepository;
    @Autowired
    private MemberService memberService;    
    
    public Page<Convit> findConvitsByPerson(Pageable pageable, UserPeople userPeople) {
        return convitRepository.findConvitsByPerson(pageable, userPeople);
    }    

    public Convit save(Convit convit){
        return convitRepository.save(convit);
    }

    public void remove(String id){
        convitRepository.deleteById(UUID.fromString(id));
    }
    
    public Member createMember(Convit convit){
        Member member = memberService.save(new Member(convit.getUserPeople(),convit.getGroup()));
        convitRepository.deleteById(convit.getId());
        return member;
    }
    
    public Optional<Convit> findOneExample(Convit convit){
        return convitRepository.findOne(Example.of(convit));
    }    
    
    public boolean exists(Convit convit){
        return convitRepository.exists(Example.of(convit));
    }
    
}
