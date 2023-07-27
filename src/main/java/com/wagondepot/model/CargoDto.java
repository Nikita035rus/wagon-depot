package com.wagondepot.model;

import lombok.Builder;

@Builder
public record CargoDto(
        Long id,
        String code,
        String name) {
}
