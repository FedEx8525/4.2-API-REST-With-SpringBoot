package cat.itacademy.s04.t02.n01.fruit.services;

import cat.itacademy.s04.t02.n01.fruit.model.Fruit;
import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitRequestDTO;
import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitResponseDTO;
import cat.itacademy.s04.t02.n01.fruit.repository.FruitRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitServiceImpl implements FruitService {

    private final FruitRepository fruitRepository;

    public FruitServiceImpl(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Override
    public FruitResponseDTO createFruit(FruitRequestDTO fruitRequestDTO) {
        Fruit fruit = mapToEntity(fruitRequestDTO);
        Fruit savedFruit = fruitRepository.save(fruit);
        FruitResponseDTO output = mapToDTO(savedFruit);
        return output;

    }

    @Override
    public List<FruitResponseDTO> listFruits(String name) {
        if (name != null && !name.isEmpty()) {
            return fruitRepository.searchByName(name);
        }
        return fruitRepository.findAll();
    }


    private static FruitResponseDTO mapToDTO(Fruit fruit) {
        FruitResponseDTO fruitResponseDTO = new FruitResponseDTO(fruit.getId(), fruit.getName(), fruit.getWeightInKilos());
        return fruitResponseDTO;
    }

    private static Fruit mapToEntity(@NotNull FruitRequestDTO fruitRequestDTO) {
        Fruit fruit = new Fruit(fruitRequestDTO.name(), fruitRequestDTO.weightInKilos());
        return fruit;
    }
}
