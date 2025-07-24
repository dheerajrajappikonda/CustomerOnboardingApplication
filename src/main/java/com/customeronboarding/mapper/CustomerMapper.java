package com.customeronboarding.mapper;

import com.customeronboarding.dto.CustomerRequestDTO;
import com.customeronboarding.dto.CustomerResponseDTO;
import com.customeronboarding.entity.Customer;
import com.customeronboarding.entity.User;
import java.time.LocalDate;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequestDTO dto, User user) {
        Customer customer = new Customer();
        customer.setUser(user);
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setDob(LocalDate.parse(dto.getDob()));
        customer.setAddress(dto.getAddress());
        customer.setPanNumber(dto.getPanNumber());
        customer.setAadhaarNumber(dto.getAadhaarNumber());
        return customer;
    }

    public static CustomerResponseDTO toDTO(Customer customer) {
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setCustomerId(customer.getCustomerId());
        dto.setUserId(customer.getUser().getUserId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        dto.setDob(customer.getDob());
        dto.setAddress(customer.getAddress());
        dto.setPanNumber(customer.getPanNumber());
        dto.setAadhaarNumber(customer.getAadhaarNumber());
        dto.setCreatedAt(customer.getCreatedAt());
        return dto;
    }
}
