package ru.jakesmokie.cities.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import ru.jakesmokie.cities.services.ICityService;
import ru.jakesmokie.cities.services.IFirstServiceIntegration;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
    private final ICityService cityService;
    private final IFirstServiceIntegration integration;

    public CitiesController(ICityService cityService, IFirstServiceIntegration integration) {
        this.cityService = cityService;
        this.integration = integration;
    }

    @GetMapping("info")
    public String port() {
        return integration.port();
    }

    @GetMapping("/count/{id1}/{id2}/{id3}")
    public ResponseEntity<?> population(@PathVariable @NonNull Long id1, @PathVariable @NonNull Long id2, @PathVariable @NonNull Long id3) {
        return cityService.population(id1, id2, id3)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/deport/{from}/{to}")
    public ResponseEntity<?> deport(@PathVariable @NonNull Long from, @PathVariable @NonNull Long to) {
        if (from.equals(to)) {
            return ResponseEntity.badRequest().build();
        }

        return cityService.deport(from, to) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}  