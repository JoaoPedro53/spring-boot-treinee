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
import java.util.Optional;

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
    void findAll_ReturnAllProducers_WhenSuccessful() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producersList);
        var producers = repository.findAll();
        Assertions.assertThat(producers).isNotNull().hasSameElementsAs(producersList);
    }

    @Test
    @DisplayName("findById return a producer with give id")
    @Order(2)
    void findById_ReturnProducerById_WhenSuccessful() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producersList);

        var expectedProducer = producersList.getFirst();
        var producer = repository.findById(expectedProducer.getId());
        Assertions.assertThat(producer).isPresent().contains(expectedProducer);
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
    @DisplayName("save created a producer")
    @Order(4)
    void save_CreatesProducer_WhenSuccessful() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producersList);

        var producerToSave = Producer.builder().id(99L).name("OP studio").createdAt(LocalDateTime.now()).build();
        var producer = repository.save(producerToSave);

        Assertions.assertThat(producer).isEqualTo(producerToSave).hasNoNullFieldsOrProperties();

        var ProducerSaveOptional = repository.findById(producerToSave.getId());
        Assertions.assertThat(ProducerSaveOptional).isPresent().contains(producerToSave);
    }

    @Test
    @DisplayName("delete removes a producer")
    @Order(5)
    void delete_removeProducer_WhenSuccessful() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producersList);

        var removeProducer = producersList.getFirst();
        repository.delete(removeProducer);
        Assertions.assertThat(this.producersList).doesNotContain(removeProducer);
    }

    @Test
    @DisplayName("update updates a producer")
    @Order(6)
    void update_updatesProducer_WhenSuccessful() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producersList);

        var producerToUpdate = this.producersList.getFirst();
        producerToUpdate.setName("Aniplex");
        repository.update(producerToUpdate);

        Assertions.assertThat(this.producersList).contains(producerToUpdate);

        var producerUpdateOptional = repository.findById(producerToUpdate.getId());
        Assertions.assertThat(producerUpdateOptional).isPresent();
        Assertions.assertThat(producerUpdateOptional.get().getName()).isEqualTo(producerToUpdate.getName());
    }

}