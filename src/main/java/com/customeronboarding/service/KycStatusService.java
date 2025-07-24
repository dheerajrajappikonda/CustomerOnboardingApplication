package com.customeronboarding.service;

import com.customeronboarding.entity.Customer;
import com.customeronboarding.entity.KycStatus;
import com.customeronboarding.entity.User;
import com.customeronboarding.repository.CustomerRepository;
import com.customeronboarding.repository.KycStatusRepository;
import com.customeronboarding.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KycStatusService {

    private final KycStatusRepository kycStatusRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public KycStatusService(KycStatusRepository kycStatusRepository,
                            CustomerRepository customerRepository,
                            UserRepository userRepository) {
        this.kycStatusRepository = kycStatusRepository;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    // CREATE
    public KycStatus saveKycStatus(KycStatus kycStatus) {
        if (kycStatus.getCustomer() != null && kycStatus.getCustomer().getCustomerId() != null) {
            Optional<Customer> customer = customerRepository.findById(kycStatus.getCustomer().getCustomerId());
            customer.ifPresent(kycStatus::setCustomer);
        }

        if (kycStatus.getVerifiedBy() != null && kycStatus.getVerifiedBy().getUserId() != null) {
            Optional<User> user = userRepository.findById(kycStatus.getVerifiedBy().getUserId());
            user.ifPresent(kycStatus::setVerifiedBy);
        }

        return kycStatusRepository.save(kycStatus);
    }

    // READ ALL
    public List<KycStatus> getAllStatuses() {
        return kycStatusRepository.findAll();
    }

    // READ BY ID
    public Optional<KycStatus> getStatusById(Long id) {
        return kycStatusRepository.findById(id);
    }

    // UPDATE
    public Optional<KycStatus> updateStatus(Long id, KycStatus updatedStatus) {
        return kycStatusRepository.findById(id).map(existingStatus -> {
            existingStatus.setStatus(updatedStatus.getStatus());
            existingStatus.setAdminMessage(updatedStatus.getAdminMessage());

            if (updatedStatus.getCustomer() != null && updatedStatus.getCustomer().getCustomerId() != null) {
                customerRepository.findById(updatedStatus.getCustomer().getCustomerId())
                        .ifPresent(existingStatus::setCustomer);
            }

            if (updatedStatus.getVerifiedBy() != null && updatedStatus.getVerifiedBy().getUserId() != null) {
                userRepository.findById(updatedStatus.getVerifiedBy().getUserId())
                        .ifPresent(existingStatus::setVerifiedBy);
            }

            return kycStatusRepository.save(existingStatus);
        });
    }

    // DELETE
    public void deleteStatus(Long id) {
        kycStatusRepository.deleteById(id);
    }
}
