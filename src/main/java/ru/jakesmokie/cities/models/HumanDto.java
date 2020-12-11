package ru.jakesmokie.cities.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class HumanDto {
    Long height;
    LocalDate birthday;
}
