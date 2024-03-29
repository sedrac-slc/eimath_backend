package com.ei.math.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
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
@Table(name = "tb_convits")
public class Convit implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)   
    private UUID id;
    
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserPeople userPeople;
  
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;  
    
    private Boolean is_system;    
    
    private LocalDateTime createdAt;

    public Convit(UserPeople userPeople, Group group) {
        this(null,userPeople.getEmail(),userPeople,group, true, LocalDateTime.now());
    }

    public Convit(String email, UserPeople userPeople, Group group) {
        this(null,email,userPeople,group, true, LocalDateTime.now());
    }
    
    public Convit(String email, UserPeople userPeople, Group group, Boolean is_system) {
        this(email,userPeople,group);
        this.is_system = is_system;
    }


    
    
    
}
