package com.customeronboarding.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountRequestDTO {
    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotBlank(message = "Account type is required")
    private String accountType;

    @NotBlank(message = "Account status is required")
    private String accountStatus;

    private Long createdBy;
}
