package com.ei.math.records;

import com.ei.math.entity.UserPeople;

public record PasswordDto(
    UserPeople user,
    String passwordNew, 
    String passwordOld, 
    String passwordConfirmNew, 
    String passwordConfirmOld
){}
