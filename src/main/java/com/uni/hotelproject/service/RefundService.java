package com.uni.hotelproject.service;

import com.uni.hotelproject.dto.RefundDTO;
import com.uni.hotelproject.entity.Payment;
import com.uni.hotelproject.entity.Refund;
import com.uni.hotelproject.factory.RefundStrategy;
import com.uni.hotelproject.factory.RefundStrategyFactory;
import com.uni.hotelproject.mapper.RefundMapper;
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

    RefundRepository refundRepository;

    @Autowired
    public RefundService(RefundRepository refundRepository) {
        this.refundRepository = refundRepository;
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
    public RefundDTO addRefund(Payment payment) {
        RefundStrategy refundStrategy = RefundStrategyFactory.getStrategy(payment);
        Refund refund = refundStrategy.calculateRefund(payment, calculateDaysBeforeReservation(payment));
        refundRepository.save(refund);
        RefundDTO refundDTO = RefundMapper.INSTANCE.refundToRefundDTO(refund);
        return refundDTO;
    }

    private int calculateDaysBeforeReservation(Payment payment) {
        LocalDate reservationDate = payment.getPaymentDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate today = LocalDate.now();

        return (int) ChronoUnit.DAYS.between(today, reservationDate);
    }



}
