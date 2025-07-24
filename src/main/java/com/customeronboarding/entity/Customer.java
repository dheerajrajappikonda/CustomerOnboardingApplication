package com.customeronboarding.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CUSTOMERS")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    // If a customer is deleted, we don't want to delete the User (shared account),
    // so remove cascade here to avoid accidental user deletion
    @OneToOne
    @JoinColumn(name = "USER_ID", nullable = false, unique = true)
    @JsonManagedReference
    private User user;

    @Column(name = "FULL_NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "DOB", nullable = false)
    private LocalDate dob;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "PAN_NUMBER", nullable = false)
    private String panNumber;

    @Column(name = "AADHAAR_NUMBER", nullable = false)
    private String aadhaarNumber;

    @CreationTimestamp
    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    // Added orphanRemoval so accounts are deleted when a customer is deleted
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Account> accounts;

    public Long getUserId() {
        return user != null ? user.getUserId() : null;
    }
}
