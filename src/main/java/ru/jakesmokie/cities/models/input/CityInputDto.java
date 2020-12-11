package ru.jakesmokie.cities.models.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityInputDto {
    private long id;
    @NotNull @NotBlank private String name;
    @NotNull @Min(0) private Integer area;
    @NotNull @Min(0) private Long population;
    private Integer metersAboveSeaLevel;
    @NotNull @Min(0) @Max(1000) private Long carCode;
    private Float agglomeration;

    @NotNull @Valid private GovernmentDto government;
    @NotNull @Valid private CoordinatesInputDto coordinates;
    @NotNull @Valid private HumanInputDto governor;
}
