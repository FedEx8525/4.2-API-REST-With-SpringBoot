package cat.itacademy.s04.t02.n01.fruit.services;

import cat.itacademy.s04.t02.n01.fruit.exception.FruitNotFoundException;
import cat.itacademy.s04.t02.n01.fruit.model.Fruit;
import cat.itacademy.s04.t02.n01.fruit.dto.FruitRequestDTO;
import cat.itacademy.s04.t02.n01.fruit.dto.FruitResponseDTO;
import cat.itacademy.s04.t02.n01.fruit.dto.FruitUpdateDTO;
import cat.itacademy.s04.t02.n01.fruit.repository.FruitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FruitServiceImplTest {

    @Mock
    FruitRepository fruitRepository;

    @InjectMocks
    FruitServiceImpl fruitService;

    private Fruit fruit1;
    private Fruit fruit2;

    @BeforeEach
    void setup() {
        fruit1 = new Fruit("Apple", 10);
        fruit1.setId(1L);
        fruit2 = new Fruit("Banana", 20);
        fruit2.setId(2L);
    }

    @Test
    void createFruit_ShouldReturnFruitResponseDTO_WhenFruitIsSave() {
        FruitRequestDTO request = new FruitRequestDTO("Apple", 10);

        when(fruitRepository.save(any(Fruit.class))).thenReturn(fruit1);

        FruitResponseDTO result = fruitService.createFruit(request);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Apple", result.name());
        assertEquals(10, result.weightInKilos());
        verify(fruitRepository, times(1)).save(any(Fruit.class));
    }

    @Test
    void listFruits_ShouldReturnAll_WhenFruitsExist() {

        List<Fruit> fruits = Arrays.asList(fruit1, fruit2);

        when(fruitRepository.findAll()).thenReturn(fruits);

        List<FruitResponseDTO> result = fruitService.listFruits();

        assertEquals(2, result.size());
        assertEquals("Apple", result.get(0).name());
        verify(fruitRepository, times(1)).findAll();
    }

    @Test
    void getFruitById_ShouldReturnFruitResponseDTO_WhenIdExists() {
        when(fruitRepository.findById(1L)).thenReturn(Optional.of(fruit1));

        FruitResponseDTO result = fruitService.getFruitById(1L);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Apple", result.name());
        verify(fruitRepository, times(1)).findById(1L);

    }

    @Test
    void updateFruit_ShouldReturnUpdatedFruitResponseDTO_WhenIdExists() {
        Long id = 1L;
        FruitUpdateDTO request = new FruitUpdateDTO( "Mango", 50);

        fruit1.setName("Mango");
        fruit1.setWeightInKilos(50);

        when(fruitRepository.findById(id)).thenReturn(Optional.of(fruit1));
        when(fruitRepository.save(any(Fruit.class))).thenReturn(fruit1);

        FruitResponseDTO result = fruitService.updateFruit(id, request);

        assertNotNull(result);
        assertEquals(id, result.id());
        assertEquals("Mango", result.name());
        assertEquals(50, result.weightInKilos());
        verify(fruitRepository, times(1)).findById(id);
        verify(fruitRepository, times(1)).save(any(Fruit.class));
    }

    @Test
    void updateFruit_ShouldThrowException_WhenIdDoesNotExist() {
        Long id = 99L;
        FruitUpdateDTO request = new FruitUpdateDTO("Mango", 50);

        when(fruitRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(FruitNotFoundException.class, () -> fruitService.updateFruit(id, request));
        verify(fruitRepository, never()).save(any(Fruit.class));
    }

    @Test
    void deleteFruit_ShouldDelete_WhenIdExists() {
        Long id = 1L;

        when(fruitRepository.existsById(id)).thenReturn(true);
        doNothing().when(fruitRepository).deleteById(id);

        fruitService.deleteFruit(id);

        verify(fruitRepository, times(1)).existsById(id);
        verify(fruitRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteFruit_ShouldThrowException_WhenIdDoesNotExist() {
        Long id = 99L;

        when(fruitRepository.existsById(id)).thenReturn(false);

        assertThrows(FruitNotFoundException.class, () -> fruitService.deleteFruit(id));

        verify(fruitRepository, never()).deleteById(anyLong());
    }


}
