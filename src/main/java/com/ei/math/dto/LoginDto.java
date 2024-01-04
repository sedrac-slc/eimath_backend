package com.ei.math.dto;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
public class LoginDto {
    @NotEmpty
    @NotNull
    private String username;
    @NotEmpty
    @NotNull
    private String password;
}