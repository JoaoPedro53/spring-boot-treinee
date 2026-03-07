package developer.jota.service;

import developer.jota.domain.Anime;
import developer.jota.domain.Producer;
import developer.jota.repository.AnimeRepository;
import developer.jota.repository.ProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository repository;

    public List<Anime> listAll(String name) {
        return name == null ? repository.findAll() : repository.findByName(name);
    }

    public Anime findById(Long id) {
        return repository.findById(id);
    }

    public Anime save(Anime anime) {
        repository.save(anime);
        return anime;
    }

    public void delete(Long id) {
        var anime = repository.findById(id);
        repository.delete(anime);
    }

    public void update(Anime animeToUpdate) {
        repository.update(animeToUpdate);
    }
}
