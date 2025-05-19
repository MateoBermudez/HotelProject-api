package com.uni.hotelproject.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AmenityDTO {
    private Long id;

    private String name;

    private String description;

    private Boolean available;
}
