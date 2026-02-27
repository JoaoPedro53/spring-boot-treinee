package developer.jota.controller;

import developer.jota.domain.Producer;
import developer.jota.mapper.ProducerMapper;
import developer.jota.response.ProducerGetResponse;
import developer.jota.response.ProducerPostResponse;
import developer.jota.resquest.ProducerPostRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("v1/producers")
public class ProducerController {
    private static final ProducerMapper MAPPER = ProducerMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<List<ProducerGetResponse>> listAllOrProducerByName(@RequestParam(required = false) String name) {
        log.info("request received to list all producers, param name '{}'", name);

        var producers = Producer.getProducers();
        var listProducerGetResponse = MAPPER.toListProducerGetResponse(producers);
        if (name == null) return ResponseEntity.ok(listProducerGetResponse);
        var response = listProducerGetResponse.stream()
                .filter(producer -> producer.getName().equalsIgnoreCase(name))
                .toList();

        return ResponseEntity.ok(response);

    }

    @GetMapping("{ID}")
    public ResponseEntity<ProducerGetResponse> findById(@PathVariable Long ID) {
        log.info("Request to find producer by id: '{}'", ID);

        var response = Producer.getProducers().stream()
                .filter(producer -> producer.getId().equals(ID))
                .findFirst()
                .map(MAPPER::toProducerGetResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producer not Found"));

        return ResponseEntity.ok(response);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = "x-api-key")
    public ResponseEntity<ProducerPostResponse> save(@RequestBody ProducerPostRequest producerPostRequest) {
        log.info("request to save producer: '{}'", producerPostRequest);

        var producer = MAPPER.toProducer(producerPostRequest);
        Producer.getProducers().add(producer);
        var response = MAPPER.toProducerPostResponse(producer);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{ID}")
    public ResponseEntity<Void> deleteById(@PathVariable Long ID) {
        log.info("Request to delete producer by id: '{}'", ID);

        var producerToDelete = Producer.getProducers().stream()
                .filter(producer -> producer.getId().equals(ID))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producer not Found"));

        Producer.getProducers().remove(producerToDelete);
        return ResponseEntity.noContent().build();
    }

}
