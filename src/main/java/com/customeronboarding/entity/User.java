package com.customeronboarding.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank(message = "Username is required")
    @Size(max = 100, message = "Username must be at most 100 characters")
    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @NotBlank(message = "Password hash is required")
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @NotBlank(message = "Role is required")
    @Pattern(regexp = "ADMIN|CUSTOMER", message = "Role must be either ADMIN or CUSTOMER")
    @Column(name = "role", nullable = false, length = 20)
    private String role;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = false)
    @JsonBackReference
    private com.customeronboarding.entity.Customer customer;

    public User() {
        // No-args constructor
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public com.customeronboarding.entity.Customer getCustomer() { return customer; }
    public void setCustomer(com.customeronboarding.entity.Customer customer) { this.customer = customer; }
}
