package developer.jota.service;

import developer.jota.domain.Anime;
import developer.jota.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository repository;

    public List<Anime> listAll(String name) {
        return name == null ? repository.findAll() : repository.findByName(name);
    }

    public Anime findByIdOrElseThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not Found"));
    }

    public Anime save(Anime anime) {
        repository.save(anime);
        return anime;
    }

    public void delete(Long id) {
        var anime = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not Found"));
        repository.delete(anime);
    }

    public void update(Anime animeToUpdate) {
        var anime = repository.findById(animeToUpdate.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not Found"));
        repository.update(anime);
    }
}
