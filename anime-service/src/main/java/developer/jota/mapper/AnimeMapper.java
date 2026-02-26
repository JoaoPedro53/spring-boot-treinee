package developer.jota.mapper;

import developer.jota.domain.Anime;
import developer.jota.response.AnimeGetResponse;
import developer.jota.response.AnimePostResponse;
import developer.jota.resquest.AnimePostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AnimeMapper {
    AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);
    
    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(100_000))")
    Anime toAnime(AnimePostRequest postRequest);
    AnimePostResponse toAnimePostResponse(Anime anime);

    AnimeGetResponse toAnimeGetResponse(Anime anime);
    List<AnimeGetResponse> toListAnimeGetResponse(List<Anime> animes);
}
