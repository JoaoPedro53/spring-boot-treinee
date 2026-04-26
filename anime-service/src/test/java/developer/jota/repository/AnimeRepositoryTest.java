package developer.jota.repository;

import developer.jota.domain.Anime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnimeRepositoryTest {
    @InjectMocks
    private AnimeRepository repository;
    @Mock
    private AnimeData animeData;
    private final List<Anime> animeList = new ArrayList<>();

    @BeforeEach
    void init() {
        var aot = Anime.builder().name("AOT").id(0L).build();
        var onePiece = Anime.builder().name("One Piece").id(1L).build();
        var sao = Anime.builder().name("SAO").id(2L).build();
        animeList.addAll(List.of(aot, onePiece, sao));
    }

    @Test
    @DisplayName("findAll return all animes when successful")
    @Order(1)
    void findAll_ReturnAllAnimes_WhenSuccessful() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animeList);
        var animes = repository.findAll();
        Assertions.assertThat(animes).isNotNull().hasSameElementsAs(animeList);
    }

    @Test
    @DisplayName("findByName return anime by name when successful")
    @Order(2)
    void findByName_ReturnAnimesByName_WhenSuccessful(){
        BDDMockito.when(animeData.getAnimes()).thenReturn(animeList);

        var animes = repository.findByName(null);
        Assertions.assertThat(animes).isNotNull().isEmpty();

    }

    @Test
    @DisplayName("findById return anime by id when successful")
    @Order(3)
    void findById_ReturnAnimesById_WhenSuccessful(){
        BDDMockito.when(animeData.getAnimes()).thenReturn(animeList);

        var animeById = animeList.getFirst();
        var anime = repository.findById(animeById.getId());

        Assertions.assertThat(anime).isPresent().contains(animeById);
    }

    @Test
    @DisplayName("save saved anime when successful")
    @Order(4)
    void save_SavedAnime_WhenSuccessful(){
        BDDMockito.when(animeData.getAnimes()).thenReturn(animeList);

        var animeToSave = Anime.builder().name("Jojo").id(10L).build();
        repository.save(animeToSave);
        var anime = repository.findById(animeToSave.getId());

        Assertions.assertThat(anime).isNotNull().contains(animeToSave);

    }

    @Test
    @DisplayName("delete remove anime when successful")
    @Order(5)
    void delete_RemoveAnime_WhenSuccessful(){
        BDDMockito.when(animeData.getAnimes()).thenReturn(animeList);

        var animeToDelete = animeList.getFirst();
        repository.delete(animeToDelete);

        Assertions.assertThat(animeList).doesNotContain(animeToDelete);

    }

    @Test
    @DisplayName("update update anime when successful")
    @Order(6)
    void update_UpdateAnime_WhenSuccessful(){
        BDDMockito.when(animeData.getAnimes()).thenReturn(animeList);

        var animeToUpdate = animeList.getFirst();
        animeToUpdate.setName("Dragon Ball");
        repository.update(animeToUpdate);

        Assertions.assertThat(this.animeList).contains(animeToUpdate);

        var anime = repository.findById(animeToUpdate.getId());
        Assertions.assertThat(anime).isPresent();
        Assertions.assertThat(anime.get().getName()).isEqualTo(animeToUpdate.getName());

    }

}