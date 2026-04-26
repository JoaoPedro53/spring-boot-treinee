package developer.jota.controller;

import developer.jota.domain.Producer;
import developer.jota.repository.ProducerData;
import org.junit.jupiter.api.*;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(controllers = ProducerController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = "developer.jota")
//@Import({ProducerMapperImpl.class, ProducerService.class, ProducerRepository.class, ProducerData.class})
class ProducerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProducerData producerData;
    private final List<Producer> producersList = new ArrayList<>();
    @Autowired
    private ResourceLoader resourceLoader;

    @BeforeEach
    void init(){
        var dateTime = "2026-03-26T18:42:59.828324";
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
        var dateTimeFormatter = LocalDateTime.parse(dateTime, formatter);

        var mappa = Producer.builder().name("MAPPA").id(0L).createdAt(dateTimeFormatter).build();
        var ghibli = Producer.builder().name("Studio Ghibli").id(1L).createdAt(dateTimeFormatter).build();
        var toei = Producer.builder().name("TOEI Animation").id(2L).createdAt(dateTimeFormatter).build();
        producersList.addAll(List.of(mappa, ghibli, toei));
    }

    @Test
    @DisplayName("GET v1/producers returns list with all producers when argument is null")
    @Order(1)
    void findAll_ReturnAllProducers_WhenArgumentIsNull() throws Exception {
        BDDMockito.when(producerData.getProducers()).thenReturn(producersList);
        var response = readResourceFile("producer/get-producer-null-name-200.json");

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/producers"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));

    }

    @Test
    @DisplayName("GET v1/producers?name=MAPPA returns list with producers by argument name is exists")
    @Order(2)
    void findAll_ReturnAllProducerByName_WhenArgumentNameIsExists() throws Exception {
        BDDMockito.when(producerData.getProducers()).thenReturn(producersList);
        var response = readResourceFile("producer/get-producer-by-MAPPA-200.json");

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/producers").param("name", "MAPPA"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));

    }

    @Test
    @DisplayName("GET v1/producers?name=x returns list empty when name not found")
    @Order(3)
    void findAll_ReturnListEmpty_WhenNameIsNotFound() throws Exception {
        BDDMockito.when(producerData.getProducers()).thenReturn(producersList);
        var response = readResourceFile("producer/get-producer-name-not-found-200.json");

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/producers").param("name", "x"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));

    }

    @Test
    @DisplayName("GET v1/producers/1 returns a producer with given id")
    @Order(4)
    void findById_ReturnProducerById_WhenIsSuccessful() throws Exception {
        BDDMockito.when(producerData.getProducers()).thenReturn(producersList);
        var response = readResourceFile("producer/get-producer-by-id-1.json");

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/producers/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));

    }

    @Test
    @DisplayName("GET v1/producers/99 throw ResponseStatusException 404 when producer is not found")
    @Order(5)
    void findById_ThrowResponseStatusException_WhenProducerIsNotFound() throws Exception {
        BDDMockito.when(producerData.getProducers()).thenReturn(producersList);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/producers/{id}", 99))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().reason("Producer not Found"));
    }

    private String readResourceFile(String fileName) throws IOException {
        var file = resourceLoader.getResource("classpath:%s".formatted(fileName)).getFile();
        return new String(Files.readAllBytes(file.toPath()));
    }
}