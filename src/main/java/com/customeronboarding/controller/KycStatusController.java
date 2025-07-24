package com.customeronboarding.controller;

import com.customeronboarding.entity.KycStatus;
import com.customeronboarding.service.KycStatusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/kyc-status")
public class KycStatusController {

    @Autowired
    private KycStatusService kycStatusService;

    // CREATE
    @PostMapping
    public ResponseEntity<KycStatus> saveKycStatus(@Valid @RequestBody KycStatus kycStatus) {
        KycStatus saved = kycStatusService.saveKycStatus(kycStatus);
        return ResponseEntity.ok(saved);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<KycStatus>> getAllStatuses() {
        return ResponseEntity.ok(kycStatusService.getAllStatuses());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<KycStatus> getStatusById(@PathVariable Long id) {
        Optional<KycStatus> status = kycStatusService.getStatusById(id);
        return status.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<KycStatus> updateStatus(@PathVariable Long id, @Valid @RequestBody KycStatus updatedStatus) {
        Optional<KycStatus> status = kycStatusService.updateStatus(id, updatedStatus);
        return status.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable Long id) {
        kycStatusService.deleteStatus(id);
        return ResponseEntity.noContent().build();
    }
}
