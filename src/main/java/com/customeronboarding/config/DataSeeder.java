package com.customeronboarding.config;

import com.customeronboarding.entity.*;
import com.customeronboarding.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedData(
            UserRepository userRepo,
            CustomerRepository customerRepo,
            AccountRepository accountRepo,
            KycDocumentRepository kycDocRepo,
            KycStatusRepository kycStatusRepo
    ) {
        return args -> {
            // Check if user already exists
            Optional<User> existingUser = userRepo.findByUsername("john_admin");

            if (existingUser.isPresent()) {
                System.out.println("Seed data already exists. Skipping insertion.");
                return;
            }

            // 1. Create User
            User user = new User();
            user.setUsername("john_admin");
            user.setPasswordHash("securePassword");
            user.setRole("ADMIN");
            userRepo.save(user);

            // 2. Create Customer
            Customer customer = new Customer();
            customer.setUser(user);  // FK mapping
            customer.setName("John Doe");
            customer.setEmail("john.doe@example.com");
            customer.setPhone("9876543210");
            customer.setDob(LocalDate.of(1990, 5, 10));
            customer.setAddress("221B Baker Street");
            customer.setPanNumber("ABCDE1234F");
            customer.setAadhaarNumber("123412341234");
            customer.setCreatedAt(LocalDateTime.now());
            customerRepo.save(customer);

            // 3. Create Account
            Account account = new Account();
            account.setCustomer(customer);
            account.setAccountType("SAVINGS");
            account.setAccountStatus("ACTIVE");
            account.setCreatedAt(LocalDateTime.now());
            account.setCreatedBy(user.getUserId());
            accountRepo.save(account);

            // 4. Create KYC Document
            KycDocument document = new KycDocument();
            document.setCustomer(customer);
            document.setAadhaarImagePath("/images/aadhaar.jpg");
            document.setPanImagePath("/images/pan.jpg");
            document.setPhotoImagePath("/images/photo.jpg");
            document.setUploadedAt(LocalDateTime.now());
            kycDocRepo.save(document);

            // 5. Create KYC Status
            KycStatus status = new KycStatus();
            status.setCustomer(customer);
            status.setStatus("PENDING");
            status.setAdminMessage("Initial review pending");
            status.setVerifiedBy(user); // Only if this mapping is defined
            kycStatusRepo.save(status);
        };
    }
}
