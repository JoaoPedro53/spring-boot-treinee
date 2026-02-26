package developer.jota.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Anime {
    private Long id;
    private String name;
    @Getter
    private static List<Anime> animes = new ArrayList<>();

    static {
        Anime onePiece = new Anime(0L, "One Piece");
        Anime kimetsu = new Anime(1L, "Kimetsu");
        animes.addAll(List.of(onePiece, kimetsu));
    }

}
