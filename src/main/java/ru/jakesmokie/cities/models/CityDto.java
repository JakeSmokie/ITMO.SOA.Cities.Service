package ru.jakesmokie.cities.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CityDto {
    long id;
    String name;
    java.time.LocalDate creationDate;
    Integer area;
    Long population;
    Integer metersAboveSeaLevel;
    Long carCode;
    Float agglomeration;
    String government;
    CoordinatesDto coordinates;
    HumanDto governor;
}
