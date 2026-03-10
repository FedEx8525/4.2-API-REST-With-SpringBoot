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
    }

    @Test
    void listUsers_shouldReturnAll_whenNameIsNull() {
        Fruit savedEntity = new Fruit("Apple", 10);
        savedEntity.setId(1L);

        when(fruitRepository.findAll()).thenReturn(List.of(savedEntity));

        fruitService.listFruits(null);

        verify(fruitRepository, times(1)).findAll();
        verify(fruitRepository, never()).searchByName(anyString());
    }
}
