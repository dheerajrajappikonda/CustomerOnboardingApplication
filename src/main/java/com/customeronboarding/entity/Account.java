package com.customeronboarding.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ACCOUNTS")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    @NotNull(message = "Customer is required")
    @JsonBackReference
    private Customer customer;

    @Column(name = "ACCOUNT_TYPE", nullable = false, length = 50)
    @NotBlank(message = "Account type is required")
    @Size(max = 50, message = "Account type must not exceed 50 characters")
    private String accountType;

    @Column(name = "ACCOUNT_STATUS", nullable = false, length = 20)
    @NotBlank(message = "Account status is required")
    @Size(max = 20, message = "Account status must not exceed 20 characters")
    private String accountStatus;

    @CreationTimestamp
    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "CREATED_BY")
    private Long createdBy;
}
