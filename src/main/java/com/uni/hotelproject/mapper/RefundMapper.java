package com.uni.hotelproject.mapper;

import com.uni.hotelproject.dto.RefundDTO;
import com.uni.hotelproject.entity.Refund;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RefundMapper {
    RefundMapper INSTANCE = Mappers.getMapper(RefundMapper.class);

    @Mapping(source = "refundDate", target = "refundDate", dateFormat = "yyyy-MM-dd")
    RefundDTO refundToRefundDTO(Refund refund);

}