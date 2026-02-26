package developer.jota.response;

import developer.jota.domain.Anime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class AnimeGetResponse {
    private Long id;
    private String name;

}
