package developer.jota.repository;

import developer.jota.domain.Anime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnimeData {
    private final List<Anime> animes = new ArrayList<>();

    {
        var aot = Anime.builder().name("AOT").id(0L).build();
        var onePiece = Anime.builder().name("One Piece").id(1L).build();
        var sao = Anime.builder().name("SAO").id(2L).build();
        animes.addAll(List.of(aot, onePiece, sao));
    }

    public List<Anime> getAnimes() {
        return animes;
    }
}
