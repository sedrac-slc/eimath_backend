package com.ei.math.endpoint;

import com.ei.math.config.FileStorageProperties;
import com.ei.math.entity.UserPeople;
import com.ei.math.records.PasswordDto;
import com.ei.math.service.UserService;
import jakarta.validation.Valid;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public  class UserController {
    @Autowired
    UserService userService;
    
    private final Path fileStorageLocation;

    public UserController(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
    }    

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
    public ResponseEntity<?> passowdUpadte(@Valid @RequestBody PasswordDto passowrd){
        try{
            if(!passowrd.passwordOld().equals(passowrd.passwordConfirmOld())) throw new RuntimeException("Palavra passes antigas são diferentes");
            if(!passowrd.passwordNew().equals(passowrd.passwordConfirmNew())) throw new RuntimeException("Palavra passes novas são diferentes");
            UserPeople user = passowrd.user();
            user.setPassword(new BCryptPasswordEncoder().encode(passowrd.passwordNew()));
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update-photo")
    @Transactional
    public ResponseEntity<UserPeople> passowdImage(@RequestParam("file") MultipartFile file, @RequestParam String user){
        try{
            UserPeople person = userService.findById(user).orElseThrow(RuntimeException::new);
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path targetLocation = fileStorageLocation.resolve(fileName);
            file.transferTo(targetLocation);
            
            String fileUrlDownload = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/pub/file/image/")
                    .path(fileName)
                    .toUriString();
            
            person.setImage(fileUrlDownload);
            UserPeople people = userService.save(person);
            
            return ResponseEntity.ok(people);
        }catch(IOException e){
            return ResponseEntity.badRequest().build();
        }
    }    
    
}
