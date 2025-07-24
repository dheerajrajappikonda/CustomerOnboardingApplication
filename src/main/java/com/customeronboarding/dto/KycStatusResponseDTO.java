package com.customeronboarding.dto;

import lombok.Data;

@Data
public class KycStatusResponseDTO {
    private Long statusId;
    private Long customerId;
    private String status;
    private String adminMessage;
    private Long verifiedById;
}
