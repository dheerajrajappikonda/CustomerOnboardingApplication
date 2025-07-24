package com.customeronboarding.controller;

import com.customeronboarding.dto.CustomerRequestDTO;
import com.customeronboarding.dto.CustomerResponseDTO;
import com.customeronboarding.entity.Customer;
import com.customeronboarding.mapper.CustomerMapper;
import com.customeronboarding.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // ✅ Register new customer (userId now taken from JSON body)
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> registerCustomer(@Valid @RequestBody CustomerRequestDTO requestDTO) {
        Optional<Customer> created = customerService.registerCustomer(requestDTO.getUserId(), requestDTO);
        return created
                .map(customer -> ResponseEntity.ok(CustomerMapper.toDTO(customer)))
                .orElse(ResponseEntity.badRequest().build());
    }

    // ✅ Get all customers
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerResponseDTO> dtos = customers.stream()
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // ✅ Get single customer
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.getCustomerById(id);
        return customerOptional
                .map(customer -> ResponseEntity.ok(CustomerMapper.toDTO(customer)))
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Update Customer
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable Long id,
                                                              @Valid @RequestBody CustomerRequestDTO requestDTO) {
        Optional<Customer> updated = customerService.updateCustomer(id, requestDTO);
        return updated
                .map(customer -> ResponseEntity.ok(CustomerMapper.toDTO(customer)))
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        boolean deleted = customerService.deleteCustomer(id);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
