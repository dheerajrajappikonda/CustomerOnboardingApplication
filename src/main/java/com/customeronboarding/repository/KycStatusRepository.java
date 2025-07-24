package com.customeronboarding.repository;

import com.customeronboarding.entity.KycStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KycStatusRepository extends JpaRepository<KycStatus, Long> {
}
