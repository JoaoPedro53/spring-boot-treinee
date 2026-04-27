package developer.jota.controller;

import developer.jota.models.User;
import developer.jota.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    public ResponseEntity<List<User>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }
}
