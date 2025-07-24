package com.customeronboarding.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountResponseDTO {
    private Long accountId;
    private Long customerId;
    private String accountType;
    private String accountStatus;
    private LocalDateTime createdAt;
    private Long createdBy;
}
