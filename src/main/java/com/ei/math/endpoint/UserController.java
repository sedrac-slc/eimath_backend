package com.ei.math.endpoint;

import com.ei.math.entity.UserPeople;
import com.ei.math.records.PasswordDto;
import com.ei.math.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public  class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/username/{username}")
    public ResponseEntity<UserPeople> findByUsername(@PathVariable String username){
        UserPeople findByUsername = userService.findByUsername(username);
        findByUsername.setPassword("");
        return ResponseEntity.ok(findByUsername);
    }
    
    @PostMapping
    @Transactional
    public ResponseEntity<UserPeople> save(@Valid @RequestBody UserPeople user){
        return ResponseEntity.ok(userService.save(user));
    }
    
    @PutMapping
    @Transactional
    public ResponseEntity<UserPeople> update(@Valid @RequestBody UserPeople user){
        return ResponseEntity.ok(userService.save(user));
    }    
    
    
    @PutMapping("/password-update")
    @Transactional
    public ResponseEntity<Void> passowdUpadte(@Valid @RequestBody PasswordDto passowrd){
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }     
}
