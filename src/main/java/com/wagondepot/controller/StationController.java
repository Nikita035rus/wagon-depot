package com.wagondepot.controller;

import com.wagondepot.model.StationDto;
import com.wagondepot.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/station", produces = MediaType.APPLICATION_JSON_VALUE)
public class StationController {
    private final StationService stationService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<StationDto> getStation(@PathVariable Long id) {
        var stationById = stationService.getStationById(id);
        return ResponseEntity.ok(stationById);
    }

    @PostMapping
    public ResponseEntity<StationDto> createStation(@RequestBody StationDto stationDto) {
        var station = stationService.createStation(stationDto);
        return ResponseEntity.ok(station);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StationDto> updateStation(@PathVariable Long id,
                                                    @RequestBody StationDto stationDto) {
        var station = stationService.updateStation(id, stationDto);
        return ResponseEntity.ok(station);
    }

    @PutMapping("/acceptance/{id}")
    public ResponseEntity<StationDto> acceptanceOfWagons(@PathVariable Long id,
                                                         @RequestBody StationDto stationDto) {
        var station = stationService.acceptanceOfWagons(id, stationDto);
        return ResponseEntity.ok(station);
    }

    @PutMapping("/rearrangement/{id}")
    public ResponseEntity<StationDto> rearrangementsOfWagons(@PathVariable Long id,
                                                             @RequestBody StationDto stationDto) {
        var station = stationService.rearrangementsOfWagons(id, stationDto);
        return ResponseEntity.ok(station);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        stationService.deleteStation(id);
        return ResponseEntity.ok("Delete success");
    }
}
