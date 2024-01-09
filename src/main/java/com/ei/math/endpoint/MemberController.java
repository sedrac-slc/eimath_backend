package com.ei.math.endpoint;

import com.ei.math.entity.Group;
import com.ei.math.entity.Member;
import com.ei.math.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@CrossOrigin(origins = "*")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/by-group")
    public ResponseEntity<Page<Member>> findAllPageMember(Pageable pageable,@RequestParam String group){
        return ResponseEntity.ok(memberService.findMembersByGroup(pageable, new Group(group)));
    }
     
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        memberService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }    
    
}
