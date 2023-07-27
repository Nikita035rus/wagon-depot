package com.wagondepot.mapper;


import com.wagondepot.entity.wagon.Wagon;
import com.wagondepot.model.WagonDto;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "string")
public interface WagonMapper {

    WagonDto toWagonDto(Wagon Wagon);

    Wagon toWagon(WagonDto WagonDto);

    List<WagonDto> toWagonDtoList(List<Wagon> Wagons);

    List<Wagon> toWagonList(List<WagonDto> WagonDtos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateWagon(@MappingTarget Wagon Wagon, WagonDto WagonDto);
}
