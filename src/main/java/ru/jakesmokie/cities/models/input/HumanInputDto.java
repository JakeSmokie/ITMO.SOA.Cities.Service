package ru.jakesmokie.cities.models.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HumanInputDto {
    @Min(0L) private Long height;
    private LocalDate birthday;
}
