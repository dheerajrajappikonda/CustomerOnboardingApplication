package com.customeronboarding.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "KYC_DOCUMENTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KycDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOC_ID")
    private Long docId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    @JsonBackReference(value = "customer-kycDocuments")
    private Customer customer;

    @Column(name = "AADHAAR_IMAGE_PATH")
    @Size(max = 255, message = "Aadhaar image path must not exceed 255 characters")
    private String aadhaarImagePath;

    @Column(name = "PAN_IMAGE_PATH")
    @Size(max = 255, message = "PAN image path must not exceed 255 characters")
    private String panImagePath;

    @Column(name = "PHOTO_IMAGE_PATH")
    @Size(max = 255, message = "Photo image path must not exceed 255 characters")
    private String photoImagePath;

    @Column(name = "UPLOADED_AT")
    private LocalDateTime uploadedAt = LocalDateTime.now();
}
