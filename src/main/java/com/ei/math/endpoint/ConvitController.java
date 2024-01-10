package com.ei.math.endpoint;

import com.ei.math.entity.Convit;
import com.ei.math.entity.EmailHistory;
import com.ei.math.service.ConvitService;
import com.ei.math.service.EmailHistoryService;
import com.ei.math.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/convits")
@CrossOrigin(origins = "*")
public class ConvitController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private ConvitService convitService;
    @Autowired
    private EmailHistoryService emailHistoryService; 
    
    @Value("{spring.mail.username}")
    private String emailFrom;
    
    @PostMapping
    public ResponseEntity<Convit> store(@RequestBody Convit convit){
        Optional.ofNullable(userService.findByEmail(convit.getEmail())).ifPresentOrElse(it -> {
            convit.setIs_system(Boolean.TRUE);
        }, () -> {
            emailHistoryService.sendEmailWithHtml(new EmailHistory(
             emailFrom, convit.getEmail(), "Bem vindo","Olá"
            ));
            convit.setIs_system(Boolean.FALSE);
        });
        return ResponseEntity.ok(convitService.save(convit));
    }
    
}
