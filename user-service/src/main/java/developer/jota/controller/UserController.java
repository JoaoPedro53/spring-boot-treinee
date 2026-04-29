package developer.jota.controller;

import developer.jota.mapper.UserMapper;
import developer.jota.models.User;
import developer.jota.request.UserPostRequest;
import developer.jota.request.UserPutRequest;
import developer.jota.response.UserGetResponse;
import developer.jota.response.UserPostResponse;
import developer.jota.response.UserPutResponse;
import developer.jota.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    @GetMapping
    public ResponseEntity<List<UserGetResponse>> listAll() {
        var listUserGetResponse = mapper.toListUserGetResponse(service.listAll());
        return ResponseEntity.ok(listUserGetResponse);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserPostResponse> save(@RequestBody UserPostRequest postRequest) {
        var userToSave = mapper.toUser(postRequest);
        var user = service.save(userToSave);
        var response = mapper.toUserPostResponse(user);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){

        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<UserPutResponse> update(@RequestBody UserPutRequest putRequest) {

        var request = mapper.toUser(putRequest);
        var user = service.update(request);
        var response = mapper.toUserPutResponse(user);

        return ResponseEntity.ok(response);
    }
}
