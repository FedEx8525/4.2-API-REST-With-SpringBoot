package cat.itacademy.s04.t02.n01.fruit.controllers;

import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitRequestDTO;
import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitResponseDTO;
import cat.itacademy.s04.t02.n01.fruit.services.FruitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(FruitController.class)
public class FruitControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FruitService fruitService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createFruit_ShouldReturn201Created() throws Exception {
        FruitRequestDTO input = new FruitRequestDTO("orange", 50);

        FruitResponseDTO output = new FruitResponseDTO(1L, "orange", 50);

        when(fruitService.createFruit(any(FruitRequestDTO.class))).thenReturn(output);

        mockMvc.perform(post("/fruits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("orange"))
                .andExpect(jsonPath("$.weightInKilos").value(50));
    }

    @Test
    void createFruit_ShouldReturn400WhenDataIsInvalid() throws Exception {
        FruitRequestDTO invalidInput = new FruitRequestDTO("", -5);

        mockMvc.perform(post("/fruits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidInput)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void listFruits_ShouldReturn200WithList() throws Exception {
        List<FruitResponseDTO> fruits = List.of(new FruitResponseDTO(1L, "apple", 10), new FruitResponseDTO(2L, "banana", 20));
        when(fruitService.listFruits()).thenReturn(fruits);
        mockMvc.perform(get("/fruits").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("apple"))
                .andExpect(jsonPath("$[1].name").value("banana"));
    }


    @Test
    void listFruits_ShouldReturnEmptyListWhenNoFruitsExist() throws Exception {
        when(fruitService.listFruits()).thenReturn(List.of());
        mockMvc.perform(get("/fruits"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

}
