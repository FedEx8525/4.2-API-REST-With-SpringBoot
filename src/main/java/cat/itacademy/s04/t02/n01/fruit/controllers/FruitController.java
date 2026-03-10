package cat.itacademy.s04.t02.n01.fruit.controllers;

import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitRequestDTO;
import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitResponseDTO;
import cat.itacademy.s04.t02.n01.fruit.services.FruitService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fruits")
public class FruitController {

    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @PostMapping
    public ResponseEntity<FruitResponseDTO> createFruit(@Valid @RequestBody FruitRequestDTO fruitRequestDTO) {

        FruitResponseDTO savedFruit = fruitService.createFruit(fruitRequestDTO);

        return new ResponseEntity<>(savedFruit, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FruitResponseDTO>> getFruits(String name){
        List<FruitResponseDTO> fruits = fruitService.listFruits(name);
        return new ResponseEntity<>(fruits, HttpStatus.OK);
    }

}
