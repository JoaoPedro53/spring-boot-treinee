package developer.jota.service;

import developer.jota.domain.Anime;
import developer.jota.repository.AnimeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnimeServiceTest {
    @InjectMocks
    private AnimeService service;
    @Mock
    private AnimeRepository repository;
    private List<Anime> animeList = new ArrayList<>();

    @BeforeEach
    void init(){
        var aot = Anime.builder().name("AOT").id(0L).build();
        var onePiece = Anime.builder().name("One Piece").id(1L).build();
        var sao = Anime.builder().name("SAO").id(2L).build();
        animeList.addAll(List.of(aot, onePiece, sao));
    }

    @Test
    @Order(1)
    @DisplayName("findAll return list all animes when name is null")
    void findAll_ReturnAllAnimes_WhenNameIsNull(){
        BDDMockito.when(repository.findAll()).thenReturn(animeList);
        var animes = service.listAll(null);

        Assertions.assertThat(animes).isNotNull().hasSameElementsAs(animeList);
    }

    @Test
    @Order(2)
    @DisplayName("findAll return list anime by name when name exists")
    void findAll_ReturnAnimeByName_WhenNameExists(){
        var animeExpected = animeList.getFirst();
        BDDMockito.when(repository.findByName(animeExpected.getName())).thenReturn(Collections.singletonList(animeExpected));
        var anime = service.listAll(animeExpected.getName());

        Assertions.assertThat(anime).isNotNull().contains(animeExpected);
    }

    @Test
    @Order(3)
    @DisplayName("findAll return empty list when name is not found")
    void findAll_ReturnEmptyList_WhenNameIsNotFound(){
        String nameTest = "Iujora";
        BDDMockito.when(repository.findByName(nameTest)).thenReturn(List.of());
        var animes = service.listAll(nameTest);

        Assertions.assertThat(animes).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("findById return producer with give id")
    @Order(4)
    void findById_ReturnProducerById_WhenSuccessful() {
        var producer = animeList.getFirst();
        BDDMockito.when(repository.findById(producer.getId())).thenReturn(Optional.of(producer));

        var producerById = service.findByIdOrElseThrow(producer.getId());
        Assertions.assertThat(producerById).isEqualTo(producer);
    }

    @Test
    @DisplayName("findById throw ResponseStatusException when anime is not found")
    @Order(5)
    void findById_ThrowResponseStatusException_WhenAnimeIsNotFound() {
        var anime = animeList.getFirst();
        BDDMockito.when(repository.findById(anime.getId())).thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() -> service.findByIdOrElseThrow(anime.getId()))
                .isInstanceOf(ResponseStatusException.class);
    }

    @Test
    @DisplayName("save anime is transfer correct for repository to be saved")
    @Order(6)
    void save_TransferAnimeForRepositoryToBeSaved_WhenSuccessful() {
        var anime = Anime.builder().name("Tenku Shipan").id(5L).build();
        BDDMockito.when(repository.save(anime)).thenReturn(anime);

        var producerToSave = service.save(anime);

        Assertions.assertThat(producerToSave).isNotNull().isEqualTo(anime);
        BDDMockito.then(repository).should().save(anime);
    }

    @Test
    @DisplayName("delete removes anime")
    @Order(7)
    void delete_RemoveAnime_WhenSuccessful() {
        var animeToDelete = animeList.getFirst();
        BDDMockito.when(repository.findById(animeToDelete.getId())).thenReturn(Optional.of(animeToDelete));
        BDDMockito.doNothing().when(repository).delete(animeToDelete);

        Assertions.assertThatNoException().isThrownBy(() -> service.delete(animeToDelete.getId()));
    }

    @Test
    @DisplayName("delete throws ResponseStatusException when anime is not found ")
    @Order(8)
    void delete_ThrowsResponseStatusException_WhenAnimeIsNotFound() {
        var animeToDelete = animeList.getFirst();
        BDDMockito.when(repository.findById(animeToDelete.getId())).thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() -> service.delete(animeToDelete.getId()))
                .isInstanceOf(ResponseStatusException.class);
    }

    @Test
    @DisplayName("update updates a anime")
    @Order(9)
    void update_updatedAnime_WhenSuccessful() {
        var animeToUpdate = animeList.getFirst();
        animeToUpdate.setName("JApon");

        BDDMockito.when(repository.findById(animeToUpdate.getId())).thenReturn(Optional.of(animeToUpdate));
        BDDMockito.doNothing().when(repository).update(animeToUpdate);

        Assertions.assertThatNoException().isThrownBy(() -> service.update(animeToUpdate));
    }

    @Test
    @DisplayName("update throw ResponseStatusException when anime is not found")
    @Order(10)
    void update_ThrowNotFound_WhenAnimeIsNotFound() {
        var animeToUpdate = animeList.getFirst();
        BDDMockito.when(repository.findById(animeToUpdate.getId())).thenReturn(Optional.empty());

        Assertions.assertThatException()
                .isThrownBy(() -> service.update(animeToUpdate))
                .isInstanceOf(ResponseStatusException.class);
    }
}