package com.ei.math.records;

public record PasswordDto(
    String passwordNew, 
    String passwordOld, 
    String passwordConfirmNew, 
    String passwordConfirmOld
) {

}
