package com.wagondepot.controller;

import com.wagondepot.model.CargoDto;
import com.wagondepot.service.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cargo")
public class CargoController {
    private final CargoService cargoService;

    @GetMapping("/{id}")
    public ResponseEntity<CargoDto> getCargo(@PathVariable Long id) {
        var cargoById = cargoService.getCargoById(id);
        return ResponseEntity.ok(cargoById);
    }

    @PostMapping
    public ResponseEntity<CargoDto> createCargo(@RequestBody CargoDto cargoRequest) {
        var cargoResponse = cargoService.createCargo(cargoRequest);
        return ResponseEntity.ok(cargoResponse);
    }

    @PutMapping("/{id}}")
    public ResponseEntity<CargoDto> updateCargo(@PathVariable Long id,
                                                @RequestBody CargoDto cargoRequest) {
        var cargoResponse = cargoService.updateCargo(id, cargoRequest);
        return ResponseEntity.ok(cargoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        cargoService.deleteCargo(id);
        return ResponseEntity.ok("Delete success");
    }
}
