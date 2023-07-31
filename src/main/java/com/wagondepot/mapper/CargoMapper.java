package com.wagondepot.mapper;

import com.wagondepot.entity.Cargo;
import com.wagondepot.model.CargoDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CargoMapper {

    CargoDto toCargoDto (Cargo cargo);

    Cargo toCargo (CargoDto cargoDto);

    List<CargoDto> toCargoDtoList(List<Cargo> cargos);

    List<Cargo> toCargoList(List<CargoDto> cargoDtos);

    @BeanMapping(nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
    void updateCargo(@MappingTarget Cargo cargo, CargoDto cargoDto);
}
