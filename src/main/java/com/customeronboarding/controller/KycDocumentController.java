package com.customeronboarding.controller;

import com.customeronboarding.entity.KycDocument;
import com.customeronboarding.service.KycDocumentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kyc-documents")
public class KycDocumentController {

    @Autowired
    private KycDocumentService kycDocumentService;

    // CREATE
    @PostMapping
    public ResponseEntity<KycDocument> registerDocument(@Valid @RequestBody KycDocument document) {
        KycDocument saved = kycDocumentService.registerDocument(document);
        return ResponseEntity.ok(saved);
    }

    // READ - All
    @GetMapping
    public ResponseEntity<List<KycDocument>> getAllDocuments() {
        return ResponseEntity.ok(kycDocumentService.getAllDocuments());
    }

    // READ - By ID
    @GetMapping("/{id}")
    public ResponseEntity<KycDocument> getDocumentById(@PathVariable Long id) {
        return kycDocumentService.getDocumentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<KycDocument> updateDocument(@PathVariable Long id,
                                                      @Valid @RequestBody KycDocument updatedDoc) {
        try {
            KycDocument updated = kycDocumentService.updateDocument(id, updatedDoc);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        try {
            kycDocumentService.deleteDocument(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
