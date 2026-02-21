package developer.jota.controller;

import developer.jota.domain.Anime;
import developer.jota.domain.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RestController
@RequestMapping("v1/producers")
public class ProducerController {

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
            headers = "x-api-key=1234")
    public ResponseEntity<Producer> save(@RequestBody Producer producer, @RequestHeader HttpHeaders headers){
        producer.setId(ThreadLocalRandom.current().nextLong(100_000));
        Producer.getProducers().add(producer);

        var responseHeaders = new HttpHeaders();
        responseHeaders.add("Authorization", "MY_KEY");
        return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(producer);
    }

}
