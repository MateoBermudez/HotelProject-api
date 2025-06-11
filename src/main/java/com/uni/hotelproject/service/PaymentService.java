package com.uni.hotelproject.service;

import com.uni.hotelproject.dto.PaymentDTO;
import com.uni.hotelproject.entity.Payment;
import com.uni.hotelproject.mapper.PaymentMapper;
import com.uni.hotelproject.repository.BookingRepository;
import com.uni.hotelproject.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final PaymentMapper paymentMapper;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, BookingRepository bookingRepository,
                          PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
        this.paymentMapper = paymentMapper;
    }

    public String generateRandomPaymentId() {
        Random random = new Random();
        String PaymentId; // Minúscula inicial según convenciones Java
        do {
            PaymentId = "PY" + String.format("%05d", random.nextInt(100000));
        } while (paymentRepository.existsPaymentByPaymentID(PaymentId));

        return PaymentId;
    }

    @Transactional
    public Payment savePayment(PaymentDTO paymentdto) {
        try {
            Payment payment = paymentMapper.paymentDTOToPayment(paymentdto);
            payment.setPaymentID(generateRandomPaymentId());

            // Guardar el pago
            Payment savedPayment = paymentRepository.save(payment);

            // Confirmar la reserva
            bookingRepository.confirmBooking(savedPayment.getBooking().getId());

            return savedPayment;
        } catch (Exception e) {

            throw e; // Relanzar para manejo superior
        }
    }


    @Transactional
    public Payment getPaymentById(String paymentId) {
        return paymentRepository.findPaymentByPaymentID(paymentId);
    }

    @Transactional(readOnly = true)
    public List<Payment> getAllPaymentsByUserId(String userId) {
        List<Payment> payments = paymentRepository.findAllByuserId(userId);
        return payments;
    }

    public Payment getPaymentByBookingId(Long bookingId) {
        Payment payment = paymentRepository.findPaymentByBookingId(bookingId);
        return payment;
    }
}
