package com.ei.math.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_group")
@Order(2)
public class Group implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String name;

    private LocalDateTime createdAt;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserPeople userPeople;
    
    @JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Member> elements;    

    public Group(String id) {
        this.id = UUID.fromString(id);
    }  
    
    public Group(String name, UserPeople userPeople) {
        this.name = name;
        this.userPeople = userPeople;
    }

    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
    }
    
}
