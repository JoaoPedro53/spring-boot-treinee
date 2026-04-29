package developer.jota.service;

import developer.jota.models.User;
import developer.jota.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<User> listAll() {
        return repository.listAll();
    }

    public User findByIdAndReturnUserOrThrowNotFound(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not Found"));
    }

    public User save(User user){
        return repository.save(user);
    }

    public void delete(Long id){
        var user = findByIdAndReturnUserOrThrowNotFound(id);
        repository.delete(user);
    }
}
