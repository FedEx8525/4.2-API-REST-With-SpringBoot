package cat.itacademy.s04.t02.n01.fruit.services;

import cat.itacademy.s04.t02.n01.fruit.dto.FruitRequestDTO;
import cat.itacademy.s04.t02.n01.fruit.dto.FruitResponseDTO;
import cat.itacademy.s04.t02.n01.fruit.dto.FruitUpdateDTO;

import java.util.List;

public interface FruitService {
    FruitResponseDTO createFruit(FruitRequestDTO fruitRequestDTO);
    List<FruitResponseDTO> listFruits();
    FruitResponseDTO getFruitById(Long id);
    FruitResponseDTO updateFruit(Long id, FruitUpdateDTO fruitUpdateDTO);
    void deleteFruit(Long id);
}
