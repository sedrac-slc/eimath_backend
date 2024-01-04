package com.ei.math.entity;

import java.io.Serializable;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_historic_expression")
public class ExpressionHistory implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Email @NotEmpty @NotNull
    private String expression;
    private LocalDateTime created_at;
    private LocalDateTime updated;
    
    private UserPeople userPeolpe;
    
}
