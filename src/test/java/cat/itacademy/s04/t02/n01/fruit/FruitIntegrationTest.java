package cat.itacademy.s04.t02.n01.fruit;

import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitRequestDTO;
import cat.itacademy.s04.t02.n01.fruit.model.dto.FruitUpdateDTO;
import cat.itacademy.s04.t02.n01.fruit.repository.FruitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FruitIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired private FruitRepository fruitRepository;

    @BeforeEach
    void setUp() {
        fruitRepository.deleteAll();
    }

    @Test
    void createFruit_ThenGetById_ShouldWork() throws Exception {
        FruitRequestDTO apple = new FruitRequestDTO("apple", 10);

        String response = mockMvc.perform(post("/fruits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(apple)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("apple"))
                .andReturn().getResponse().getContentAsString();

        Long id = objectMapper.readTree(response).get("id").asLong();

        mockMvc.perform(get("/fruits/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("apple"));
    }

    @Test
    void createFruit_ThenList_ShouldReturnOne() throws Exception {
        FruitRequestDTO apple = new FruitRequestDTO("apple", 10);

        mockMvc.perform(post("/fruits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(apple)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/fruits"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("[0].name").value("apple"));
    }

    @Test
    void createFruit_ThenUpdate_ShouldReturnUpdated() throws Exception {
        FruitRequestDTO apple = new FruitRequestDTO("apple", 10);

        String response = mockMvc.perform(post("/fruits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(apple)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Long id = objectMapper.readTree(response).get("id").asLong();

        FruitUpdateDTO update = new FruitUpdateDTO("mango", 50);

        mockMvc.perform(put("/fruits/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("mango"))
                .andExpect(jsonPath("$.weightInKilos").value(50));
    }

    @Test
    void createFruit_ThenDelete_ShouldReturn204() throws Exception {
        FruitRequestDTO apple = new FruitRequestDTO("apple", 10);

        String response = mockMvc.perform(post("/fruits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(apple)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Long id = objectMapper.readTree(response).get("id").asLong();

        mockMvc.perform(delete("/fruits/" + id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/fruits/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void getById_WhenNotExists_ShouldReturn404() throws Exception {
        mockMvc.perform(get("/fruits/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void createFruit_WithInvalidData_ShouldReturn400() throws Exception {
        FruitRequestDTO invalid = new FruitRequestDTO("", -5);

        mockMvc.perform(post("/fruits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void listFruits_WhenEmpty_ShouldReturnEmptyArray() throws Exception {
        mockMvc.perform(get("/fruits"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.size()").value(0));
    }
}
