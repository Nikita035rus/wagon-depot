package com.wagondepot.controller;

import com.wagondepot.model.WagonDto;
import com.wagondepot.service.WagonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wagon")
public class WagonController {
    private final WagonService wagonService;

    @GetMapping("/{id}")
    public ResponseEntity<WagonDto> getWagon(@PathVariable Long id) {
        var wagonById = wagonService.getWagonById(id);
        return ResponseEntity.ok(wagonById);
    }

    @PostMapping
    public ResponseEntity<WagonDto> createWagon(@RequestBody WagonDto wagonDto) {
        var wagon = wagonService.createWagon(wagonDto);
        return ResponseEntity.ok(wagon);
    }

    @PutMapping("/{id}}")
    public ResponseEntity<WagonDto> updateWagon(@PathVariable Long id,
                                                @RequestBody WagonDto wagonDto) {
        var wagon = wagonService.updateWagon(id, wagonDto);
        return ResponseEntity.ok(wagon);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        wagonService.deleteWagon(id);
        return ResponseEntity.ok("Delete success");
    }
}
