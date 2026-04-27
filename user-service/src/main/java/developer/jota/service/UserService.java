package developer.jota.service;

import developer.jota.models.User;
import developer.jota.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<User> listAll() {
        return repository.listAll();
    }
}
