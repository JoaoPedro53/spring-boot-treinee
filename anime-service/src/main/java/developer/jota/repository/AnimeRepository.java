package developer.jota.repository;

import developer.jota.domain.Anime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AnimeRepository {
    private static List<Anime> ANIMES = new ArrayList<>();

    public List<Anime> findAll() {
        return ANIMES;
    }

    public List<Anime> findByName(String name) {
        return ANIMES.stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).toList();
    }

    public Anime findById(Long id) {
        return ANIMES.stream().filter(anime -> anime.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not Found"));
    }

    public Anime save(Anime anime) {
        ANIMES.add(anime);
        return anime;
    }

    public void delete(Anime anime) {
        ANIMES.remove(anime);
    }

    public void update(Anime anime) {
        delete(anime);
        save(anime);
    }


}
