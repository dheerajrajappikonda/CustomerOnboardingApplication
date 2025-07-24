package com.customeronboarding.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class KycStatusRequestDTO {
    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "^(Pending|Verified|Rejected)$")
    private String status;

    @Size(max = 500, message = "Admin message must not exceed 500 characters")
    private String adminMessage;

    private Long verifiedById; // Optional
}
