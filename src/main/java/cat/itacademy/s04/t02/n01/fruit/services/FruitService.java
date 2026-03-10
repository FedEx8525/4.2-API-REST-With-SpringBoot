package cat.itacademy.s04.t02.n01.fruit.services;

import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitCreateDTO;
import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitDTO;

import java.util.List;

public interface FruitService {
    FruitDTO createFruit(FruitCreateDTO fruitCreateDTO);
    List<FruitDTO> listFruits(String name);
}
