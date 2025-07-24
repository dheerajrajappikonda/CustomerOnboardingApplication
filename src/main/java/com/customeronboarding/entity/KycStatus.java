package com.customeronboarding.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "KYC_STATUS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KycStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STATUS_ID")
    private Long statusId;

    @NotNull(message = "Customer cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    @JsonBackReference(value = "customer-kycStatuses")
    private Customer customer;

    @NotBlank(message = "Status cannot be blank")
    @Pattern(
            regexp = "^(Pending|Verified|Rejected)$",
            message = "Status must be either Pending, Verified, or Rejected"
    )
    @Column(name = "STATUS", nullable = false)
    private String status;

    @Size(max = 500, message = "Admin message can be up to 500 characters")
    @Column(name = "ADMIN_MESSAGE")
    private String adminMessage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VERIFIED_BY")
    @JsonBackReference(value = "user-verifiedStatuses")
    private User verifiedBy;
}
