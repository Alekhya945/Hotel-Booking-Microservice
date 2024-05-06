package com.abc.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abc.paymentservice.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
 
}
