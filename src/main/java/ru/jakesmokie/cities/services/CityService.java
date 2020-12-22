package ru.jakesmokie.cities.services;

import feign.FeignException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jakesmokie.cities.models.CityDto;
import ru.jakesmokie.cities.models.input.CityInputDto;
import ru.jakesmokie.cities.models.input.CoordinatesInputDto;
import ru.jakesmokie.cities.models.input.GovernmentDto;
import ru.jakesmokie.cities.models.input.HumanInputDto;

import java.util.Optional;

@Service
public class CityService implements ICityService {
    @Autowired IFirstServiceIntegration integration;

    public CityDto get(long id) {
        try {
            return integration.get(id);
        } catch (FeignException.FeignClientException.NotFound notFound) {
            return null;
        }
    }

    public Optional<Long> population(long first, long second, long third) {
        val firstCity = get(first);
        val secondCity = get(second);
        val thirdCity = get(third);

        if (firstCity == null || secondCity == null || thirdCity == null) {
            return Optional.empty();
        }

        return Optional.of(firstCity.getPopulation() + secondCity.getPopulation() + thirdCity.getPopulation());
    }

    public boolean deport(long first, long second) {
        val firstCity = get(first);
        val secondCity = get(second);

        if (firstCity == null || secondCity == null) {
            return false;
        }

        setPopulation(firstCity, 0L);
        setPopulation(secondCity, firstCity.getPopulation() + secondCity.getPopulation());

        return true;
    }

    private void setPopulation(CityDto city, Long population) {
        val cityInputDto = CityInputDto.builder()
            .agglomeration(city.getAgglomeration())
            .area(city.getArea())
            .carCode(city.getCarCode())
            .coordinates(
                CoordinatesInputDto.builder()
                    .x(city.getCoordinates().getX())
                    .y(city.getCoordinates().getY())
                    .build()
            )
            .government(GovernmentDto.valueOf(city.getGovernment()))
            .governor(
                HumanInputDto.builder()
                    .birthday(city.getGovernor().getBirthday())
                    .height(city.getGovernor().getHeight())
                    .build()
            )
            .id(city.getId())
            .metersAboveSeaLevel(city.getMetersAboveSeaLevel())
            .name(city.getName())
            .population(population)
            .build();

        integration.patch(city.getId(), cityInputDto);
    }
}
