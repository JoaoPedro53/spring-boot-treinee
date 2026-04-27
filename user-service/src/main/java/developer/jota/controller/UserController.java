package developer.jota.controller;

import developer.jota.models.User;
import developer.jota.repository.UserRepositoryData;
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
    private final UserRepositoryData repositoryData;

    @GetMapping
    public ResponseEntity<List<User>> listAll(){
        return ResponseEntity.ok(repositoryData.getUSERS());
    }
}
