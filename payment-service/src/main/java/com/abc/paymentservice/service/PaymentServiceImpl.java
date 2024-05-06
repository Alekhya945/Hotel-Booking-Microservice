package com.abc.paymentservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.paymentservice.entity.Payment;
import com.abc.paymentservice.model.BookingDetails;
import com.abc.paymentservice.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    @Autowired
    private BookingDetailsService bookingService;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment makePayment(Payment payment) {
    	BookingDetails booking=bookingService.getBookingDetailsById(payment.getBookingId());
    	if(payment.getPaymentAmount() == booking.getAmount()){
    		Payment processPayment=paymentRepository.save(payment);
    		return processPayment;
    	}else{
    		throw new RuntimeException("payment Fail");
    	}
       
    }

    @Override
    public Payment getPaymentDetails(int paymentId) {
        Optional<Payment> paymentOptional = paymentRepository.findById(paymentId);
        return paymentOptional.orElse(null);
    }

    @Override
    public List<Payment> getAllPaymentsDetails() {
        return paymentRepository.findAll();
    }
}
