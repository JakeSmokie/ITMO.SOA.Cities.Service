package ru.jakesmokie.cities.models.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoordinatesInputDto {
    @Max(273) private Double x;
    @NotNull private Integer y;
}
