package developer.jota.repository;

import developer.jota.domain.Producer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProducerRepositoryTest {
    @InjectMocks
    private ProducerRepository repository;
    @Mock
    private ProducerData producerData;
    private final List<Producer> producersList = new ArrayList<>();

    @BeforeEach
    void init() {
        var mappa = Producer.builder().name("MAPPA").id(0L).createdAt(LocalDateTime.now()).build();
        var ghibli = Producer.builder().name("Studio Ghibli").id(1L).createdAt(LocalDateTime.now()).build();
        var toei = Producer.builder().name("TOEI Animation").id(2L).createdAt(LocalDateTime.now()).build();
        producersList.addAll(List.of(mappa, ghibli, toei));
    }

    @Test
    @DisplayName("findAll returns list with all producers")
    @Order(1)
    void findAll_ReturnAllProducers_WhenSucessful() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producersList);
        var producers = repository.findAll();
        Assertions.assertThat(producers).isNotNull().hasSameElementsAs(producers);
    }

    @Test
    @DisplayName("findById return a producer with give id")
    @Order(2)
    void findById_ReturnProducerById_WhenSucessful() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producersList);

        var expectedProducer = producersList.getFirst();
        var producers = repository.findById(expectedProducer.getId());
        Assertions.assertThat(producers).isPresent().contains(expectedProducer);
    }

    @Test
    @DisplayName("findByName return empty list when name is null")
    @Order(3)
    void findByName_ReturnProducersEmpty_WhenNameIsNull() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producersList);

        var producers = repository.findByName(null);
        Assertions.assertThat(producers).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("findByName return producer list with objects when name exists")
    @Order(4)
    void findByName_ReturnFoundProducerInList_WhenNameIsFound() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producersList);

        var expectedProducer = producersList.getFirst();
        var producers = repository.findByName(expectedProducer.getName());
        Assertions.assertThat(producers).hasSize(1).contains(expectedProducer).isNotNull().isNotEmpty();
    }

}