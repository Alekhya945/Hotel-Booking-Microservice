package com.abc.paymentservice.service;


import java.util.List;

import com.abc.paymentservice.entity.Payment;

public interface PaymentService {
 Payment makePayment(Payment payment);
     Payment getPaymentDetails(int paymentId);
     List<Payment> getAllPaymentsDetails();
 }



