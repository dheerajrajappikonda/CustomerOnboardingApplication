package com.customeronboarding.service;

import com.customeronboarding.dto.CustomerRequestDTO;
import com.customeronboarding.entity.Customer;
import com.customeronboarding.repository.CustomerRepository;
import com.customeronboarding.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<Customer> registerCustomer(Long userId, CustomerRequestDTO dto) {
        return userRepository.findById(userId).map(user -> {
            Customer customer = new Customer();
            customer.setUser(user);
            customer.setName(dto.getName());
            customer.setEmail(dto.getEmail());
            customer.setPhone(dto.getPhone());
            customer.setDob(java.time.LocalDate.parse(dto.getDob()));
            customer.setAddress(dto.getAddress());
            customer.setPanNumber(dto.getPanNumber());
            customer.setAadhaarNumber(dto.getAadhaarNumber());
            return customerRepository.save(customer);
        });
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> updateCustomer(Long id, CustomerRequestDTO dto) {
        return customerRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setEmail(dto.getEmail());
            existing.setPhone(dto.getPhone());
            existing.setDob(java.time.LocalDate.parse(dto.getDob()));
            existing.setAddress(dto.getAddress());
            existing.setPanNumber(dto.getPanNumber());
            existing.setAadhaarNumber(dto.getAadhaarNumber());
            return customerRepository.save(existing);
        });
    }

    @Transactional
    public boolean deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            customerRepository.flush();  // Ensure DB commit
            return true;
        }
        return false;
    }
}
