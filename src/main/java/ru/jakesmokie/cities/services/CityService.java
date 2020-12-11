package ru.jakesmokie.cities.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.jakesmokie.cities.models.CityDto;
import ru.jakesmokie.cities.models.input.CityInputDto;
import ru.jakesmokie.cities.models.input.CoordinatesInputDto;
import ru.jakesmokie.cities.models.input.GovernmentDto;
import ru.jakesmokie.cities.models.input.HumanInputDto;

import java.util.Optional;

@Service
public class CityService implements ICityService {
    private final ObjectMapper objectMapper;

    public CityService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public CityDto get(long id) {
        try {
            val response = restTemplate()
                .exchange("https://localhost:50002/cities/api/cities/" + id, HttpMethod.GET, null, CityDto.class);

            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
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

        restTemplate()
            .exchange("https://localhost:50002/cities/api/cities/" + city.getId(), HttpMethod.PATCH, new HttpEntity<>(cityInputDto), String.class);
    }

    private RestTemplate restTemplate() {
        val httpClient = HttpClients.custom()
            .setSSLHostnameVerifier(new NoopHostnameVerifier())
            .build();

        val requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        return new RestTemplate(requestFactory);
    }
}
