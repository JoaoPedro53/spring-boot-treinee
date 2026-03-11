package developer.jota.controller;

import developer.jota.mapper.ProducerMapper;
import developer.jota.response.ProducerGetResponse;
import developer.jota.response.ProducerPostResponse;
import developer.jota.resquest.ProducerPostRequest;
import developer.jota.resquest.ProducerPutRequest;
import developer.jota.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("v1/producers")
@RequiredArgsConstructor
public class ProducerController {
    private final ProducerMapper mapper;
    private final ProducerService service;

    @GetMapping
    public ResponseEntity<List<ProducerGetResponse>> listAllOrProducerByName(@RequestParam(required = false) String name) {
        log.info("request received to list all producers, param name '{}'", name);

        var listProducerGetResponse = mapper.toListProducerGetResponse(service.listAll(name));
        return ResponseEntity.ok(listProducerGetResponse);

    }

    @GetMapping("{id}")
    public ResponseEntity<ProducerGetResponse> findById(@PathVariable Long id) {
        log.info("Request to find producer by id: '{}'", id);

        var producer = service.findById(id);
        var producerGetResponse = mapper.toProducerGetResponse(producer);

        return ResponseEntity.ok(producerGetResponse);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "x-api-key")
    public ResponseEntity<ProducerPostResponse> save(@RequestBody ProducerPostRequest producerPostRequest) {
        log.info("request to save producer: '{}'", producerPostRequest);

        var producer = mapper.toProducer(producerPostRequest);
        service.save(producer);
        var response = mapper.toProducerPostResponse(producer);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("Request to delete producer by id: '{}'", id);

        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ProducerPutRequest putRequest) {
        log.info("Request to update producer by id: '{}'", putRequest.getId());

        var producer = mapper.putRequestToProducer(putRequest);
        service.update(producer);

        return ResponseEntity.noContent().build();
    }

}
