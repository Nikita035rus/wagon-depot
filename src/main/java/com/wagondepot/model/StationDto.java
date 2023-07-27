package com.wagondepot.model;

import com.wagondepot.entity.station.Way;
import lombok.Builder;

import java.util.List;

@Builder
public record StationDto(
        Long id,
        String name,
        List<Way> ways) {
}
