package cat.itacademy.s04.t02.n01.fruit.services;

import cat.itacademy.s04.t02.n01.fruit.model.Fruit;
import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitRequestDTO;
import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitResponseDTO;
import cat.itacademy.s04.t02.n01.fruit.repository.FruitRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static cat.itacademy.s04.t02.n01.fruit.mapper.FruitMapper.mapToDTO;
import static cat.itacademy.s04.t02.n01.fruit.mapper.FruitMapper.mapToEntity;

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
    public List<FruitResponseDTO> listFruits() {
        List<Fruit> fruits = fruitRepository.findAll();
        List<FruitResponseDTO> dtos = new ArrayList<FruitResponseDTO>();
        for(Fruit f : fruits) {
            dtos.add(mapToDTO(f));
        }
        return dtos;
    }
}
