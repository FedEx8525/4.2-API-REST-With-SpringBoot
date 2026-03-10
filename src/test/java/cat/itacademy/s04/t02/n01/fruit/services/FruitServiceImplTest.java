package cat.itacademy.s04.t02.n01.fruit.services;

import cat.itacademy.s04.t02.n01.fruit.model.Fruit;
import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitCreateDTO;
import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitDTO;
import cat.itacademy.s04.t02.n01.fruit.repository.FruitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FruitServiceImplTest {

    @Mock
    FruitRepository fruitRepository;

    @InjectMocks
    FruitServiceImpl fruitService;

    @Test
    void createFruit_ShouldReturnSavedFruitDTO() {
        FruitCreateDTO input = new FruitCreateDTO("Apple", 10);

        Fruit savedEntity = new Fruit("Apple", 10);
        savedEntity.setId(1L);

        when(fruitRepository.save(any(Fruit.class))).thenReturn(savedEntity);

        FruitDTO result = fruitService.createFruit(input);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Apple", result.name());
        assertEquals(10, result.weightInKilos());
    }
}
