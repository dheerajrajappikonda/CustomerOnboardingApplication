package com.customeronboarding.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class KycDocumentDTO {
    private Long docId;
    private Long customerId;
    private String aadhaarImagePath;
    private String panImagePath;
    private String photoImagePath;
    private LocalDateTime uploadedAt;
}
