package developer.jota.repository;

import developer.jota.domain.Producer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProducerData {
    private final List<Producer> producers = new ArrayList<>();

   {
        var mappa = Producer.builder().name("JAPan").id(0L).createdAt(LocalDateTime.now()).build();
        var ghibli = Producer.builder().name("Studio Ghibli").id(1L).createdAt(LocalDateTime.now()).build();
        var toei = Producer.builder().name("TOEI Animation").id(2L).createdAt(LocalDateTime.now()).build();
        producers.addAll(List.of(mappa, ghibli, toei));
    }

    public List<Producer> getProducers() {
        return producers;
    }
}
