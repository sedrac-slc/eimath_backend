package com.ei.math.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_members")
public class Member implements Serializable{
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;    
   
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserPeople userPeople;
    
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;  
    
    private LocalDateTime createdAt;
    
    public Member(UserPeople userPeople, Group group) {
        this.userPeople = userPeople;
        this.group = group;
    }
    
    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
    }
    
}
