package developer.jota.mapper;

import developer.jota.models.User;
import developer.jota.request.UserPostRequest;
import developer.jota.request.UserPutRequest;
import developer.jota.response.UserGetResponse;
import developer.jota.response.UserPostResponse;
import developer.jota.response.UserPutResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(100_000))")
    User toUser(UserPostRequest userPostRequest);

    UserGetResponse toUserGetResponse(User user);
    List<UserGetResponse> toListUserGetResponse(List<User> users);
    UserPostResponse toUserPostResponse(User user);
    User toUser(UserPutRequest userPutRequest);
    UserPutResponse toUserPutResponse(User user);
}
