package cat.itacademy.s04.t02.n01.fruit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record FruitRequestDTO(
        @NotBlank(message = "The name cannot be empty")
        String name,
        @Positive(message = "The weight must be greater than zero")
        int weightInKilos) {}