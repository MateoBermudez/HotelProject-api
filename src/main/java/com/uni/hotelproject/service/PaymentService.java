package com.uni.hotelproject.service;

import com.uni.hotelproject.entity.Payment;
import com.uni.hotelproject.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public String generateRandomPaymentId() {
        Random random = new Random();
        String PaymentId; // Minúscula inicial según convenciones Java
        do {
            PaymentId = "PY" + String.format("%05d", random.nextInt(100000));
        } while (paymentRepository.existsPaymentByPaymentID(PaymentId));

        return PaymentId;
    }

    public String savePayment(Payment payment) {
        paymentRepository.save(payment);
        return payment.getPaymentID();
    }

}
