package com.ei.math.endpoint;

import com.ei.math.entity.Group;
import com.ei.math.entity.Member;
import com.ei.math.entity.UserPeople;
import com.ei.math.service.GroupService;
import com.ei.math.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups")
@CrossOrigin(origins = "*")
public class GroupController {
    
    @Autowired
    private GroupService groupService;
    @Autowired
    private MemberService memberService;
    
    @GetMapping("/page")
    public ResponseEntity<Page<Group>> findAllPage(Pageable pageable,@RequestParam String people){
        return ResponseEntity.ok(groupService.findAll(pageable,new UserPeople(people)));
    }
    
    @GetMapping("/page/members")
    public ResponseEntity<Page<Group>> findAllPageMember(Pageable pageable,@RequestParam String people){
        return ResponseEntity.ok(groupService.findAllMember(pageable, new UserPeople(people)));
    }    
    
    @Transactional
    @PostMapping
    public ResponseEntity<Group> store(@RequestBody Group group){
        return ResponseEntity.ok(groupService.save(group));
    }
    
    @Transactional
    @PutMapping
    public ResponseEntity<Group> update(@RequestBody Group group){
        groupService.save(group);
        return ResponseEntity.ok(group);
    }    
    
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        groupService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @Transactional
    @PostMapping("/by-member")
    public ResponseEntity<Void> deleteMember(@RequestBody Member member){
        memberService.findOneExample(member).ifPresent( it->{
            memberService.remove(it.getId());
        });
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }    
    
}
