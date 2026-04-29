package developer.jota.repository;

import developer.jota.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final UserRepositoryData repositoryData;

    public List<User> listAll() {
        return repositoryData.getUSERS();
    }

    public Optional<User> findById(Long id) {
        return repositoryData.getUSERS().stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public User save(User user){
        repositoryData.getUSERS().add(user);
        return user;
    }

    public void delete(User user){
        repositoryData.getUSERS().remove(user);
    }

}
