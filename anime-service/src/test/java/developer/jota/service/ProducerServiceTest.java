package developer.jota.service;

import developer.jota.domain.Producer;
import developer.jota.repository.ProducerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProducerServiceTest {
    @InjectMocks
    private ProducerService service;
    @Mock
    private ProducerRepository repository;
    private final List<Producer> producersList = new ArrayList<>();

    @BeforeEach
    void init() {
        var mappa = Producer.builder().name("MAPPA").id(0L).createdAt(LocalDateTime.now()).build();
        var ghibli = Producer.builder().name("Studio Ghibli").id(1L).createdAt(LocalDateTime.now()).build();
        var toei = Producer.builder().name("TOEI Animation").id(2L).createdAt(LocalDateTime.now()).build();
        producersList.addAll(List.of(mappa, ghibli, toei));
    }

    @Test
    @DisplayName("findAll returns list with all producers when argument is null")
    @Order(1)
    void findAll_ReturnAllProducers_WhenArgumentIsNull() {
        BDDMockito.when(repository.findAll()).thenReturn(producersList);

        var producers = service.listAll(null);
        Assertions.assertThat(producers).isNotNull().hasSameElementsAs(producersList);
    }

    @Test
    @DisplayName("findAll returns list with producer by argument name is exists")
    @Order(2)
    void findAll_ReturnAllProducerByName_WhenArgumentNameIsExists() {
        var producer = producersList.getFirst();
        var foundProducer = singletonList(producer);

        BDDMockito.when(repository.findByName(producer.getName())).thenReturn(foundProducer);

        var producers = service.listAll(producer.getName());
        Assertions.assertThat(producers).isNotNull().containsAll(foundProducer);
    }

    @Test
    @DisplayName("findAll returns list empty when name not found")
    @Order(3)
    void findAll_ReturnListEmpty_WhenNameIsNotFound() {
        BDDMockito.when(repository.findByName("StudioZodik")).thenReturn(List.of());

        var producers = service.listAll("StudioZodik");
        Assertions.assertThat(producers).isNotNull().isEmpty();
    }
    @Test
    @DisplayName("findById return producer with give id")
    @Order(4)
    void findById_ReturnProducerById_WhenSuccessful() {
        var producer = producersList.getFirst();
        BDDMockito.when(repository.findById(producer.getId())).thenReturn(Optional.of(producer));

        var producerById = service.findByIdOrThrowNotFoundException(producer.getId());
        Assertions.assertThat(producerById).isEqualTo(producer);
    }

    @Test
    @DisplayName("findById throw ResponseStatusException when producer is not found")
    @Order(5)
    void findById_ThrowResponseStatusException_WhenProducerIsNotFound() {
        var producer = producersList.getFirst();
        BDDMockito.when(repository.findById(producer.getId())).thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() -> service.findByIdOrThrowNotFoundException(producer.getId()))
                .isInstanceOf(ResponseStatusException.class);
    }

    @Test
    @DisplayName("save producer is transfer correct for repository to be saved")
    @Order(6)
    void save_TransferProducerForRepositoryToBeSaved_WhenSuccessful() {
        var producer = Producer.builder().name("Japan").id(5L).createdAt(LocalDateTime.now()).build();
        BDDMockito.when(repository.save(producer)).thenReturn(producer);

        var producerToSave = service.save(producer);

        Assertions.assertThat(producerToSave).isNotNull().isEqualTo(producer);
        BDDMockito.then(repository).should().save(producer);
    }

    @Test
    @DisplayName("delete removes producer")
    @Order(7)
    void delete_RemoveProducer_WhenSuccessful() {
        var producerToDelete = producersList.getFirst();
        BDDMockito.when(repository.findById(producerToDelete.getId())).thenReturn(Optional.of(producerToDelete));
        BDDMockito.doNothing().when(repository).delete(producerToDelete);

        Assertions.assertThatNoException().isThrownBy(() -> service.delete(producerToDelete.getId()));
    }

    @Test
    @DisplayName("delete throws ResponseStatusException when producer is not found ")
    @Order(8)
    void delete_ThrowsResponseStatusException_WhenProducerIsNotFound() {
        var producerToDelete = producersList.getFirst();
        BDDMockito.when(repository.findById(producerToDelete.getId())).thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() -> service.delete(producerToDelete.getId()))
                .isInstanceOf(ResponseStatusException.class);
    }

    @Test
    @DisplayName("update updates a producer")
    @Order(9)
    void update_updatedProducer_WhenSuccessful() {
        var producerToUpdate = producersList.getFirst();
        producerToUpdate.setName("JApon");

        BDDMockito.when(repository.findById(producerToUpdate.getId())).thenReturn(Optional.of(producerToUpdate));
        BDDMockito.doNothing().when(repository).update(producerToUpdate);

        Assertions.assertThatNoException().isThrownBy(() -> service.update(producerToUpdate));
    }

    @Test
    @DisplayName("update throw ResponseStatusException when producer is not found")
    @Order(10)
    void update_ThrowProducerNotFound_WhenProducerIsNotFound() {
        var producerToUpdate = producersList.getFirst();
        BDDMockito.when(repository.findById(producerToUpdate.getId())).thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() -> service.update(producerToUpdate))
                .isInstanceOf(ResponseStatusException.class);
    }
}

