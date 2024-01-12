package com.ei.math.endpoint;

import com.ei.math.entity.Convit;
import com.ei.math.entity.EmailHistory;
import com.ei.math.entity.Member;
import com.ei.math.entity.UserPeople;
import com.ei.math.service.ConvitService;
import com.ei.math.service.EmailHistoryService;
import com.ei.math.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @GetMapping("/by-people")
    public ResponseEntity<Page<Convit>> findConvitsByPerson(Pageable pageable,@RequestParam String people){
        return ResponseEntity.ok(convitService.findConvitsByPerson(pageable, new UserPeople(people)));
    }    
    
    @PostMapping
    @Transactional
    public ResponseEntity<Convit> store(@RequestBody Convit convit){
        Convit convitNew = convit;
        convitService.findOneExample(convit).ifPresentOrElse(it->{ }, ()->{
                Optional.ofNullable(userService.findByEmail(convit.getEmail())).orElseThrow(RuntimeException::new);
                convit.setIs_system(Boolean.TRUE);
                Convit find = convitService.save(convit);
                convitNew.setId(find.getId());
                emailHistoryService.sendEmailWithHtml(
                    new EmailHistory(emailFrom, convit.getEmail(), "Grupo de estudo", messageGmailUserIN(convitNew))
                );
            }
        );
        return ResponseEntity.ok(convitNew);
    }
    
    @Transactional
    @PostMapping("/accept")
    public ResponseEntity<Member> accept(@RequestBody Convit convit){
        return ResponseEntity.ok(convitService.createMember(convit));
    }    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        convitService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }    
    
    private String messageGmailUserIN(Convit convit){
        return "<div style='text-align:center;'>"
                + "<h1>Convite para participa no grupo de estudo: "+convit.getGroup().getName()+"</h1>"
                + "<p><img width='80' height='80' src='https://pics.freeicons.io/uploads/icons/png/1842265661548336220-512.png' alt='envelope image'/></p>";
    }    
    
}
