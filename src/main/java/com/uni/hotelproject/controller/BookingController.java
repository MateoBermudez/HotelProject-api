package com.uni.hotelproject.controller;

import com.uni.hotelproject.dto.BookingDTO;
import com.uni.hotelproject.service.BookingService;
import com.uni.hotelproject.service.PdfService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final PdfService pdfService;

    public BookingController(BookingService bookingService, PdfService pdfService) {
        this.bookingService = bookingService;
        this.pdfService = pdfService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @PostMapping("/create")
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        return ResponseEntity.ok(bookingService.createBooking(bookingDTO));
    }

    @PatchMapping("/{bookingId}/confirm")
    public ResponseEntity<Void> confirmBooking(@PathVariable Long bookingId) {
        bookingService.confirmBooking(bookingId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{bookingId}/cancel")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.getBookingById(bookingId));
    }

    @GetMapping("/booking/{bookingId}/pdf")
    public ResponseEntity<byte[]> getBookingPdf(@PathVariable Long bookingId) {
        BookingDTO booking = bookingService.getBookingById(bookingId);
        byte[] pdfBytes = pdfService.generateBookingPdf(booking);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=booking_" + bookingId + ".pdf")
                .body(pdfBytes);
    }
}
