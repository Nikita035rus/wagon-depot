package com.wagondepot.mapper;

import com.wagondepot.entity.Cargo;
import com.wagondepot.model.CargoDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "string")
public interface CargoMapper {

    CargoDto toCargoDto (Cargo cargo);

    Cargo toCargo (CargoDto cargoDto);

    List<CargoDto> toCargoDtoList(List<Cargo> cargos);

    List<Cargo> toCargoList(List<CargoDto> cargoDtos);

    @BeanMapping(nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
    void updateCargo(@MappingTarget Cargo cargo, CargoDto cargoDto);
}
