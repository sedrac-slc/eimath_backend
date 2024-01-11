package com.ei.math.entity;

import com.ei.math.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * @author Sedrac Lucas Calupeteca
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_users")
public class UserPeople implements UserDetails, Serializable{
        
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
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
    private LocalDate birthDay;
    @NotNull
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    
    private String username;
    private String password;
    
    @Column(nullable = true)
    private String image;

    public UserPeople(String userId) {
       this.id = UUID.fromString(userId);
    }
    
    public UserPeople(UUID userId) {
       this.id = userId;
    }    

    public UserPeople(String name, String email, String phone, LocalDate birthDay, GenderEnum gender, String username, String password) {
        this(null, name, email, phone, birthDay, gender, username, password, null, null, null,null);
    }    
    
    @ManyToMany
    @JoinTable(
        name = "tb_roles_users",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
    
    @JsonIgnore
    @OneToMany(mappedBy = "userPeople")
    private List<Group> groups;
    
    @JsonIgnore
    @OneToMany(mappedBy = "userPeople")
    private List<Member> elements;    


    @PrePersist
    public void passwordBcrypt(){
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return roles;
    }

    @Override
    public String getPassword() {
       return password;
    }

    @Override
    public String getUsername() {
       return username;
    }

    @Override
    public boolean isAccountNonExpired() {
       return true;
    }

    @Override
    public boolean isAccountNonLocked() {
      return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
       return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }  
    
}
