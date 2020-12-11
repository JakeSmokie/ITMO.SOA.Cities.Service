package ru.jakesmokie.cities.services;

import ru.jakesmokie.cities.models.CityDto;

import java.util.Optional;

public interface ICityService {
    CityDto get(long id);
    Optional<Long> population(long first, long second, long third);
    boolean deport(long first, long second);
}
