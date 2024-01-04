package com.ei.math.endpoint;

import com.ei.math.dto.LoginDto;
import com.ei.math.dto.UserPeopleDto;
import com.ei.math.entity.UserPeople;
import com.ei.math.jwt.TokenUtils;
import com.ei.math.repository.UserRepository;
import com.ei.math.service.EmailHistoryService;
import com.ei.math.util.ResponseTokenUser;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorization")
@CrossOrigin(origins = "*")
public class AuthorizationController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmailHistoryService emailService;

     @PostMapping("/login")
    public ResponseEntity<ResponseTokenUser> login(@RequestBody @Valid LoginDto people){
        try{
            UserPeople person = userRepository.findByUsername(people.getUsername());
            BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
            if(!bCrypt.matches(people.getPassword(), person.getPassword())) 
                throw new Exception("Password invalid");
            String token = TokenUtils.createToken(person.getUsername(), person.getEmail());
            return ResponseEntity.ok(new ResponseTokenUser(token, person));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(new ResponseTokenUser());
        }
    }   
    
    @PostMapping("/register")
    public ResponseEntity<ResponseTokenUser> create(@RequestBody @Valid UserPeopleDto people){
        try{
            UserPeople userPeople = new UserPeople();
            BeanUtils.copyProperties(people, userPeople);
            UserPeople person = userRepository.save(userPeople);
            String token = TokenUtils.createToken(person.getUsername(), person.getEmail());
            return ResponseEntity.ok(new ResponseTokenUser(token, person));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(new ResponseTokenUser());
        }
    }
    
}
