package com.abc.bookingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.abc.bookingservice.entity.BookingDetails;
import com.abc.bookingservice.service.BookingDetailsService;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingDetailsController {

    @Autowired
    private BookingDetailsService bookingDetailsService;

    @PostMapping("/Save")
    public ResponseEntity<BookingDetails> addBookingDetails(@RequestBody BookingDetails bookingDetails) {
        BookingDetails addedBookingDetails = bookingDetailsService.addBookingDetails(bookingDetails);
        return new ResponseEntity<>(addedBookingDetails, HttpStatus.CREATED);
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingDetails> updateBookingDetails(@PathVariable int bookingId, @RequestBody BookingDetails bookingDetails) {
        bookingDetails.setBookingId(bookingId);
        BookingDetails updatedBookingDetails = bookingDetailsService.updateBookingDetails(bookingDetails);
        if (updatedBookingDetails != null) {
            return new ResponseEntity<>(updatedBookingDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> removeBookingDetails(@PathVariable int bookingId) {
        bookingDetailsService.removeBookingDetails(bookingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<BookingDetails>> getAllBookingDetails() {
        List<BookingDetails> bookingDetailsList = bookingDetailsService.getAllBookingDetails();
        return new ResponseEntity<>(bookingDetailsList, HttpStatus.OK);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDetails> getBookingDetailsById(@PathVariable("bookingId") int bookingId) {
        BookingDetails bookingDetails = bookingDetailsService.getBookingDetailsById(bookingId);
        if (bookingDetails != null) {
            return new ResponseEntity<>(bookingDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
