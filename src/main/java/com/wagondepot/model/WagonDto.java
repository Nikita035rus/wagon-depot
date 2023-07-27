package com.wagondepot.model;

import com.wagondepot.entity.wagon.WagonType;
import lombok.Builder;

@Builder
public record WagonDto(
        Long id,
        String sequenceNumber,
        WagonType type,
        Integer weight,
        Integer tareWeight,
        Double capacity) {
}
