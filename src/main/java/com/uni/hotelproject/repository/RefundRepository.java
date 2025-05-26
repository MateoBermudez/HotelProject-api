package com.uni.hotelproject.repository;

import com.uni.hotelproject.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RefundRepository extends JpaRepository<Refund, String> {

    boolean existsRefundByRefundID(String refundID);
}
