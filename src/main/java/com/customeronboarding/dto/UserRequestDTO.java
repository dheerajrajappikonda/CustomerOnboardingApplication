package com.customeronboarding.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "Username is required")
    @Size(max = 100, message = "Username must be at most 100 characters")
    private String username;

    @NotBlank(message = "Password hash is required")
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
    private String passwordHash;

    @NotBlank(message = "Role is required")
    @Pattern(regexp = "ADMIN|CUSTOMER", message = "Role must be either ADMIN or CUSTOMER")
    private String role;
}
