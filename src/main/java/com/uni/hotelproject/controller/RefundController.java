package com.uni.hotelproject.controller;

import com.uni.hotelproject.dto.RefundDTO;
import com.uni.hotelproject.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/refunds")
@CrossOrigin(origins = {"http://localhost:3000"})
public class RefundController {

    private final RefundService refundService;

    @Autowired
    public RefundController(RefundService refundService) {
        this.refundService = refundService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<RefundDTO> calculateRefund(@RequestBody RefundDTO refundDTO) {
        RefundDTO refund = refundService.addRefund(refundDTO);
        if (refund != null) {
            return ResponseEntity.ok(refund);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


}
