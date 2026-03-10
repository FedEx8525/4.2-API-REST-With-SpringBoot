package cat.itacademy.s04.t02.n01.fruit.services;

import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitCreateDTO;
import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitDTO;

public interface FruitService {
    FruitDTO createFruit(FruitCreateDTO fruitCreateDTO);
}
