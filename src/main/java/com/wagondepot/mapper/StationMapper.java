package com.wagondepot.mapper;

import com.wagondepot.entity.station.Station;
import com.wagondepot.model.StationDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "string")
public interface StationMapper {

    StationDto toStationDto(Station station);

    Station toStation(StationDto stationDto);

    List<StationDto> toStationDtoList(List<Station> stations);

    List<Station> toStationList(List<StationDto> stationDtos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStation(@MappingTarget Station station, StationDto stationDto);
}
