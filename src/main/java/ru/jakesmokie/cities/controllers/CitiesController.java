package ru.jakesmokie.cities.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import ru.jakesmokie.cities.services.ICityService;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
    private final ICityService cityService;

    public CitiesController(ICityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/count/{id1}/{id2}/{id3}")
    public ResponseEntity<?> population(@PathVariable @NonNull Long id1, @PathVariable @NonNull Long id2, @PathVariable @NonNull Long id3) {
        return cityService.population(id1, id2, id3)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/deport/{from}/{to}")
    public ResponseEntity<?> deport(@PathVariable @NonNull Long from, @PathVariable @NonNull Long to) {
        return cityService.deport(from, to) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}