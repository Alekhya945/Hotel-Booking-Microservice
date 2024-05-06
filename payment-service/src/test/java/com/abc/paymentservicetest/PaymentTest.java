package com.abc.paymentservicetest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.abc.paymentservice.PaymentServiceApplication;
import com.abc.paymentservice.entity.Payment;
import com.abc.paymentservice.model.BookingDetails;
import com.abc.paymentservice.repository.PaymentRepository;
import com.abc.paymentservice.service.BookingDetailsService;
import com.abc.paymentservice.service.PaymentServiceImpl;


@SpringBootTest(classes = PaymentServiceApplication.class)
	public class PaymentTest {
		 @Mock
		    private PaymentRepository paymentRepository;

		    @Mock
		    private BookingDetailsService bookingService;

		    @InjectMocks
		    private PaymentServiceImpl paymentService;
		    @Test
		    public void testMakePayment_Successful() {
		        // Mocking
		        Payment payment = new Payment();
		        BookingDetails booking=new BookingDetails();
		        booking.setBookingID(123); // Assuming booking ID
		        payment.setAmount("100"); // Assuming payment amount
		        when(bookingService.getBookingDetailsById(123)).thenReturn(booking);

		        // Test
		        Payment savedPayment = paymentService.makePayment(payment);

		        // Verification
		        verify(paymentRepository).save(payment);
		        // Add more verifications if necessary
		    }
		  
		   @Test
		   
		    public void testMakePayment_Failure() {
		        // Mocking
		        Payment payment = new Payment();
		        payment.setBookingId(123); // Assuming booking ID
		        payment.setAmount("100"); // Assuming payment amount
		        when(bookingService.getBookingDetailsById(123)).thenReturn(Optional.of(BookingDetails));

		        // Test
		        paymentService.makePayment(payment);

		        // No need for verification here, as we're expecting an exception to be thrown
		    }

		    @Test
		    public void testGetPaymentDetails() {
		        int paymentId = 1;
		        Payment payment = new Payment();
		        payment.setPaymentId("1");
		        payment.setBookingId(2);
		        payment.setAmount("2500");

		        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(payment));

		        Payment retrievedPayment = paymentService.getPaymentDetails(paymentId);

		        verify(paymentRepository, times(1)).findById(paymentId);
		        Assertions.assertNotNull(retrievedPayment);
		        Assertions.assertEquals(payment, retrievedPayment);
		    }
		    
		    @Test
		    public void testGetAllPaymentsDetails() {
		        // Prepare test data
		        Payment payment1 = new Payment();
		        payment1.setPaymentId("1");
		        payment1.setBookingId(3);
		        payment1.setAmount("3000");
		        Payment payment2 = new Payment();
		        payment2.setPaymentId("2");
		        payment2.setBookingId(4);
		        payment2.setAmount("4000");
		        List<Payment> payments =  new ArrayList<>();
		        payments.add(payment1);
		        payments.add(payment2);
		        
		        
		        when(paymentRepository.findAll()).thenReturn(payments);
		        
		        List<Payment> retrievedPayments = paymentService.getAllPaymentsDetails();
		        verify(paymentRepository, times(1)).findAll();
		        
		        Assertions.assertEquals(payments.size(), retrievedPayments.size());
		        Assertions.assertEquals(payments, retrievedPayments);
		    }
			
		}


