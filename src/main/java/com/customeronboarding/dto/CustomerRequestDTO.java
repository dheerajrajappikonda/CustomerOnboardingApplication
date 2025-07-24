package com.customeronboarding.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDTO {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Phone is mandatory")
    private String phone;

    @NotBlank(message = "Date of Birth is mandatory")
    private String dob;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "PAN number is mandatory")
    private String panNumber;

    @NotBlank(message = "Aadhaar number is mandatory")
    private String aadhaarNumber;
}
