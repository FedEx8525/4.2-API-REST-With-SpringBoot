package cat.itacademy.s04.t02.n01.fruit.mapper;

import cat.itacademy.s04.t02.n01.fruit.model.Fruit;
import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitRequestDTO;
import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitResponseDTO;
import jakarta.validation.constraints.NotNull;

public class FruitMapper {
    public static FruitResponseDTO mapToDTO(Fruit fruit) {
        return new FruitResponseDTO(fruit.getId(), fruit.getName(), fruit.getWeightInKilos());
    }

    public static Fruit mapToEntity(@NotNull FruitRequestDTO dto) {
        return new Fruit(dto.name(), dto.weightInKilos());
    }
}
