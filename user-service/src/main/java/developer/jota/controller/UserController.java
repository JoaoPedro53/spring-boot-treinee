package developer.jota.controller;

import developer.jota.mapper.UserMapper;
import developer.jota.response.UserGetResponse;
import developer.jota.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
