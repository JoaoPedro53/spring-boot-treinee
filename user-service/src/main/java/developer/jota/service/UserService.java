package developer.jota.service;

import developer.jota.models.User;
import developer.jota.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<User> listAll() {
        return repository.listAll();
    }
}
