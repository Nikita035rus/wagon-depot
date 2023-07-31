package com.wagondepot.model;

import com.wagondepot.entity.wagon.Wagon;
import lombok.*;

import java.util.List;

@Builder
public record StationDepartureOfWagonDto(
        Long id,
        String name,
        List<Wagon> wagons) {
}
