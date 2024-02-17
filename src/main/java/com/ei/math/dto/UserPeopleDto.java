package com.ei.math.dto;

import com.ei.math.enums.GenderEnum;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UserPeopleDto implements Serializable{
    @NotEmpty
    @NotNull
    private String name;
    @Email 
    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String email;
    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String phone;
    @NotNull 
    @Temporal(TemporalType.DATE)
    private LocalDate birthDay;
    @NotNull
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    
    private String username;
    private String password;
}