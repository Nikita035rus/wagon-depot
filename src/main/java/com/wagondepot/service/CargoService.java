package com.wagondepot.service;

import com.wagondepot.entity.Cargo;
import com.wagondepot.exception.NoSuchCustomerException;
import com.wagondepot.mapper.CargoMapper;
import com.wagondepot.model.CargoDto;
import com.wagondepot.repositiry.CargoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CargoService {
    private final CargoMapper cargoMapper;
    private final CargoRepository cargoRepository;

    public CargoDto getCargoById(Long id) {
        return cargoMapper.toCargoDto(findCargoById(id));
    }

    public CargoDto createCargo(CargoDto cargoDto) {
        var cargo = cargoMapper.toCargo(cargoDto);
        return cargoMapper.toCargoDto(cargoRepository.save(cargo));
    }

    public CargoDto updateCargo(Long id, CargoDto cargoDto) {
        var cargo = findCargoById(id);
        cargoMapper.updateCargo(cargo, cargoDto);
        return cargoMapper.toCargoDto(cargoRepository.save(cargo));

    }

    public Cargo findCargoById(Long id) {
        return cargoRepository.findById(id)
                .orElseThrow(() -> new NoSuchCustomerException("Cargo with id: %s is missing in the database".formatted(id)));
    }

    public void deleteCargo(Long id) {
        cargoRepository.delete(findCargoById(id));
    }
}
