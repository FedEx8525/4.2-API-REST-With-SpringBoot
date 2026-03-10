package cat.itacademy.s04.t02.n01.fruit.controllers;

import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitCreateDTO;
import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitDTO;
import cat.itacademy.s04.t02.n01.fruit.services.FruitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fruits")
public class FruitController {

    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @PostMapping
    public ResponseEntity<FruitDTO> createFruit(@Validated @RequestBody FruitCreateDTO fruitCreateDTO) {

        FruitDTO savedFruit = fruitService.createFruit(fruitCreateDTO);

        return new ResponseEntity<>(savedFruit, HttpStatus.CREATED);
    }

}
