package com.uni.hotelproject.controller;

import com.uni.hotelproject.dto.PaymentDTO;
import com.uni.hotelproject.entity.Payment;
import com.uni.hotelproject.mapper.PaymentMapper;
import com.uni.hotelproject.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = {"http://localhost:3000"})
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @Autowired
    public PaymentController(PaymentService paymentService, PaymentMapper paymentMapper) {
        this.paymentService = paymentService;
        this.paymentMapper = paymentMapper;

    }

    @PostMapping("/payments/create")
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {
        Payment payment = paymentService.savePayment(paymentDTO);
        return ResponseEntity.ok(paymentMapper.paymentToPaymentDTO(payment));
    }

    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable String paymentId) {
        try {
            Payment payment = paymentService.getPaymentById(paymentId);
            PaymentDTO paymentDTO = paymentMapper.paymentToPaymentDTO(payment);
            return ResponseEntity.ok(paymentDTO);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/payment-booking/{bookingId}")
    public ResponseEntity<PaymentDTO> getPaymentByBookingId(@PathVariable Long bookingId) {
        try {
            Payment payment = paymentService.getPaymentByBookingId(bookingId);
            PaymentDTO paymentDTO = paymentMapper.paymentToPaymentDTO(payment);
            return ResponseEntity.ok(paymentDTO);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/payments/all/{userId}")
    public ResponseEntity<List<PaymentDTO>> getAllPaymentsByBookingId(@PathVariable String userId) {
        List<Payment> payments = paymentService.getAllPaymentsByUserId(userId);
        List<PaymentDTO> paymentDTOs = payments.stream()
                .map(paymentMapper::paymentToPaymentDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paymentDTOs);
    }


}
