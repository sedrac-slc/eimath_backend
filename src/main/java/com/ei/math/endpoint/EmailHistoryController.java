package com.ei.math.endpoint;

import com.ei.math.dto.EmailHistoryDto;
import com.ei.math.entity.EmailHistory;
import com.ei.math.service.EmailHistoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/emails")
@CrossOrigin(origins = "*")
public class EmailHistoryController {

    @Autowired
    EmailHistoryService emailService;
    
    @PostMapping
    public ResponseEntity<EmailHistory> sendingEmail(@RequestBody @Valid EmailHistoryDto emailDto) {
        EmailHistory emailModel = new EmailHistory();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendEmailWithHtml(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<EmailHistory>> getAllEmails(@PageableDefault(page = 0, size = 5, sort = "emailId", direction = Sort.Direction.DESC) Pageable pageable){
        return new ResponseEntity<>(emailService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{emailId}")
    public ResponseEntity<Object> getOneEmail(@PathVariable(value="emailId") UUID emailId){
        Optional<EmailHistory> emailModelOptional = emailService.findById(emailId);
        if(!emailModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(emailModelOptional.get());
        }
    }
}