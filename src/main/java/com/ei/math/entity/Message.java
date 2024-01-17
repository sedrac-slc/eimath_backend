package com.ei.math.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_messagens")
public class Message implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserPeople userPeople;
    
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;  

    private String text;
    
    private LocalDateTime createdAt;

    public Message(UserPeople userPeople, Group group, String text) {
        this.userPeople = userPeople;
        this.group = group;
        this.text = text;
        this.createdAt = LocalDateTime.now();
    }
    
    
    
}
