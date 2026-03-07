package developer.jota.mapper;

import developer.jota.domain.Producer;
import developer.jota.response.ProducerGetResponse;
import developer.jota.resquest.ProducerPostRequest;
import developer.jota.resquest.ProducerPutRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProducerMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(100_000))")
    Producer toProducer(ProducerPostRequest postRequest);
    Producer putRequestToProducer(ProducerPutRequest putRequest);

    ProducerPostResponse toProducerPostResponse(Producer producer);

    ProducerGetResponse toProducerGetResponse(Producer producer);

    List<ProducerGetResponse> toListProducerGetResponse(List<Producer> producers);
}
