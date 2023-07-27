package com.wagondepot.controller;

import com.wagondepot.model.StationDto;
import com.wagondepot.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/station")
public class StationController {
    private final StationService stationService;

    @GetMapping("/{id}")
    public ResponseEntity<StationDto> getStation(@PathVariable Long id) {
        var stationById = stationService.getStationById(id);
        return ResponseEntity.ok(stationById);
    }

    @PostMapping
    public ResponseEntity<StationDto> createStation(@RequestBody StationDto stationDto) {
        var station = stationService.createStation(stationDto);
        return ResponseEntity.ok(station);
    }

    @PutMapping("/{id}}")
    public ResponseEntity<StationDto> updateStation(@PathVariable Long id,
                                                    @RequestBody StationDto stationDto) {
        var station = stationService.updateStation(id, stationDto);
        return ResponseEntity.ok(station);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        stationService.deleteStation(id);
        return ResponseEntity.ok("Delete success");
    }
}
