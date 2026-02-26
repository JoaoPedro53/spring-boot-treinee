package developer.jota.controller;

import developer.jota.domain.Anime;
import developer.jota.domain.Producer;
import developer.jota.mapper.ProducerMapper;
import developer.jota.response.ProducerGetResponse;
import developer.jota.resquest.ProducerPostRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("v1/producers")
public class ProducerController {
    private static final ProducerMapper MAPPER = ProducerMapper.INSTANCE;

    @GetMapping
    public List<Producer> listAllOrProducerByName(@RequestParam(required = false) String name) {
        var producers = Producer.getProducers();
        if (name == null) return producers;
        return producers.stream().filter(producer -> producer.getName().equalsIgnoreCase(name))
                .toList();

    }

    @GetMapping("{ID}")
    public Producer findById(@PathVariable Long ID) {
        return Producer.getProducers().stream()
                .filter(producer -> producer.getId().equals(ID))
                .findFirst().orElse(null);


    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = "x-api-key")
    public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest producerPostRequest, @RequestHeader HttpHeaders headers){

        var producer = MAPPER.toProducer(producerPostRequest);
        Producer.getProducers().add(producer);
        var response = MAPPER.toProducerGetRespnse(producer);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
