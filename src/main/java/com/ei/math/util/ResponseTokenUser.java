package com.ei.math.util;

import com.ei.math.entity.UserPeople;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTokenUser implements Serializable{
    private String token;
    private UserPeople person;
    private boolean exist = false;

    public ResponseTokenUser(String token, UserPeople person) {
        this.token = token;
        this.person = person;
        this.exist = true;
    }
    
    
    
}
