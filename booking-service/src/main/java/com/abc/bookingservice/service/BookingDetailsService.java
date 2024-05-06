package com.abc.bookingservice.service;

import java.util.List;

import com.abc.bookingservice.entity.BookingDetails;
import com.abc.bookingservice.payload.BookingPayloadDetails;

public interface BookingDetailsService {
    BookingDetails addBookingDetails(BookingDetails bookingDetails);
    BookingDetails updateBookingDetails(BookingDetails bookingDetails);
    void removeBookingDetails(int bookingId);
    List<BookingDetails> getAllBookingDetails();
    BookingDetails getBookingDetailsById(int bookingId);
   
    BookingPayloadDetails ViewBookingDetailsById(int bookingId);
}
