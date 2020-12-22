package ru.jakesmokie.cities.services;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.jakesmokie.cities.models.CityDto;
import ru.jakesmokie.cities.models.input.CityInputDto;

@FeignClient(name = "first")
@RibbonClient(name = "first")
public interface IFirstServiceIntegration {
    @GetMapping("info")
    String port();

    @GetMapping("api/cities/{id}")
    CityDto get(@PathVariable long id);

    @PostMapping("api/cities/{id}")
    void patch(@PathVariable long id, @RequestBody CityInputDto input);
}
