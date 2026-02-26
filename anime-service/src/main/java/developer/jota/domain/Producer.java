package developer.jota.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class Producer {
    private Long id;
    @JsonProperty("name")
    private String name;
    private LocalDateTime createdAt;
    private static List<Producer> producers = new ArrayList<>();

    static {
        var mappa = Producer.builder().id(0L).name("MAPPA").createdAt(LocalDateTime.now()).build();
        var toei = Producer.builder().id(1L).name("TOEI ANIMATION").createdAt(LocalDateTime.now()).build();
        producers.addAll(List.of(mappa, toei));
    }

    public static List<Producer> getProducers() {
        return producers;
    }
}
