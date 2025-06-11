package com.uni.hotelproject.mapper;

import com.uni.hotelproject.dto.PaymentDTO;
import com.uni.hotelproject.entity.Booking;
import com.uni.hotelproject.entity.Payment;
import com.uni.hotelproject.enums.PaymentType;
import com.uni.hotelproject.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    @Autowired
    private BookingRepository bookingRepository;

    public Payment paymentDTOToPayment(PaymentDTO paymentDTO) {
        if (paymentDTO == null) {
            return null;
        }
        Booking booking = bookingRepository.findById(paymentDTO.getBookingID())
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + paymentDTO.getBookingID()));

        return Payment.builder()
                .booking(booking)
                .amount(paymentDTO.getAmount())
                .amountPaid(paymentDTO.getAmountPaid())
                .paymentDate(paymentDTO.getPaymentDate())
                .paymentType(PaymentType.valueOf(paymentDTO.getPaymentType().toUpperCase()))
                .cardNumber(paymentDTO.getCardNumber())
                .userid(paymentDTO.getUserid())
                .build();
    }

    public PaymentDTO paymentToPaymentDTO(Payment payment) {
        if (payment == null) {
            return null;
        }
        Long bookingId = null;
        if (payment.getBooking() != null) {
            bookingId = payment.getBooking().getId();
        }
        return PaymentDTO.builder()
                .paymentID(payment.getPaymentID())
                .bookingID(bookingId)
                .amount(payment.getAmount())
                .amountPaid(payment.getAmountPaid())
                .paymentDate(payment.getPaymentDate())
                .paymentType(payment.getPaymentType().name())
                .cardNumber(payment.getCardNumber())
                .userid(payment.getUserid())
                .build();
    }

}
