package com.ei.math.service;

import com.ei.math.entity.EmailHistory;
import com.ei.math.enums.StatusEmailEnum;
import com.ei.math.repository.EmailHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class EmailHistoryService {

    @Autowired
    EmailHistoryRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Transactional
    public EmailHistory sendEmail(EmailHistory emailModel) {
        emailModel.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);
            emailModel.setStatusEmail(StatusEmailEnum.SENT);
        } catch (MailException e){
            emailModel.setStatusEmail(StatusEmailEnum.ERROR);
        } finally {
            return emailRepository.save(emailModel);
        }
    }
    
    @Transactional
    public EmailHistory sendEmailWithHtml(EmailHistory emailModel) {
        emailModel.setSendDateEmail(LocalDateTime.now());
        try{
            MimeMessageHelper message = new MimeMessageHelper(emailSender.createMimeMessage());
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText(), true);
            emailSender.send(message.getMimeMessage());
            emailModel.setStatusEmail(StatusEmailEnum.SENT);
        } catch (MailException e){
            emailModel.setStatusEmail(StatusEmailEnum.ERROR);
        } finally {
            return emailRepository.save(emailModel);
        }
    }  

    public Page<EmailHistory> findAll(Pageable pageable) {
        return  emailRepository.findAll(pageable);
    }

    public Optional<EmailHistory> findById(UUID emailId) {
        return emailRepository.findById(emailId);
    }
}