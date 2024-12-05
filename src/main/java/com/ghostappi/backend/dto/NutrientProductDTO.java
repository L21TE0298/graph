package com.ghostappi.backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NutrientProductDTO {

    @Positive(message = "The nutrient id must be a positive number")
    @Min(value = 1, message = "The nutrient id min value is 1")
    @Max(value = 999999999, message = "The nutrient id max value is 999999999")
    @NotNull(message = "The nutrient id must contain at least one digit")
    private Integer idNutrient;


    @Positive(message = "Quantity number must be a positive number")
    @Min(value = 1, message = "The quantity number min value is 1")
    @Max(value = 999999999, message = "The quantity number max value is 999999999")
    @NotNull(message = "The quantity number must contain at least one digit")
    private double quantity;


    @Positive(message = "Percentage must be a positive number")
    @Min(value = 1, message = "The percentage min value is 1")
    @Max(value = 100, message = "The percentage number max value is 100")
    @NotNull(message = "The percentage must contain at least one digit")
    private Short percentage;

}