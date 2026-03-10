package cat.itacademy.s04.t02.n01.fruit.services;

import cat.itacademy.s04.t02.n01.fruit.model.Fruit;
import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitRequestDTO;
import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitResponseDTO;
import cat.itacademy.s04.t02.n01.fruit.repository.FruitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FruitServiceImplTest {

    @Mock
    FruitRepository fruitRepository;

    @InjectMocks
    FruitServiceImpl fruitService;

    @Test
    void createFruit_ShouldReturnSavedFruitDTO() {
        FruitRequestDTO input = new FruitRequestDTO("Apple", 10);

        Fruit savedEntity = new Fruit("Apple", 10);
        savedEntity.setId(1L);

        when(fruitRepository.save(any(Fruit.class))).thenReturn(savedEntity);

        FruitResponseDTO result = fruitService.createFruit(input);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Apple", result.name());
        assertEquals(10, result.weightInKilos());
        verify(fruitRepository, times(1)).save(any(Fruit.class));
    }

    @Test
    void listUsers_shouldReturnAll_whenNameIsNull() {
        Fruit fruit1 = new Fruit("Apple", 10);
        fruit1.setId(1L);
        Fruit fruit2 = new Fruit("Banana", 20);
        fruit1.setId(2L);


        List<Fruit> fruits = Arrays.asList(fruit1, fruit2);

        when(fruitRepository.findAll()).thenReturn(fruits);

        List<FruitResponseDTO> result = fruitService.listFruits();

        assertEquals(2, result.size());
        assertEquals("Apple", result.get(0).name());
        verify(fruitRepository, times(1)).findAll();
    }
}
