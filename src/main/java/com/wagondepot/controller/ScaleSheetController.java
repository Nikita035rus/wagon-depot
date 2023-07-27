package com.wagondepot.controller;

import com.wagondepot.model.ScaleSheetDto;
import com.wagondepot.service.ScaleSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ScaleSheet")
public class ScaleSheetController {
    private final ScaleSheetService scaleSheetService;

    @GetMapping("/{id}")
    public ResponseEntity<ScaleSheetDto> getScaleSheet(@PathVariable Long id) {
        var ScaleSheetById = scaleSheetService.getScaleSheetById(id);
        return ResponseEntity.ok(ScaleSheetById);
    }

    @PostMapping
    public ResponseEntity<ScaleSheetDto> createScaleSheet(@RequestBody ScaleSheetDto ScaleSheetRequest) {
        var ScaleSheetResponse = scaleSheetService.createScaleSheet(ScaleSheetRequest);
        return ResponseEntity.ok(ScaleSheetResponse);
    }

    @PutMapping("/{id}}")
    public ResponseEntity<ScaleSheetDto> updateScaleSheet(@PathVariable Long id,
                                                @RequestBody ScaleSheetDto ScaleSheetRequest) {
        var ScaleSheetResponse = scaleSheetService.updateScaleSheet(id, ScaleSheetRequest);
        return ResponseEntity.ok(ScaleSheetResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        scaleSheetService.deleteScaleSheet(id);
        return ResponseEntity.ok("Delete success");
    }
}
