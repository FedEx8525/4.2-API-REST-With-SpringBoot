package cat.itacademy.s04.t02.n01.fruit.services;

import cat.itacademy.s04.t02.n01.fruit.exception.FruitNotFoundException;
import cat.itacademy.s04.t02.n01.fruit.mapper.FruitMapper;
import cat.itacademy.s04.t02.n01.fruit.model.Fruit;
import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitRequestDTO;
import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitResponseDTO;
import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitUpdateDTO;
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
        FruitResponseDTO response = mapToDTO(savedFruit);
        return response;

    }

    @Override
    public List<FruitResponseDTO> listFruits() {
        List<Fruit> fruits = fruitRepository.findAll();
        return fruitRepository.findAll().stream()
                .map(FruitMapper::mapToDTO)
                .toList();
    }

    @Override
    public FruitResponseDTO getFruitById(Long id) {
        Fruit fruit = fruitRepository.findById(id)
                .orElseThrow(() -> new FruitNotFoundException(id.toString()));
        FruitResponseDTO response = mapToDTO(fruit);
        return response;
    }

    @Override
    public FruitResponseDTO updateFruit(Long id, FruitUpdateDTO fruitUpdateDTO) {
        Fruit fruit = fruitRepository.findById(id)
                .orElseThrow(() -> new FruitNotFoundException(id.toString()));

        fruit.setName(fruitUpdateDTO.name());
        fruit.setWeightInKilos(fruitUpdateDTO.weightInKilos());

        Fruit updatedFruit = fruitRepository.save(fruit);
        FruitResponseDTO response = mapToDTO(updatedFruit);
        return response;
    }

    @Override
    public void deleteFruit(Long id) {
        if (!fruitRepository.existsById(id)) {
            throw new FruitNotFoundException(id.toString());
        }
        fruitRepository.deleteById(id);
    }


}
