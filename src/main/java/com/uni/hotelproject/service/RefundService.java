package com.uni.hotelproject.service;

import com.uni.hotelproject.dto.PaymentDTO;
import com.uni.hotelproject.dto.RefundDTO;
import com.uni.hotelproject.entity.Payment;
import com.uni.hotelproject.entity.Refund;
import com.uni.hotelproject.factory.RefundStrategy;
import com.uni.hotelproject.factory.RefundStrategyFactory;
import com.uni.hotelproject.mapper.RefundMapper;
import com.uni.hotelproject.repository.BookingRepository;
import com.uni.hotelproject.repository.PaymentRepository;
import com.uni.hotelproject.repository.RefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
public class RefundService {

    private final RefundRepository refundRepository;
    private final RefundMapper refundMapper;
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public RefundService(RefundRepository refundRepository, RefundMapper refundMapper,
                         PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.refundRepository = refundRepository;
        this.refundMapper = refundMapper;
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    public String generateRandomRefundId() {
        Random random = new Random();
        String refundId;
        do {
            refundId = "RF" + String.format("%05d", random.nextInt(100000));
        } while (refundRepository.existsRefundByRefundID(refundId));

        return refundId;
    }

    @Transactional
    public RefundDTO addRefund(RefundDTO refundDTO) {
        Payment payment = paymentRepository.findById(refundDTO.getPaymentID())
                .orElseThrow(() -> new IllegalArgumentException("Payment not found with ID: " + refundDTO.getPaymentID()));
        RefundStrategy refundStrategy = RefundStrategyFactory.getStrategy(payment);
        Refund refund = refundStrategy.calculateRefund(payment, calculateDaysBeforeReservation(payment));
        refund.setRefundID(generateRandomRefundId());
        refundRepository.save(refund);
        bookingRepository.unconfirmBooking(payment.getBooking().getId());
        return refundMapper.refundToRefundDTO(refund);
    }

    private int calculateDaysBeforeReservation(Payment payment) {
        LocalDate reservationDate = payment.getBooking().getStartDate();
        LocalDate today = LocalDate.now();
        return (int) ChronoUnit.DAYS.between(today, reservationDate);
    }



}
