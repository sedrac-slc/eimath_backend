package com.ei.math.endpoint;

import com.ei.math.entity.Convit;
import com.ei.math.entity.EmailHistory;
import com.ei.math.service.ConvitService;
import com.ei.math.service.EmailHistoryService;
import com.ei.math.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public ResponseEntity<Convit> store(@RequestBody Convit convit){
        Convit convitNew = convit;
        Optional.ofNullable(userService.findByEmail(convit.getEmail())).ifPresentOrElse(it -> {
            convit.setIs_system(Boolean.TRUE);
            convitNew.setId(convitService.save(convit).getId());
            emailHistoryService.sendEmailWithHtml(new EmailHistory(
             emailFrom, convit.getEmail(), "Grupo de estudo", messageGmailUserIN(convitNew)
            ));
        }, () -> {
            convit.setIs_system(Boolean.FALSE);
            convitNew.setId(convitService.save(convit).getId());
            emailHistoryService.sendEmailWithHtml(new EmailHistory(
             emailFrom, convit.getEmail(), "Bem vindo", messageGmailUserOut(convitNew)
            ));
        });
        return ResponseEntity.ok(convitNew);
    }
    /*
    private String urlDomain(HttpServletRequest request) {
        return STR."\{request.getScheme()}://\{request.getServerName()}:\{request.getServerPort()}";
    }    
    */
    private String messageGmailUserOut(Convit convit){
        String url = "http://localhost:4200/register?convit='"+convit.getId()+"'";
        return STR."<div style='text-align:center;'>"
                + "<h1>Convite para participa no grupo de estudo : "+convit.getGroup().getName()+"</h1>"
                + "<p><img width='80' height='80' src='https://pics.freeicons.io/uploads/icons/png/1842265661548336220-512.png' alt='envelope image'/></p>"
                + "<p style='margin:0.3rem;'>Seja bem vindo a plantaforma Eimath com ferramentas de resolução de problema de matemática, iremos ajudar você a estudar matemática e podes participar ou gerenciar grupos de estudo</p>"
                + "<a style='margin:2rem 1rem;padding:0.5rem;color:black;background:rgb(255,245,235);border-radius:0.5rem;' href='"+url+"'>Fazer Cadastramentro</a>"
                + "</div>";
    }
    
    private String messageGmailUserIN(Convit convit){
        return STR."<div style='text-align:center;'>"
                + "<h1>Convite para participa no grupo de estudo: "+convit.getGroup().getName()+"</h1>"
                + "<p><img width='80' height='80' src='https://pics.freeicons.io/uploads/icons/png/1842265661548336220-512.png' alt='envelope image'/></p>";
    }    
    
}
