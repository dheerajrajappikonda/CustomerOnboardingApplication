package com.customeronboarding.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CustomerResponseDTO {
    private Long customerId;
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private LocalDate dob;
    private String address;
    private String panNumber;
    private String aadhaarNumber;
    private LocalDateTime createdAt;
}
