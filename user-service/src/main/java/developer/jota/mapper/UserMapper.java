package developer.jota.mapper;

import developer.jota.models.User;
import developer.jota.response.UserGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(100_000))")
    UserGetResponse toUserGetResponse(User user);

    List<UserGetResponse> toListUserGetResponse(List<User> users);
}
