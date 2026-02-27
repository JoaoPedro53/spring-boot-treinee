package developer.jota.controller;

import developer.jota.domain.Anime;
import developer.jota.mapper.AnimeMapper;
import developer.jota.response.AnimeGetResponse;
import developer.jota.response.AnimePostResponse;
import developer.jota.resquest.AnimePostRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("v1/animes")
public class AnimeController {
    private static final AnimeMapper MAPPER = AnimeMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<List<AnimeGetResponse>> listAllOrAnimeByName(@RequestParam(required = false) String name) {
        log.info("request received to list all animes, param name '{}'", name);

        var animes = Anime.getAnimes();
        var listAnimeGetResponse = MAPPER.toListAnimeGetResponse(animes);
        if (name == null) return ResponseEntity.ok(listAnimeGetResponse);
        var response = listAnimeGetResponse.stream()
                .filter(anime -> anime.getName().equalsIgnoreCase(name))
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("{ID}")
    public ResponseEntity<AnimeGetResponse> findById(@PathVariable Long ID) {
        log.info("Request to find anime by id: '{}'", ID);
        var response = Anime.getAnimes().stream()
                .filter(anime -> anime.getId().equals(ID))
                .findFirst()
                .map(MAPPER::toAnimeGetResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not Found"));

        return ResponseEntity.ok(response);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnimePostResponse> save(@RequestBody AnimePostRequest request) {
        log.info("request to save anime: '{}'", request);
        var anime = MAPPER.toAnime(request);
        Anime.getAnimes().add(anime);
        var response = MAPPER.toAnimePostResponse(anime);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{ID}")
    public ResponseEntity<Void> deleteById(@PathVariable Long ID) {
        log.info("Request to delete anime by id: '{}'", ID);

        var animeToDelete = Anime.getAnimes().stream()
                .filter(producer -> producer.getId().equals(ID))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not Found"));

        Anime.getAnimes().remove(animeToDelete);
        return ResponseEntity.noContent().build();
    }

}
