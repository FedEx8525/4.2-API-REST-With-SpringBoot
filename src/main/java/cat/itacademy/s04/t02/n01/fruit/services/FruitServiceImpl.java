package cat.itacademy.s04.t02.n01.fruit.services;

import cat.itacademy.s04.t02.n01.fruit.model.Fruit;
import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitCreateDTO;
import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitDTO;
import cat.itacademy.s04.t02.n01.fruit.repository.FruitRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class FruitServiceImpl implements FruitService {

    private final FruitRepository fruitRepository;

    public FruitServiceImpl(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Override
    public FruitDTO createFruit(FruitCreateDTO fruitCreateDTO) {
        Fruit fruit = mapToEntity(fruitCreateDTO);
        Fruit savedFruit = fruitRepository.save(fruit);
        FruitDTO output = mapToDTO(savedFruit);
        return output;

    }


    private static FruitDTO mapToDTO(Fruit fruit) {
        FruitDTO fruitDTO = new FruitDTO(fruit.getId(), fruit.getName(), fruit.getWeightInKilos());
        return fruitDTO;
    }

    private static Fruit mapToEntity(@NotNull FruitCreateDTO fruitCreateDTO) {
        Fruit fruit = new Fruit(fruitCreateDTO.name(), fruitCreateDTO.weightInKilos());
        return fruit;
    }
}
