package developer.jota.repository;

import developer.jota.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final UserRepositoryData repositoryData;

    public List<User> listAll() {
        return repositoryData.getUSERS();
    }

    public User save(User user){
        repositoryData.getUSERS().add(user);
        return user;
    }

}
