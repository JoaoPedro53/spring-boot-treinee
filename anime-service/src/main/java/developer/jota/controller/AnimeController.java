package developer.jota.controller;

import developer.jota.domain.Anime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RestController
@RequestMapping("v1/animes")
public class AnimeController {

    @GetMapping
    public List<Anime> listAllOrAnimeByName(@RequestParam(required = false) String name) {
        var animes = Anime.getAnimes();
        if (name == null) return animes;
        return animes.stream().filter(anime -> anime.getName().equalsIgnoreCase(name))
                .toList();

    }

    @GetMapping("{ID}")
    public Anime findById(@PathVariable Long ID) {
        return Anime.getAnimes().stream()
                .filter(anime -> anime.getId().equals(ID))
                .findFirst().orElse(null);


    }

    @PostMapping
    public Anime save(@RequestBody Anime anime){
        anime.setId(ThreadLocalRandom.current().nextLong(100_000));
        Anime.getAnimes().add(anime);
        return anime;
    }

}
