package developer.jota.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Producer {
    private Long id;
    @JsonProperty("name")
    private String name;
    @Getter
    private static List<Producer> producers = new ArrayList<>();

    static {
        Producer mappa = new Producer(0L, "MAPPA");
        Producer toei = new Producer(1L, "TOEI ANIMATION");
        producers.addAll(List.of(mappa, toei));
    }

}
