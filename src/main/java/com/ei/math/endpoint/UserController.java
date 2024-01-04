package com.ei.math.endpoint;

import com.ei.math.entity.Group;
import com.ei.math.entity.UserPeople;
import com.ei.math.service.UserService;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public  class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserPeople>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
    
    @GetMapping("/page")
    public ResponseEntity<Page<UserPeople>> findAll(Pageable pageable){
        return ResponseEntity.ok(userService.findAll(pageable));
    }
    
    @GetMapping("/groups/{userId}")
    public ResponseEntity<List<Group>> groupUser(@PathVariable String userId){
        UserPeople user = userService.findById(userId)
                                     .orElseThrow(()-> new RuntimeException());
        PageRequest.of(0, user.getGroups().size());
        return ResponseEntity.ok(user.getGroups());
    }
    
    @GetMapping("/username/{username}")
    public ResponseEntity<UserPeople> findByUsername(@PathVariable String username){
        UserPeople findByUsername = userService.findByUsername(username);
        findByUsername.setPassword("");
        return ResponseEntity.ok(findByUsername);
    }
    
    @PostMapping
    public ResponseEntity<UserPeople> save(@Valid @RequestBody UserPeople user){
        return ResponseEntity.ok(userService.save(user));
    }
    
}
