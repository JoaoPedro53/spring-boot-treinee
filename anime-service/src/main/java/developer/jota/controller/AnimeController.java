package developer.jota.controller;

import developer.jota.domain.Anime;
import developer.jota.mapper.AnimeMapper;
import developer.jota.response.AnimeGetResponse;
import developer.jota.response.AnimePostResponse;
import developer.jota.resquest.AnimePostRequest;
import developer.jota.resquest.AnimePutRequest;
import developer.jota.service.AnimeService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AnimeController {
    private final AnimeMapper mapper;
    private final AnimeService service;

    @GetMapping
    public ResponseEntity<List<AnimeGetResponse>> listAllOrAnimeByName(@RequestParam(required = false) String name) {
        log.info("request received to list all animes, param name '{}'", name);

        var animes = service.listAll(name);
        var listAnimeGetResponse = mapper.toListAnimeGetResponse(animes);

        return ResponseEntity.ok(listAnimeGetResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> findById(@PathVariable Long id) {
        log.info("Request to find anime by id: '{}'", id);

        var anime = service.findById(id);
        var response = mapper.toAnimeGetResponse(anime);
        return ResponseEntity.ok(response);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnimePostResponse> save(@RequestBody AnimePostRequest request) {
        log.info("request to save anime: '{}'", request);
        var anime = mapper.toAnime(request);
        service.save(anime);
        var response = mapper.toAnimePostResponse(anime);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("Request to delete anime by id: '{}'", id);

        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody AnimePutRequest putRequest) {
        log.info("Request to update anime by id: '{}'", putRequest.getId());

        var animeUpdate = mapper.toAnime(putRequest);
        service.update(animeUpdate);

        return ResponseEntity.noContent().build();
    }

}
