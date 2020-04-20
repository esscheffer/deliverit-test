package com.example.erikscheffer.deliverittestbackend.repository;

import com.example.erikscheffer.deliverittestbackend.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
