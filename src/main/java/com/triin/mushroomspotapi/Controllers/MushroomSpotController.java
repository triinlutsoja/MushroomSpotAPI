package com.triin.mushroomspotapi.Controllers;

import com.triin.mushroomspotapi.DTOs.MushroomSpotCreateDto;
import com.triin.mushroomspotapi.DTOs.MushroomSpotGeoJsonDto;
import com.triin.mushroomspotapi.Services.MushroomSpotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mushroomspots")
public class MushroomSpotController {

    private final MushroomSpotService mushroomSpotService;

    public MushroomSpotController(MushroomSpotService mushroomSpotService) {
        this.mushroomSpotService = mushroomSpotService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllSpots() {
        List<MushroomSpotGeoJsonDto> dtos = mushroomSpotService.getAllSpots();

        Map<String, Object> featureCollection = new HashMap<>();
        featureCollection.put("type", "FeatureCollection");
        featureCollection.put("features", dtos);

        return ResponseEntity.status(HttpStatus.OK).body(featureCollection);
    }

    @PostMapping
    public ResponseEntity<MushroomSpotGeoJsonDto> saveSpot(@RequestBody MushroomSpotCreateDto dto) {
        MushroomSpotGeoJsonDto savedDto = mushroomSpotService.saveSpot(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MushroomSpotGeoJsonDto> updateSpot(@PathVariable Long id,
                                                             @RequestBody MushroomSpotCreateDto dto) {
        MushroomSpotGeoJsonDto updatedDto = mushroomSpotService.updateSpot(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpot(@PathVariable Long id) {
        mushroomSpotService.deleteSpot(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
