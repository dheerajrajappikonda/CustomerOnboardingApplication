package com.customeronboarding.service;

import com.customeronboarding.entity.KycDocument;
import com.customeronboarding.repository.KycDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KycDocumentService {

    @Autowired
    private KycDocumentRepository kycDocumentRepository;

    public KycDocument registerDocument(KycDocument document) {
        return kycDocumentRepository.save(document);
    }

    public List<KycDocument> getAllDocuments() {
        return kycDocumentRepository.findAll();
    }

    public Optional<KycDocument> getDocumentById(Long id) {
        return kycDocumentRepository.findById(id);
    }

    public KycDocument updateDocument(Long id, KycDocument updatedDoc) {
        return kycDocumentRepository.findById(id).map(existing -> {
            existing.setAadhaarImagePath(updatedDoc.getAadhaarImagePath());
            existing.setPanImagePath(updatedDoc.getPanImagePath());
            existing.setPhotoImagePath(updatedDoc.getPhotoImagePath());
            existing.setCustomer(updatedDoc.getCustomer());
            return kycDocumentRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Document not found with ID: " + id));
    }

    public void deleteDocument(Long id) {
        if (!kycDocumentRepository.existsById(id)) {
            throw new RuntimeException("Document not found with ID: " + id);
        }
        kycDocumentRepository.deleteById(id);
    }
}
