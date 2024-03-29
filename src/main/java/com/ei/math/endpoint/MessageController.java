package com.ei.math.endpoint;

import com.ei.math.entity.Group;
import com.ei.math.entity.Message;
import com.ei.math.service.MessageService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
@CrossOrigin(origins = "*")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/by-group")
    public ResponseEntity<Page<Message>> findAllPageMessage(Pageable pageable,@RequestParam String group){
        return ResponseEntity.ok(messageService.findMessagesByGroup(pageable, new Group(group)));
    }
    
    @Transactional
    @PostMapping
    public ResponseEntity<Message> store(@RequestBody Message message){
        return ResponseEntity.ok(messageService.save(message));
    }    
     
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        messageService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }    
    
}
